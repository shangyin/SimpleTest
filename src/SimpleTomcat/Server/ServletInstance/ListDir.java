package SimpleTomcat.Server.ServletInstance;

import SimpleTomcat.Server.Request;
import SimpleTomcat.Server.Response;

import java.io.*;
import java.util.Arrays;

/**
 * Created by 41237 on 2016/7/11.
 */
public class ListDir implements Servlet
{

    @Override
    public void service(Request request, Response response)
    {
        response.addPara("code", "YES");
//        crud.find(crud.size())
//                .stream()
//                .forEach(System.err::println);
        response.addBody(crud.find(crud.size()));
        response.flush();
    }
}
