package JinXuLiang.LearnJava.Customer_Producer;

/**
 * Created by 41237 on 2016/4/26.
 */
public interface Product
{
    public long getPlannedTakenTime();

    public void setCreateTime(long createTime);

    public void setFinishTime(long finishTime);

    public long getRealTakenTime();

    public String getName();
    public void setName(String name);
}
