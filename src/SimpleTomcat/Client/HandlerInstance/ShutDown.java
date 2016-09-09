package SimpleTomcat.Client.HandlerInstance;

import SimpleTomcat.Client.Request;
import SimpleTomcat.Client.Response;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PrimitiveIterator;

/**
 * Created by 41237 on 2016/7/13.
 */
public class ShutDown implements Handler
{
    @Override
    public void service(Request request, Response response) throws Exception
    {
        request.addPara("code", "SHUTDOWN");
        request.flush();
    }
}
