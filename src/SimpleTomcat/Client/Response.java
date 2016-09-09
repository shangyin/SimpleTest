package SimpleTomcat.Client;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by 41237 on 2016/7/24.
 */
public class Response
{
    private HashMap<String, String> parameter = new HashMap<>();
    private List<String> body = new LinkedList<>();
    private BufferedReader in;

    private boolean isRead = false;

    public Response(InputStream in)
    {
        this.in = new BufferedReader(new InputStreamReader(in));

    }

    /**
     * 本方法只应该被调用一次
     * isRead不是线程安全的
     * 然而，response不应该被多线程使用
     */
    public void readResponse() throws Exception
    {
        if (!isRead)
        {
            parse(getStrings());
            isRead = true;
        }
    }

    /*
        改变了in的状态
     */
    private List<String> getStrings() throws Exception
    {
        List<String> content = new ArrayList<>();
        content.add(in.readLine());
        while (in.ready())
            content.add(in.readLine());
        return content;
    }

    private void parse(List<String> content)
    {
        String paras = content.remove(0);
        // parse parameters

        if (content.size() == 0)
        {
            return;
        }

        Arrays.stream(paras.split("&"))
                .forEach(x -> parameter.put(x.split("=")[0], x.split("=")[1]));

        // get body
        body.addAll(content);
    }

    public List<String> getBody()
    {
        return body;
    }

    public String getPara(String key)
    {
        return parameter.get(key);
    }
}
