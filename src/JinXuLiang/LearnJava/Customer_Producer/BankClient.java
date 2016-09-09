package JinXuLiang.LearnJava.Customer_Producer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 41237 on 2016/4/26.
 */
public class BankClient implements Product
{
    private long realTakenTime;
    private long createTime;
    private long finishTime;

    private long plannedTakenFrom = 3000;
    private long plannedTakenTo = 5000;
    private long plannedTakenTime;

    private String name;

    public BankClient(String name, long plannedTakenFrom, long plannedTakenTo)
    {
        this(name);
        if (plannedTakenFrom > 0 && plannedTakenTo >= plannedTakenFrom)
        {
            this.plannedTakenFrom = plannedTakenFrom;
            this.plannedTakenTo = plannedTakenTo;
            this.plannedTakenTime =
                    ThreadLocalRandom.current().nextLong(plannedTakenFrom, plannedTakenTo);
        }
    }

    public BankClient(String name)
    {
        setName(name);
        plannedTakenTime = ThreadLocalRandom.current().nextLong(plannedTakenFrom, plannedTakenTo);
        setCreateTime(System.currentTimeMillis());
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
        this.realTakenTime = this.finishTime - this.createTime;
    }

    @Override
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public long getPlannedTakenTime() {
        return plannedTakenTime;
    }

    @Override
    public long getRealTakenTime() {
        return realTakenTime;
    }
}
