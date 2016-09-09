package SimpleTomcat.Server.FileManager;


import java.io.File;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 41237 on 2016/7/18.
 */
public class FileInit
{
    private Map<String, Pair> info;

    private Connection con;
    private Statement stm;
    private ResultSet res;

    private String conUrl;
    public String homeDir;

    public FileInit(Map<String, Pair> info, String conUri, String homeDir)
    {
        this.conUrl = conUri;
        this.homeDir = homeDir;
        this.info = info;

        readFromDb();
        scanNewFile();
    }


    private void connect2Db() throws Exception
    {
        con = DriverManager.getConnection(conUrl);
        stm = con.createStatement();
    }

    private void closeCon() throws Exception
    {
        res.close();
        stm.close();
        con.close();
    }

    public void scanNewFile()
    {
        File file = new File(homeDir);
        recursion(file);
    }

    private void recursion(File dir)
    {
        for (File f : dir.listFiles())
        {
            if (f.isFile())
            {
                String abs = f.getAbsolutePath();
                if (info.get(abs) == null)
                {
                    Pair p = new Pair(0, "new");
                    info.put(abs, p);
                }
            }
            else if (f.isDirectory())
            {
                recursion(f);
            }
        }
    }

    /**
     * 单独的方法，不被类的内部所调用
     */
    public synchronized void write2Db()
    {
        try
        {
            connect2Db();

            //empty the list at first
            stm.execute("delete from main");

            List<String> list = new LinkedList<>();
            for (Map.Entry<String, Pair> e : info.entrySet())
            {
                String name = e.getKey();
                name = "'".concat(name).concat("'");

                Pair p = e.getValue();
                String count = String.valueOf(p.getCount());
                String ps = "'".concat(p.getPs()).concat("'");

                String s = Stream.of(name, count, ps)
                        .collect(Collectors.joining(",", "(", ")"));
                list.add(s);
            }

            String values = list.stream()
                    .collect(Collectors.joining(",", "", ""));

            stm.execute("insert " +
                    "main(name, down_count, ps) " +
                    "values" + values);

            closeCon();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void readFromDb()
    {
        try
        {
            connect2Db();
            res = stm.executeQuery("select * from main");
            while (res.next())
            {

                StringBuffer name = new StringBuffer();
                StringBuffer ps = new StringBuffer();

                Reader reader = res.getCharacterStream(1);
                while (reader.ready())
                {
                    name.append(((char) reader.read()));
                }

                //如果文件系统中不存在，那就算了
                if (!new File(name.toString().trim()).exists())
                    continue;

                reader = res.getCharacterStream(3);
                while (reader.ready())
                {
                    ps.append(((char) reader.read()));
                }

                info.put(name.toString().trim(),
                        new Pair(res.getInt(2), ps.toString().trim()));
            }
            closeCon();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args)
//    {
//        scanNewFile();
//        readFromDb();
//        System.out.println(tt);
//        for (Map.Entry e : downloadCount.entrySet())
//        {
//            System.out.println(((String) e.getKey()).trim() + " : " + e.getValue());
//        }
//        System.out.println("\n=========================\n");
//        for (Map.Entry e : filePS.entrySet())
//        {
//            System.out.println(((String) e.getKey()).trim() + " : " + e.getValue());
//        }

//    }
}

/**
 * Created by 41237 on 2016/7/12.
 */



