package SimpleTomcat.Server.FileManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 41237 on 2016/7/18.
 */
public class FileCRUD
{
    private Map<String, Pair> info;
    public String homeDir;

    public FileCRUD(Map<String, Pair> info, String homeDir)
    {
        this.info = info;
        this.homeDir = homeDir;
    }

    /**
     * thread-safe
     * @param name
     * @param ps
     * @return
     */
    public boolean input(String name, String ps)
    {
        name = homeDir.concat(name);
        if (info.containsKey(name)) {
            return false;
        } else {
            info.put(name, new Pair(0, ps));
            return true;
        }
    }

    public boolean remove(String name)
    {
        return info.remove(homeDir.concat(name)) != null;
    }

    public boolean resetCount(String name)
    {
        Pair p = info.get(homeDir.concat(name));

        if (p != null)
        {
            p.setCount(0);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean modify(String name, String ps)
    {
        Pair p = info.get(homeDir.concat(name));

        if (p != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setCount(String name, long count)
    {
        Pair p = info.get(homeDir.concat(name));

        if (p != null)
        {
            p.setCount(count);
            return true;
        }
        else
        {
            return false;
        }
    }

    public String find(String name)
    {
        Pair p = info.get(homeDir.concat(name));

        if (p != null)
        {
           return name + " : " + p;
        }
        else
        {
            return "file not found";
        }
    }

    public List<String> find(int top)
    {
        return  info
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((x,y) -> (int)(y.getCount()-x.getCount())))
                .limit(top)
                .map(x -> x.getKey() + " : " + x.getValue())
                .collect(Collectors.toList());
    }

    public List<String> find()
    {
        return info
                .entrySet()
                .stream()
                .peek(x -> System.out.println(x.getKey()))
                .map(x -> x.getKey() + " : " + x.getValue())
                .collect(Collectors.toList());
    }

    /**
     * synchronized
     * @param name
     * @return
     */
    public long increaseAndGetCount(String name)
    {
        Pair p = info.get(homeDir.concat(name));

        if (p != null)
        {
            return p.incCount();
        }
        else
        {
            return -1;
        }
    }

    public int size()
    {
        return info.size();
    }

    public boolean exist(String name)
    {
        return info.containsKey(homeDir.concat(name));
    }
}
