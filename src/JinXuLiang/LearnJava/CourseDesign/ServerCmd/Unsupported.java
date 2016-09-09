package JinXuLiang.LearnJava.CourseDesign.ServerCmd;

import JinXuLiang.LearnJava.CourseDesign.KernelData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by 41237 on 2016/6/10.
 */
public class Unsupported extends Cmd {

    public Unsupported(KernelData kernelData)
    {
        super(kernelData);
    }

    public void execute(InputStream in, OutputStream out) throws IOException
    {
        PrintStream p = new PrintStream(out);
        p.println("No");
    }
}
