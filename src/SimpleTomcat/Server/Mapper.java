package SimpleTomcat.Server;

import SimpleTomcat.Server.ServletInstance.Servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 41237 on 2016/7/11.
 */
public class Mapper
{    private static Map<String, Servlet> servletMap = new HashMap<>();

    public static void addPattern(String pattern, Servlet servlet)
    {
        servletMap.put(pattern, servlet);
    }

    public static Servlet get(String method)
    {
        return servletMap.get(method);
    }

    private static boolean mapPattern(String method, String pattern)
    {
        boolean isMap = false;

        int index = pattern.indexOf("*");
        if (index == -1)
        {
            if (method.equals(pattern))
                isMap = true;
        }
        else
        {
            String firstPart = pattern.substring(0, index);
            if (method.startsWith(firstPart))
            {
                if (index == pattern.length() - 1)
                    isMap = true;
                else
                {
                    String lastPart = pattern.substring(index + 1);
                    if (method.endsWith(lastPart))
                        isMap = true;
                }
            }
        }
        return isMap;
    }
}
