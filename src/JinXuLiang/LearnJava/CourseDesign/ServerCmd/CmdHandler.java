package JinXuLiang.LearnJava.CourseDesign.ServerCmd;

import JinXuLiang.LearnJava.CourseDesign.KernelData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 41237 on 2016/5/17.
 */
public class CmdHandler {

    private static Map<String, Cmd> handler;
    private static KernelData kernelData;
    private static Cmd unsupported;

//    public static void main(String[] args) throws Exception
//    {
//        CmdHandler handler = new CmdHandler();
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        while (true)
//        handler.parse(in.readLine(), System.in, System.out);
//    }

    public CmdHandler() throws Exception
    {
        if (kernelData == null)
        synchronized (this)
        {
            if (kernelData == null)
            {
                kernelData = new KernelData("d:/ftp");
                handler = new HashMap<>();
                handler.put("ls", new Ls(kernelData));
                handler.put("cd", new Cd(kernelData));
                handler.put("down", new Down(kernelData));
                handler.put("up", new Up(kernelData));

                unsupported = new Unsupported(kernelData);
            }
        }
    }

    public void parse(InputStream in, OutputStream out) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Cmd run = handler.get(reader.readLine());
        if (run == null)
        {
            run = unsupported;
        }
        run.execute(in, out);
    }

}
