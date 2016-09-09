package debug;

/**
 * Created by 41237 on 2016/7/18.
 */
public class Bug
{
    public static void debug(String s)
    {
        System.out.println(s);
        System.exit(-1);
    }
}
