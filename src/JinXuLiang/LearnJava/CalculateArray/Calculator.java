package JinXuLiang.LearnJava.CalculateArray;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by 41237 on 2016/4/28.
 */
public class Calculator {
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        long ret = IntStream.range(0, 1000000000)
                            .parallel()
                            .sum();
        long end = System.currentTimeMillis();
        System.out.println(ret);
        System.out.println("takes " + (end - start) + "ms");
        }
}
