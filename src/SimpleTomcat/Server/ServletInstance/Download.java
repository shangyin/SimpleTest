package SimpleTomcat.Server.ServletInstance;

import SimpleTomcat.DataConnection;
import SimpleTomcat.Server.Request;
import SimpleTomcat.Server.Response;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/11.
 */
public class Download implements Servlet
{

    @Override
    public void service(Request request, Response response)
    {
        String uri = request.getParameter("file");

        if (crud.exist(uri))
        {
            downloading.incrementAndGet();

            File file = new File(homeDir.concat(uri));
            long size = file.length();
            response.addPara("size", String.valueOf(size));
            response.addPara(CODE, ANSWER_PERMIT);
            response.flush();

            //数据传输
            try
            {
                LinkedList<Socket> ls = dataConnection.get(request.getAddress());
                BufferedOutputStream out = new BufferedOutputStream(ls.getFirst().getOutputStream());
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

                while (size-- > 0)
                    out.write(in.read());
                out.flush();

                /* 资源处理 */
                in.close();
                dataConnection.release(request.getAddress());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            crud.increaseAndGetCount(request.getParameter("uri"));
            downloading.decrementAndGet();
        }
        else
        {
            response.addPara(CODE, ANSWER_DENY);
            response.flush();
        }
    }

}
