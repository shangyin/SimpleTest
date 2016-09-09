package SimpleTomcat.Client.HandlerInstance;

import SimpleTomcat.Client.Request;
import SimpleTomcat.Client.Response;
import SimpleTomcat.Util;

import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/13.
 */
public class ListDir implements Handler
{
    @Override
    public void service(Request request, Response response) throws Exception
    {

        //发送命令
        request.addPara(CODE, "LIST");
        request.flush();

        response.readResponse();
        if (response.getPara(CODE).equals(ANSWER_DENY))
        {
            System.out.println("Error");
        }
        else
        {
            response.getBody()
                    .forEach(System.out::println);
        }

    }
}
