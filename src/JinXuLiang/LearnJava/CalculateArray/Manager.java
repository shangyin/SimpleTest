package JinXuLiang.LearnJava.CalculateArray;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 41237 on 2016/4/28.
 */
public class Manager {
    public static void main(String[] args)
    {
        CountDownLatch allArrive = new CountDownLatch(10);
        for (int i = 0; i < 10; i++)
        {
            new Thread(new LazyManager(allArrive)).start();
        }
        try {
        allArrive.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("start the meeting!");
    }

}

class LazyManager implements Runnable
    {
        private CountDownLatch allArrive;
        private static ThreadLocal<Integer> temp = new ThreadLocal().withInitial(() -> 1 );


        public LazyManager(CountDownLatch allArrive)
        {
            this.allArrive = allArrive;
        }

        public void run() {
            try {
                Thread.currentThread().sleep(ThreadLocalRandom.current().nextInt(5000));
                System.out.println("lazy manager " + temp.get() + " arrive");
                allArrive.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
