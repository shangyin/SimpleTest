package Java.Core;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 41237 on 2016/3/31.
 */
public class StacmTraeTest {
    public static int factoial(int n) {
        System.out.println("factorial(" + n + "):");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames) {
            System.out.println(f);
        }
        int r;
        if (n <= 1) r = 1;
        else r = n * factoial(n - 1);
        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n:");
        int n = in.nextInt();
        factoial(n);
    }
}
