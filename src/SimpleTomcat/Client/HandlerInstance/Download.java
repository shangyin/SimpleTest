package SimpleTomcat.Client.HandlerInstance;

import SimpleTomcat.Client.Request;
import SimpleTomcat.Client.Response;

import java.io.*;
import java.net.Socket;

/**
 * Created by 41237 on 2016/7/12.
 */
public class Download implements Handler
{
    @Override
    public void service(Request request, Response response) throws Exception
    {
        //移出服务器没有必要知道的信息
        String fileDir = request.removePara("place");

        //请求下载
        request.addPara("code", "DOWN");
        request.flush();

        response.readResponse();
        if (response.getPara("code").equals("NO"))
        {
            System.out.println("Error");
        }
        else
        {
            long size = Long.valueOf(response.getPara("size"));
            System.out.println("开始下载");
            transFile(fileDir, size);
            System.out.println("完成下载");
        }
    }

    private void transFile(String filename, long size) throws Exception
    {
        Socket s = new Socket("127.0.0.1", 8890);
        BufferedInputStream fileIn = new BufferedInputStream(s.getInputStream());
        BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(filename));

        for (int i = 0; i < size; i++)
        {
            fileOut.write(fileIn.read());
        }

        fileOut.close();
        fileIn.close();
        s.close();
    }
}
