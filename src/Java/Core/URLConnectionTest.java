package Java.Core;

import com.sun.javafx.fxml.builder.URLBuilder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 41237 on 2016/4/20.
 */
public class URLConnectionTest {
    public static void main(String[] args) throws IOException {
        String urlName;
        if (args.length > 0) urlName = args[0];
        else urlName = "http://horstmann.com";

        URL url = new URL(urlName);
        URLConnection connection = url.openConnection();

        if (args.length > 2) {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            String encoding = base64Encode(input);
            connection.setRequestProperty("Authorization", "basic " + encoding);
        }

        connection.connect();

        Map<String, List<String>> headers = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            String key = e.getKey();
            for (String value :e.getValue()) {
                System.out.println(key + ":" + value);
            }
        }

//        print convenience functinos
        System.out.println("=================");
        System.out.println("getContentType: " + connection.getContentType());
        System.out.println("getContentLength: " + connection.getContentLength());
        System.out.println("getContentEncoding: " + connection.getContentEncoding());
        System.out.println("getDate: " + connection.getDate());
        System.out.println("getExpiration: " + connection.getExpiration());
        System.out.println("getLastModifed: " + connection.getLastModified());
        System.out.println("==================");

        Scanner in = new Scanner(connection.getInputStream());

        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }

    public static String base64Encode(String s) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(bOut);
        try {
            out.write(s.getBytes());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bOut.toString();
    }
}

class Base64OutputStream extends FilterOutputStream
{
    private static char[] toBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

    private int col = 0;
    private int i = 0;
    private int[] inbuf = new int[3];

    /**
     * Constructs the stream filter.
     * @param out the stream to filter
     */
    public Base64OutputStream(OutputStream out)
    {
        super(out);
    }

    public void write(int c) throws IOException
    {
        inbuf[i] = c;
        i++;
        if (i == 3)
        {
            if (col >= 76)
            {
                super.write('\n');
                col = 0;
            }
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[((inbuf[0] & 0x03) << 4) | ((inbuf[1] & 0xF0) >> 4)]);
            super.write(toBase64[((inbuf[1] & 0x0F) << 2) | ((inbuf[2] & 0xC0) >> 6)]);
            super.write(toBase64[inbuf[2] & 0x3F]);
            col += 4;
            i = 0;
        }
    }

    public void flush() throws IOException
    {
        if (i > 0 && col >= 76)
        {
            super.write('\n');
            col = 0;
        }
        if (i == 1)
        {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[(inbuf[0] & 0x03) << 4]);
            super.write('=');
            super.write('=');
        }
        else if (i == 2)
        {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[((inbuf[0] & 0x03) << 4) | ((inbuf[1] & 0xF0) >> 4)]);
            super.write(toBase64[(inbuf[1] & 0x0F) << 2]);
            super.write('=');
        }
    }
}

