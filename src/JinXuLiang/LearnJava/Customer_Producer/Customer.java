package JinXuLiang.LearnJava.Customer_Producer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by 41237 on 2016/4/23.
 */
public class Customer extends Thread {

    //缓冲区
    private BlockingQueue<Product> blockingQueue;

    //对产品进行操作
    private ConsumerDisplay display;

    public Customer(BlockingQueue blockingQueue, ConsumerDisplay display)
    {
        this.display = display;
        this.blockingQueue = blockingQueue;
    }

    public void run()
    {
        while (true) {
            try {
                Product product = blockingQueue.take();
                display.consumerDisplay(product);
                sleep(product.getPlannedTakenTime());
                product.setFinishTime(System.currentTimeMillis());
//                System.out.println(product.getName()
//                        + " takes " + product.getRealTakenTime()
//                        + " ms to finish."
//                        + "\nit planned to take "
//                        + product.getPlannedTakenTime() + " ms");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
