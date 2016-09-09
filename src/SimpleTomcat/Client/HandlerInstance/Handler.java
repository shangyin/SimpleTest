package SimpleTomcat.Client.HandlerInstance;

import SimpleTomcat.Client.Request;
import SimpleTomcat.Client.Response;

/**
 * Created by 41237 on 2016/7/12.
 */
public interface Handler
{
    public void service(Request request, Response response) throws Exception;

}
