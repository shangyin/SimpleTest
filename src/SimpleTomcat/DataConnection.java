package SimpleTomcat;

import javax.naming.ldap.SortKey;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by 41237 on 2016/7/31.
 * thread-safe, because only one thread use an address
 * oh no, if a host make two control connections to server?
 * and this will be not thread-safe!
 */

/**
 * 重构：
 * 管理key - value，即ip - sockets
 * sockets使用数据结构保存
 * 使用者直接获得该数据结构
 * 问题：怎么处理关闭的问题
 */

public class DataConnection implements Runnable
{
    private static int port;
    private static ServerSocket server;
    private Map<InetAddress, LinkedList<Socket>> addressToSockets = new HashMap<>();

    public DataConnection(int port)
    {
        this.port = port;
    }

    public static void stop() throws Exception
    {
        server.close();
    }

    public void run()
    {
        try
        {
            server = new ServerSocket(port);
            while (true)
            {
                Socket sck = server.accept();
                InetAddress address = sck.getInetAddress();

                if (!addressToSockets.containsKey(address))
                {
                    LinkedList<Socket> l = new LinkedList<>();
                    l.add(sck);
                    addressToSockets.put(address, l);
                }
                else
                {
                    addressToSockets.get(address).add(sck);
                }

            }
        }
        catch (SocketException e)
        {
            /* come here when stop() is called */
            try
            {
                closeAll();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public LinkedList<Socket> get(InetAddress address) throws Exception
    {
        LinkedList<Socket> ret;
        do
        {
            ret = addressToSockets.get(address);
        }
        while (ret == null);
        return ret;
    }

    public void release(InetAddress address) throws Exception
    {
        if (addressToSockets.containsKey(address))
        {
            LinkedList<Socket> rm = addressToSockets.remove(address);
            for (Socket socket : rm)
            {
               socket.close();
            }
        }
        else
        {
            //should throw exception, in case the error usage
        }
    }

    /* used for exist the thread, clean up all sockets */
    private void closeAll() throws IOException
    {
        for (LinkedList<Socket> list : addressToSockets.values())
        {
            for (Socket socket : list)
            {
                socket.close();
            }
        }
    }



    public static void main(String[] args) throws Exception
    {
        DataConnection data = new DataConnection(8890);
        Thread t = new Thread(data);
        t.start();
        Thread.sleep(1000);
        data.stop();
    }
}
