package Java.Core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 41237 on 2016/4/4.
 */
public class MapTest {
    public static void main(String[] args)
    {
        Map<String, String> staff = new HashMap<>();
        staff.put("144-25-5464", "one");
        staff.put("567-24-2546", "two");
        staff.put("157-62-7935", "three");
        staff.put("456-62-5527", "four");

        System.out.println(staff);

        staff.remove(("567-24-2546"));

        staff.put("456-62-5527", "five");

        System.out.println(staff.get("157-62-7935"));
        for (Map.Entry<String, String> e : staff.entrySet())
        {
            String key = e.getKey();
            String value = e.getValue();
            System.out.println("key=" + key + ", value=" + value);
        }
    }
}
