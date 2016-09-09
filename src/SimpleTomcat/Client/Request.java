package SimpleTomcat.Client;

import SimpleTomcat.Util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/12.
 */
public class Request
{
    private PrintWriter out;
    private Map<String, String> para = new HashMap<>();
    private List<String> body = new LinkedList<>();

    public Request(String cmd, OutputStream out)
    {
        this.out = new PrintWriter(out, true);
        parseParas(cmd);
    }

    //从Main中传入的格式为 code arg1=xxx arg2=xxx
    private void parseParas(String paras)
    {
        String[] elemt = paras.split("\\s");

        for (int i = 1; i < elemt.length; i++)
        {
            String[] e = elemt[i].split("=");
            para.put(e[0], e[1]);
        }

    }

    /**
     * 将所有参数转换成特定的格式，并发送
     */
    public void flush()
    {
        // produce title
        String title = para.entrySet()
                .stream()
                .map(x -> new String(x.getKey().concat("=").concat(x.getValue())))
                .collect(Collectors.joining("&", "", ""));

        // produce body
        String bd = body.stream()
                .collect(Collectors.joining
                        (lineSeparator, lineSeparator, lineSeparator));

        out.print(title + bd);
        out.flush();
    }

    public String getPara(String key)
    {
        return para.get(key);
    }

    public void addPara(String key, String value)
    {
        para.put(key, value);
    }

    public String removePara(String key)
    {
        return para.remove(key);
    }

    //在Request中比较少用body
    public void addBody(String body)
    {
        this.body.add(body);
    }
    public void addBody(List<String> body)
    {
        this.body.addAll(body);
    }
}
