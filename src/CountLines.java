import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by 41237 on 2016/7/24.
 */
public class CountLines
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            System.out.println("dir:");
            String filename = in.readLine();
            if (filename.equals("exit"))
                break;

            File file = new File(filename);
            System.out.println(count(file));
        }
    }

    static long count(File file) throws Exception
    {
        long ret = 0;
        if (file.isFile())
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ret += reader.lines().count();
            reader.close();
        }
        else if (file.isDirectory())
        {
            for (File f : file.listFiles())
                ret += count(f);
        }
        return ret;
    }
}
