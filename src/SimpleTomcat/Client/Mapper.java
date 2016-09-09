package SimpleTomcat.Client;

import SimpleTomcat.Client.HandlerInstance.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 41237 on 2016/7/12.
 */
public class Mapper
{
    private static HashMap<String, Handler> map = new HashMap<>();

    public static boolean addPattern(String cmd, Handler handler)
    {
        if (!map.keySet().contains(cmd))
        {
            map.put(cmd, handler);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Handler getPattern(String cmd)
    {
        if (map.keySet().contains(cmd))
        {
            return map.get(cmd);
        }
        else
        {
            return null;
        }
    }



}
