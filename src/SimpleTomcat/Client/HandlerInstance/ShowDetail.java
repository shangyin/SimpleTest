package SimpleTomcat.Client.HandlerInstance;

import SimpleTomcat.Client.Request;
import SimpleTomcat.Client.Response;

import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/12.
 */
public class ShowDetail implements Handler
{
    @Override
    public void service(Request request, Response response) throws Exception
    {
        request.addPara(CODE, ANSWER_PERMIT);
        request.flush();

        response.readResponse();
        response.getBody()
                .forEach(System.out::println);
    }
}
