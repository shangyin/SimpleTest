package SimpleTomcat.Client;

import SimpleTomcat.Client.HandlerInstance.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 41237 on 2016/7/11.
 */
public class Main
{
    private static void init()
    {
        Mapper.addPattern("down", new Download());
        Mapper.addPattern("show", new ShowDetail());
        Mapper.addPattern("shutdown", new ShutDown());
        Mapper.addPattern("list", new ListDir());
        Mapper.addPattern("up", new Upload());
    }

    public static void main(String[] args) throws Exception
    {

        init();

        String cmd;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (!(cmd = input.readLine()).equals("exit"))
        {
            Socket socket = new Socket("127.0.0.1", 8889);

            cmd = cmd.trim();
            String word = cmd.split("\\s")[0];
            Handler h = Mapper.getPattern(word);
            Request request = new Request(cmd, socket.getOutputStream());
            Response response = new Response(socket.getInputStream());
            if (h != null)
            {
                h.service(request, response);
            }
            else
            {
                System.out.println("command not found");
            }
            socket.close();
        }


//        writer.println("SHOW hell");
//        writer.flush();
//        reader.lines().forEach(System.out::println);
//        File file = new File("d:/这个我的第一个上传文件.txt");
//        writer.println("UP robin.txt?ps=ILoveYou&size=" + file.length());
//        writer.flush();
//        System.out.println(reader.readLine());
//
//        Socket up = new Socket("127.0.0.1", 8890);
//        BufferedOutputStream out = new BufferedOutputStream(up.getOutputStream());
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//        while (in.available() > 0)
//        {
//            out.write(in.read());
//        }
//        out.flush();
//        up.close();
//        System.out.println(reader.readLine());
//        socket.close();
    }

}
