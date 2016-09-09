package JinXuLiang.LearnJava.CourseDesign.ServerCmd;

import JinXuLiang.LearnJava.CourseDesign.KernelData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by 41237 on 2016/6/10.
 */
public class Down extends Cmd {


    public Down(KernelData kernelData)
    {
        super(kernelData);
    }

    /**
     *
     * @param in    文件名
     * @param out   返回长度，然后返回文件
     * @throws IOException
     */
    public void execute(InputStream in, OutputStream out) throws IOException
    {
        PrintStream p = new PrintStream(out);
    }
}
