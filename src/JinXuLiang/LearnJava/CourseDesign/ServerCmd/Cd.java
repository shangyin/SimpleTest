package JinXuLiang.LearnJava.CourseDesign.ServerCmd;

import JinXuLiang.LearnJava.CourseDesign.KernelData;

import java.io.*;

/**
 * Created by 41237 on 2016/6/10.
 */
public class Cd extends Cmd {

    public Cd(KernelData kernelData)
    {
        super(kernelData);
    }

    public void execute(InputStream in, OutputStream out) throws IOException
    {
        PrintStream p = new PrintStream(out);
        p.println("we need to do something...");
        DataInputStream data = new DataInputStream(in);


    }
}
