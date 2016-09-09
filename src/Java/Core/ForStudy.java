package Java.Core;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by 41237 on 2016/4/5.
 */
public class ForStudy {

    public static Map<Integer, Integer> map = new HashMap<>();
    public static Map<Set, Integer> map2 = new HashMap<>();

    public static void main(String[] args) throws Exception {
//        =========================斐波那契数列=========================================
//        map.put(1,1);
//        map.put(0,0);
//        map.put(2,2);
//        Func[] temp = new Func[] {ForStudy::Fibonacci, ForStudy::slow, ForStudy::fast};
//        for (Func TempCode: temp) {
//            long start = System.currentTimeMillis();
//            long ret = TempCode.func(40);
//            long end = System.currentTimeMillis();
//            System.out.println("result is: " + ret);
//            System.out.println("cost: " + (end - start) + "ms");
//        }

//        ======================================杨辉三角=======================================
//        display(50);
    }

    static public int Fibonacci(Integer n) {
        if (n < 3)
            return n;
        else {
//            TODO 到底发生了什么
//            int n1 = map.computeIfAbsent(n - 1, ForStudy::Fibonacci);
//            int n2 = map.computeIfAbsent(n - 2, ForStudy::Fibonacci);
            return map.computeIfAbsent(n, x -> Fibonacci(x-1) + Fibonacci(x-2));
        }
    }

    public static int fast(Integer n) {
        if (n < 3) return n;
        else {
            int index = 2;
            int one = 1, two = 2;
            while (index < n) {
                int next = one + two;
                one = two;
                two = next;
                index++;
            }
            return two;
        }
    }

    public static int slow (Integer n) {
        if (n < 3) return n;
        else return slow(n-1) + slow(n-2);
    }

    public static int getNumber(int line, int left) {
        if (line == 1)
            return 1;
        else if (left == 0)
            return 1;
        else if (left == line - 1)
            return 1;
        else {
            Set<Integer> temp = new TreeSet<>();
            temp.add(line);
            temp.add(left);
            return map2.computeIfAbsent(temp, x-> getNumber(line-1, left-1) + getNumber(line-1, left));
        }
    }

    public static void display(int line) {
        if (line == 0)
            return;
        display(line - 1);
        for (int i = 0; i < line; i++) {
            System.out.print(getNumber(line, i) + "\t");
        }
        System.out.println();
    }
}

interface  Func{
    public abstract int func(Integer n);
}

