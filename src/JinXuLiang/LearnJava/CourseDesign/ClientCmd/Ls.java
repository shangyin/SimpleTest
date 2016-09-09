package JinXuLiang.LearnJava.CourseDesign.ClientCmd;

import JinXuLiang.LearnJava.CourseDesign.ServerCmd.CmdHandler;
import com.sun.org.apache.xml.internal.security.utils.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by 41237 on 2016/6/19.
 */
public class Ls extends Cmd
{
    public static void main(String[] args) throws Exception
    {
        Thread t = new Thread(new MiniClient());
        t.setName("client");
        Thread tt = new Thread(new MiniServer());
        tt.setName("server");

        tt.start();
        t.start();
    }



    @Override
    public void execute(InputStream in, OutputStream out) throws IOException
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
        writer.println("ls");
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        System.out.println(reader.readLine());
        while (reader.ready())
        {
           System.out.println(reader.readLine());
        }
    }
}
class MiniClient implements Runnable
{
    public void run()
    {
        try
        {
            Socket socket = new Socket("localhost", 8888);
            System.out.println("client" + socket);
            Ls ls = new Ls();
            ls.execute(socket.getInputStream(), socket.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class MiniServer implements Runnable
{
    public void run()
    {
        try
        {
            System.out.println("server");
            ServerSocket server = new ServerSocket(8888);
            Socket client = server.accept();
            System.out.println("accept " + client);
            CmdHandler handler = new CmdHandler();
            handler.parse(client.getInputStream(), client.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

