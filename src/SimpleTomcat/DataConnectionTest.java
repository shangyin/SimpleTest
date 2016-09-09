package SimpleTomcat;

import SimpleTomcat.DataConnection;
import org.junit.*;


import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by 41237 on 2016/8/5.
 */
public class DataConnectionTest
{
    public static DataConnection sockets;
    public static Thread t;
    public static String ip;

    @BeforeClass
    public static void setup() throws Exception
    {
        sockets = new DataConnection(8890);
        t = new Thread(sockets);
        t.start();
        Thread.sleep(1000);
        ip = new Socket("localhost", 8890).getInetAddress().getHostAddress();
    }


    @AfterClass
    public static void tearDown() throws Exception
    {
        t.interrupt();
    }


    @Test
    public void stop() throws Exception
    {

    }

    @Test
    public void get() throws Exception
    {
    }

    @Test
    public void release() throws Exception
    {

    }

    @Test
    public void close() throws Exception
    {

    }

}