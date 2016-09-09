package JinXuLiang.LearnJava.CourseDesign;

import JinXuLiang.LearnJava.CourseDesign.ServerCmd.CmdHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 41237 on 2016/5/5.
 */
public class Server implements Runnable {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintStream out;
    private CmdHandler handler;

    public Server(Socket clientSocket) throws Exception {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintStream(clientSocket.getOutputStream());
//        handler = new CmdHandler(clientSocket);
    }

    public void run() {
        String temp = "";

        try {
            while (true) {
//                temp = getRequest();
//                temp = handler.parseCmd(temp);
//                System.out.print(temp);
                sendResponse(getRequest());
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("connection abort");
        }
    }

    public String getRequest() throws Exception {
        return in.readLine();
    }

    public void sendResponse(String response) throws Exception {
        out.println(response);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("server starts");

        //服务器开始监听
        while (true) {
            pool.execute(new Server(serverSocket.accept()));
            System.out.println("a client connects");
        }
    }
}
