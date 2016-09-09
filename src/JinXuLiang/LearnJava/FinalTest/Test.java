package JinXuLiang.LearnJava.FinalTest;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by 41237 on 2016/5/3.
 */
public class Test {

    private static List<File> list;

    public static void main(String[] args) throws Exception {
// ==========================多线程与多线程比较计算数列
//        FutureTask<Long> taskOne = new FutureTask<Long>(new Count(0, 10001));
//        FutureTask<Long> taskTwo = new FutureTask<Long>(new Count(10001, 20001));
//        FutureTask<Long> taskThree = new FutureTask<Long>(new Count(20001, 30001));
//        ExecutorService executor = Executors.newCachedThreadPool();
//        long temp = 0;
//        long start = System.currentTimeMillis();
//        executor.submit(taskOne);
//        executor.submit(taskTwo);
//        executor.submit(taskThree);
//        executor.shutdown();
//        temp += taskOne.get();
//        temp += taskTwo.get();
//        temp += taskThree.get();
//        temp = LongStream.range(0, 30001).sum();
//        long end = System.currentTimeMillis();
//        System.out.println(temp);
//        System.out.println("takes " + (end - start) + " ms");


        list = getFiles("d:\\CODE", 4096);
        FutureTask<List<File>> sort = new FutureTask<>(new Sort());
        FutureTask<Map<String, List<File>>> group = new FutureTask<>(new Group());
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(sort);
        pool.submit(group);
        sort.get().stream().forEach(System.out::println);
        System.out.println("=======================================================================");
        group.get().entrySet().forEach(System.out::println);

    }

    public static ArrayList<File> getFiles(String directory, int fileSize) {
        ArrayList<File> ret = new ArrayList<>();
        ArrayList<File> forDir = new ArrayList<>();
        File[] sub;
        FileFilter filter = x -> x.isDirectory() || x.length() > fileSize;

        forDir.add(new File(directory));
        while (!forDir.isEmpty()) {
            sub = forDir.remove(forDir.size()-1).listFiles(filter);
            if (sub == null) {
                continue;
            } else {
                for (File f : sub) {
                    if (f.isDirectory()) {
                        forDir.add(f);
                    } else if (f.isFile()) {
                        ret.add(f);
                    }
                }
            }
        }
        return ret;
    }

    static class Sort implements Callable<List<File>> {
        public List<File> sortFile(List<File> files, boolean desc) {
            List<File> ret = new ArrayList<>();
            ret.addAll(files);
            if (desc)
                ret.sort((x, y) -> 0 - x.getName().compareTo(y.getName()));
            else
                ret.sort((x, y) -> x.getName().compareTo(y.getName()));
            return ret;
        }

        public List<File> call()
        {
              return sortFile(list, true);
        }
    }

    static class Group implements Callable<Map<String, List<File>>> {
        public Map<String, List<File>> groupFile(List<File> list) {
            return list.stream()
                    .collect(Collectors.groupingBy(x -> {
                        if (x.getName().contains(".")) return x.getName().substring(x.getName().lastIndexOf('.'));
                        else return x.getName();
                    }));
        }

        public Map<String, List<File>> call() {
            return groupFile(list);
        }
    }
}

class Count implements Callable<Long> {
    private int from;
    private int to;

    public Count(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Long call() {
        return LongStream.range(from, to).sum();
    }
}

