package SimpleTomcat.Server.FileManager;

/**
 * Created by 41237 on 2016/7/18.
 */
public class Pair
{
    private volatile long count;
    private volatile String ps;

    public Pair(long count, String ps)
    {
        this.count = count;
        this.ps = ps;
    }

    public long getCount()
    {
        return count;
    }

    /**
     * synchronized
     * @return
     */
    public synchronized long incCount()
    {
        count++;
        return count;
    }

    /**
     * synchronized
     * @param count
     */
    public synchronized void setCount(long count)
    {
        this.count = count;
    }

    /**
     * synchronized
     * @param ps
     */
    public synchronized void setPs(String ps)
    {
        this.ps = ps;
    }

    public String  getPs()
    {
        return ps;
    }


    public String toString()
    {
        return count + " : " + ps;
    }
}