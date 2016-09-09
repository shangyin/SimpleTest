package SimpleTomcat.Server.ServletInstance;

import SimpleTomcat.Server.Request;
import SimpleTomcat.Server.Response;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/11.
 */
public class Upload implements Servlet
{
    @Override
    public void service(Request request, Response response)
    {
        String uri = request.getParameter("file");

        if (crud.exist(uri))
        {
            response.addPara(CODE, ANSWER_DENY);
            response.flush();
        }
        else
        {
            response.addPara(CODE, ANSWER_PERMIT);
            response.flush();

            uploading.incrementAndGet();

            long size = Long.valueOf(request.getParameter("size"));
            String ps = request.getParameter("ps");

            //write to the map
            crud.input(uri, ps);

            try
            {
                LinkedList<Socket> ls = dataConnection.get(request.getAddress());
                BufferedOutputStream out = new BufferedOutputStream(ls.getFirst().getOutputStream());
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(homeDir.concat(uri)));

                while(size-- > 0)
                    out.write(in.read());

                out.close();
                in.close();
                dataConnection.release(request.getAddress());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            uploading.decrementAndGet();
        }
    }
}
