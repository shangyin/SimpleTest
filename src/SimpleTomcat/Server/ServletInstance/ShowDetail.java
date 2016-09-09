package SimpleTomcat.Server.ServletInstance;

import SimpleTomcat.Server.Request;
import SimpleTomcat.Server.Response;

import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/12.
 */
public class ShowDetail implements Servlet
{
    @Override
    public void service(Request request, Response response)
    {
        response.addPara("code", ANSWER_PERMIT);
        response.addBody("total people downloading: " + downloading.get());
        response.addBody("total people uploading: " + uploading.get());
        response.addBody("most popular file:");
        response.addBody(crud.find(5));
        response.flush();
    }
}
