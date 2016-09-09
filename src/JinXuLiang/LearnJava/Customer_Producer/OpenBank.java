package JinXuLiang.LearnJava.Customer_Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 41237 on 2016/4/26.
 */
public class OpenBank {

    public static void main(String[] args)
    {
        ConsumerDisplay consumerDisplay = product -> System.out.println(product.getName()
                                                        + " go to " + Thread.currentThread().getName());
        ProducerDisplay producerDisplay = product -> {};

        BlockingQueue<Product> blockingQueue = new LinkedBlockingQueue<>();
        Thread consumerOne = new Thread(new Customer(blockingQueue, consumerDisplay));
        Thread consumerTwo = new Thread(new Customer(blockingQueue, consumerDisplay));
        Thread consumerThree = new Thread(new Customer(blockingQueue, consumerDisplay));
        Thread producer = new Thread(new Producer(blockingQueue, producerDisplay));
        consumerOne.start();
        consumerThree.start();
        consumerTwo.start();
        producer.start();
    }
}
