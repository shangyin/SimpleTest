package JinXuLiang.LearnJava.Customer_Producer.Temp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 41237 on 2016/4/23.
 */
public class TempCode {
    public static void main(String[] args)
    {
//        WareHouse wareHouse = new BlockingQueueWareHouse(10);
        WareHouse wareHouse = new OriginalWareHouse(5);
        Producer producerOne = new Producer(wareHouse, "one");
        Producer producerTwo = new Producer(wareHouse, "two");
        Producer producerThree = new Producer(wareHouse, "three");
        Consumer consumerOne = new Consumer(wareHouse, "one");
        Consumer consumerTwo = new Consumer(wareHouse, "two");
        Consumer consumerThree = new Consumer(wareHouse, "three");
        producerOne.start();
        producerTwo.start();
        producerThree.start();
        consumerOne.start();
        consumerTwo.start();
        consumerThree.start();
    }
}

class Product
{
    private String name;    //产品名

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Product\t-\t" + name;
    }
}

interface WareHouse
{
    Product getProduct();
    void storageProduct(Product product);
}

class OriginalWareHouse implements WareHouse
{
    private int CAPACITY;
    private Product[] products;

    private volatile int font = 0;
    private volatile int rear = 0;
    private volatile int number = 0;


    public OriginalWareHouse(int capacity)
    {
        CAPACITY = capacity;
        products = new Product[CAPACITY];
    }

    public synchronized Product getProduct()
    {
        while (font == rear)
        {
            try {
                wait();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        rear = (rear + 1) % CAPACITY;
        notifyAll();
//        System.out.println(products[rear].toString() + " is out");
//        System.out.println("font is " + font + "\t" + "rear is " + rear);
        return products[rear];
    }

    public synchronized void storageProduct(Product product)
    {
        while ((font+1)%CAPACITY == rear)
        {
            try {
                wait();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        product.setName("no. " + (number++));
        font = (font + 1) % CAPACITY;
        products[font] = product;
//        System.out.println(product.toString() + " is in");
//        System.out.println("font is " + font + "\t" + "rear is " + rear);
        notifyAll();
    }
}

class Producer extends Thread
{
    private WareHouse wareHouse;
    private static AtomicInteger productNumber = new AtomicInteger(0);

    public Producer(WareHouse wareHouse, String name)
    {
        super(name);
        this.wareHouse = wareHouse;
    }

    public void run()
    {
        while (true)
        {
            Product product = new Product();
            product.setName("no. " + productNumber.getAndIncrement());
            System.out.println("Producer " + Thread.currentThread().getName() + "\t" + product);
            wareHouse.storageProduct(product);
            try
            {
                sleep(ThreadLocalRandom.current().nextInt(1000));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread
{
    private WareHouse wareHouse;

    public Consumer(WareHouse wareHouse, String name)
    {
        super(name);
        this.wareHouse = wareHouse;
    }

    public void run()
    {
        while (true)
        {
            Product product = wareHouse.getProduct();
            System.out.println("Consumer " + Thread.currentThread().getName() + "\t" + product);
            try
            {
                sleep(ThreadLocalRandom.current().nextInt(1000));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

class BlockingQueueWareHouse implements WareHouse
{
    private BlockingQueue<Product> queue;

    public BlockingQueueWareHouse()
    {
        queue = new LinkedBlockingQueue<>();
    }

    public BlockingQueueWareHouse(int CAPACITY)
    {
        queue = new LinkedBlockingDeque<>(CAPACITY);
    }

    public Product getProduct()
    {
        try{
        return queue.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storageProduct(Product product)
    {
        try {
            queue.put(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}