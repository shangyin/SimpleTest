package Java.Core;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 41237 on 2016/4/19.
 */
public class InetAddressTest {
    public static void main(String[] args) throws Exception {
        String host = "";
        Scanner in = new Scanner(System.in);
        host = in.nextLine();
        InetAddress[] addresses = InetAddress.getAllByName(host);
        Arrays.stream(addresses).forEach(System.out::println);
    }
}
