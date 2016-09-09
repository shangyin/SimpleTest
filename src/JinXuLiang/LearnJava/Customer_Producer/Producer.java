package JinXuLiang.LearnJava.Customer_Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 41237 on 2016/4/23.
 */
public class Producer extends Thread{

    private AtomicInteger productNumber = new AtomicInteger(0);

    private BlockingQueue<Product> blockingQueue;
    private ProducerDisplay display;    //用于输出产品

    //生产产品的时间间隔范围
    private long intervalFrom = 3000;
    private long intervalTo = 5000;

    public Producer(BlockingQueue blockingQueue, ProducerDisplay display)
    {
        this.display = display;
        this.blockingQueue = blockingQueue;
    }

    public Producer(BlockingQueue blockingQueue, ProducerDisplay display,
                    long intervalFrom, long intervalTo)
    {
        this(blockingQueue, display);
        if (intervalFrom > 0 && intervalTo >= intervalFrom)
        {
            this.intervalFrom = intervalFrom;
            this.intervalTo = intervalTo;
        }
    }

    @Override
    public void run() {
        while (true) {
            try
            {
                Product product = new BankClient("client No. "
                        + productNumber.getAndIncrement());
                blockingQueue.put(product);
                display.producerDisplay(product);
                sleep(ThreadLocalRandom.current().nextLong(intervalFrom, intervalTo));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
