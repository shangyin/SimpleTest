package SimpleTomcat.Client.HandlerInstance;

import SimpleTomcat.Client.Request;
import SimpleTomcat.Client.Response;

import java.io.*;
import java.net.Socket;

/**
 * Created by 41237 on 2016/7/13.
 */
public class Upload implements Handler
{
    @Override
    public void service(Request request, Response response) throws Exception
    {

        long size = new File(request.getPara("place")).length();
        request.addPara("code", "UP");
        request.addPara("size", String.valueOf(size));
        request.flush();
        //ps already exists in request

        response.readResponse();
        if (response.getPara("code").equals("NO"))
        {
            System.out.println("Error");
        }
        else
        {
            System.out.println("开始上传");
            transFile(request.getPara("place"), size);
            System.out.println("上传完成");
        }
    }

    private void transFile(String filename, long size) throws Exception
    {
        Socket socket = new Socket("127.0.0.1", 8890);
        BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(filename));
        BufferedOutputStream fileOut = new BufferedOutputStream(socket.getOutputStream());

        for (int i = 0; i < size; i++)
        {
            fileOut.write(fileIn.read());
        }

        fileOut.close();
        fileIn.close();
        socket.close();
    }

}
