package SimpleTomcat.Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用reader对报文进行解析
 * 包含已经解析好的http报文和InputStream
 * Created by 41237 on 2016/7/11.
 */
public class Request
{
    private Map<String, String> parameters = new HashMap<>();     /* 用于uri参数 */
    private List<String> body = new LinkedList<>();
    private InetAddress address;

    /**
     * 创建对象的同时读取请求，解析格式
     * @param in 仅在读取请求时需要，不需要保存
     * @param address 传输文件建立连接时需要
     * @throws Exception
     */
    public Request(InputStream in, InetAddress address) throws Exception
    {
        this.address = address;

        parsePara(readRequest(in));
    }

    private void parsePara(List<String> content)
    {
        String para = content.remove(0);

        if (content.size() == 0)
        {
            return;
        }

        Arrays.stream(para.split("&"))
                .forEachOrdered(x -> parameters.put(x.split("=")[0], x.split("=")[1]));

        body.addAll(content);
    }

    private List<String> readRequest(InputStream in) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        List<String> ret = new ArrayList<>();
        ret.add(reader.readLine());
        while (reader.ready())
            ret.add(reader.readLine());
        return ret;
    }


    public List<String> getBody()
    {
        return body;
    }

    public String getParameter(String key)
    {
        Socket s;
        return parameters.get(key);
    }

    public InetAddress getAddress()
    {
        return address;
    }
}
