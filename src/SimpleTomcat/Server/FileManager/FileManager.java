package SimpleTomcat.Server.FileManager;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 41237 on 2016/7/18.
 */

/**
 * 由方法参数可以知道，
 * FileManager管理指定homeDir和conUrl对应的FileInit和FileCRUD
 */

public class FileManager
{
    private static FileInit init;
    private static FileCRUD crud;
    public static String homeDir = "d:\\ftp\\";
    public static String conUrl = "jdbc:sqlserver://localhost;database=java_ftp;integratedSecurity=true;";

    public static void add(String conUrl, String homeDir)
    {
        Map<String, Pair> m = new ConcurrentHashMap<>();
        init = new FileInit(m ,conUrl, homeDir);
        crud = new FileCRUD(m, homeDir);
    }

    public static FileInit getInit()
    {
        return init;
    }

    public static FileCRUD getCrud()
    {
        return crud;
    }
}