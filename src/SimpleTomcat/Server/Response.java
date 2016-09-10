package SimpleTomcat.Server;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import static SimpleTomcat.Util.*;

/**
 * Response中有request请求和对Client的OutputStream
 * 无论是Request中的InputStream还是Response中的OutputStream
 * 都是最简单的Stream，从socket获得，没有添加任何装饰器
 * Created by 41237 on 2016/7/11.
 */
public class Response
{
    private PrintWriter out;
    private HashMap<String, String> paraMap = new HashMap<>();
    private List<String> body = new LinkedList<>();

    public Response(OutputStream out)
    {
        this.out = new PrintWriter(out, true);
    }

    /**
     * @return 方便和格式化输出，注意Writer是基于字符的，auto flush哦
     */

    public void addPara(String key, String value)
    {
        paraMap.put(key, value);
    }

    public void addBody(List<String> body)
    {
        this.body.addAll(body);
    }

    public void addBody(String body)
    {
        this.body.add(body);
    }

    /**
     * 同时清空所有参数
     */
    public void flush()
    {
        String title = paraMap.entrySet()
                .stream()
                .map(x -> x.getKey().concat("=").concat(x.getValue())) // I trust the compiler will optimize
                .collect(Collectors.joining("&", "", ""));

        String bd = body.stream()
                .collect(Collectors.joining(lineSeparator, lineSeparator, lineSeparator));

        out.print(title + bd);
        out.flush();

        body.clear();
        paraMap.clear();
    }

    public static void main(String[] args)
    {
        Response r = new Response(System.out);
        r.addPara("hello", "too");
        r.addPara("goodbye", "toto");
        r.addPara("hey", "sub");
        r.addBody(Arrays.asList("dd", "hr"));
        r.flush();
    }
}
