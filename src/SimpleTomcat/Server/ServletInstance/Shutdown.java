package SimpleTomcat.Server.ServletInstance;

import SimpleTomcat.Server.Request;
import SimpleTomcat.Server.Response;
import static SimpleTomcat.Util.*;

/**
 * Created by 41237 on 2016/7/12.
 */
public class Shutdown implements Servlet
{
    @Override
    public void service(Request request, Response response)
    {
        init.write2Db();

        response.addPara(CODE, ANSWER_PERMIT);
        response.flush();
    }
}
