package shangyin.github.SimpleCalc;

import java.util.*;

/**
 * Created by 41237 on 2016/3/23.
 */
public class WordScanner {

    public static int readNumber(String s, int index)
    {
        int last = index;
        while (last < s.length() && s.charAt(last) >= 47 && s.charAt(last) <= 57) last++;
        String number = s.substring(index, last);
        return Integer.valueOf(number);
    }
}
