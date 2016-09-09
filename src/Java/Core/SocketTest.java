package Java.Core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 41237 on 2016/4/19.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException{
        try (Socket s = new Socket("localhost", 8189)) {
//            从socket获得的基本stream
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
//            包装stream
            PrintWriter printOut = new PrintWriter(out, true);
            Scanner input = new Scanner(in);
//            系统输入
            Scanner systemIn = new Scanner(System.in);

            String temp = "";

            if (input.hasNextLine()) {
                temp = input.nextLine();
                System.out.println(temp);
            }

            while (systemIn.hasNextLine()) {
                temp = systemIn.nextLine();
                printOut.println(temp);
                String line = input.nextLine();
                System.out.println(line);
            }
        }
    }
}
