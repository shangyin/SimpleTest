package JinXuLiang.LearnJava.FindBigFile;


import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 41237 on 2016/5/3.
 */

//这个程序是使用线程更加慢，
// 当屏幕输出内容少时差距大，内容多时差距小，应该是多线程操作过于复杂


public class Finder {
    public static void main(String[] args) throws Exception {
        BlockingQueue<File> blockingQueue = new LinkedBlockingQueue<>();
        LinkedList<File> innerList = new LinkedList<>();
        innerList.addFirst(new File("d:\\"));
        ProduceFile producer = new ProduceFile(innerList, blockingQueue,
                (x) -> x.isDirectory() || x.length() > 0 );
        ConsumeFile conOne = new ConsumeFile(blockingQueue);
        ConsumeFile conTwo = new ConsumeFile(blockingQueue);

        long start = System.currentTimeMillis();
        producer.start();
        conOne.start();
        conTwo.start();
        conOne.join();
        conTwo.join();
//        printArray(new File("d:\\").listFiles());
        long end = System.currentTimeMillis();
        System.out.println("takes " + (end - start)+ " ms");
    }

    public static void printArray(File[] files) {
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
                printArray(f.listFiles());
            } else if (f.length() > 0) {
                System.out.println(f);
            }
        }
    }
}


//这个用于消费文件
class ConsumeFile extends Thread {
    private BlockingQueue<File> blockingQueue;

    public ConsumeFile(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        File file = null;
        while (true) {
            try {
                file = blockingQueue.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (file.getPath().equals("END"))  {
                return;
            } else {
                System.out.println(file);
            }
        }
    }
}

//这个类用于生产文件
class ProduceFile extends Thread {

//    用于保存待检查的File目录对象，它们产生的文件对象应该送入BlockingQueue
    private LinkedList<File> innerList;
    private BlockingQueue<File> blockingQueue;
    private FileFilter filter;


    public ProduceFile(LinkedList<File> innerList, BlockingQueue blockingQueue, FileFilter filter)
    {
        super();
        this.innerList = innerList;
        this.blockingQueue = blockingQueue;
        this.filter = filter;
    }

    public void run() {
        while (!innerList.isEmpty()) {
            File file = innerList.removeLast();
            if (file.isFile()) {
                try {
                    blockingQueue.put(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (file.isDirectory()) {
                if (file.listFiles(filter) == null) continue;
                for (File f : file.listFiles(filter)) {
                    if (f.isDirectory()) {
                        innerList.addFirst(f);
                    } else if (f.isFile()) {
                        try {
                            blockingQueue.put(f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        try {
            blockingQueue.put(new File("END"));
            blockingQueue.put(new File("END"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("it's me");
        }
    }
}
