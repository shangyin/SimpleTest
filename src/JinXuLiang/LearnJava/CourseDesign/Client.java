package JinXuLiang.LearnJava.CourseDesign;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 41237 on 2016/5/10.
 */
public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;

    public Client(int port) throws Exception {
        socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }

    public void sendRequest(String request) throws Exception {
        out.println(request);

    }

    public String getResponse() throws Exception {
        return in.readLine();
    }

//    public static void main(String[] args) throws Exception {
//        Main client = new Main(8888);
//        Scanner s = new Scanner(System.in);
//        String temp  = "";
//        while (true) {
//            temp = s.nextLine();
//            if (temp.equals("exit"))
//                break;
//            client.sendRequest(temp);
//            System.out.println(client.getResponse());
//        }
//    }
}
