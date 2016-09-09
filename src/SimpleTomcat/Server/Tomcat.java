package SimpleTomcat.Server;

import SimpleTomcat.DataConnection;
import SimpleTomcat.Server.FileManager.FileManager;
import SimpleTomcat.Server.ServletInstance.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/11.
 */
public class Tomcat
{
    private ServerSocket server;

    public void init()
    {
        Mapper.addPattern("DOWN", new Download());
        Mapper.addPattern("UP", new Upload());
        Mapper.addPattern("SHUTDOWN", new Shutdown());
        Mapper.addPattern("SHOW", new ShowDetail());
        Mapper.addPattern("LIST", new ListDir());

        FileManager.add(FileManager.conUrl, FileManager.homeDir);
    }

    public Tomcat(int port) throws Exception
    {
        server = new ServerSocket(port);
    }

    public void start() throws Exception
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //启动数据连接socket线程
        DataConnection data = new DataConnection(8890);
        executor.execute(data);

        //处理客户请求
        while (true)
            executor.execute(new Handler(server.accept()));
    }

    class Handler implements Runnable
    {
        private Socket client;

        public Handler(Socket client)
        {
            this.client = client;
        }

        public void run()
        {
            try
            {
                //生成Request和Response
                Request request = new Request(client.getInputStream(),
                        client.getInetAddress());
                Response response = new Response(client.getOutputStream());
                System.out.println("new request: \"" + request.getParameter(CODE) + "\"");


                //寻找对应servlet，并启动
                Servlet servlet = Mapper.get(request.getParameter(CODE));
                if (servlet != null)
                {
                    System.out.println("invoke " + request.getParameter(CODE));
                    servlet.service(request, response);
                }
                else
                {
                    response.addPara(CODE, ANSWER_DENY);
                    response.flush();
                }

                //关闭命令传输的socket
                client.close();
                //关闭数据传输的socket
                Servlet.dataConnection.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
