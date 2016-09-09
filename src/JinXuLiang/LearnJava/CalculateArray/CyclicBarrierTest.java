package JinXuLiang.LearnJava.CalculateArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by 41237 on 2016/4/28.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws Exception {

        int[][] cal = new int[10000][10000];
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                cal[i][j] = 2;
            }
        }

        AtomicInteger line = new AtomicInteger(0);
        CyclicBarrier barrier = new CyclicBarrier(2,
                () -> System.out.println("finish a barrier " + line.get()));
        AtomicInteger acc = new AtomicInteger(0);

        Thread[] myThread = new Thread[2];
        for (int i = 0; i < 2; i++)
        {
            myThread[i] = new Thread(new Worker(cal, barrier, acc, line));
            myThread[i].start();
        }
        for (int i = 0; i < 2; i++)
        {
            myThread[i].join();
        }
        System.out.println("done! " + acc.get());
    }


}

class Worker implements Runnable
{
    private int[][] toSum;
    private CyclicBarrier barrier;
    private AtomicInteger acc;
    private AtomicInteger line;

    public Worker(int[][] toSum, CyclicBarrier barrier,
                  AtomicInteger acc, AtomicInteger line)
    {
        this.toSum = toSum;
        this.barrier = barrier;
        this.acc = acc;
        this.line = line;
    }

    public void run()
    {
        while (true) {
            acc.addAndGet(Arrays.stream(toSum[line.getAndIncrement()]).sum());
            if (line.get() < 9999) {
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }
}