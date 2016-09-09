import java.lang.reflect.Method;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        Method method = Object.class.getMethod("equals", Object.class);
        Object object = new Object();
        boolean a = (boolean)method.invoke(object, object);
        System.out.println(a);
    }

}

class Father
{
    int value = 100;
}

class Son extends Father
{
    int value = 200;
}
