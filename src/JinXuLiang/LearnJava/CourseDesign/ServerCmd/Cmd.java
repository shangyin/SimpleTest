package JinXuLiang.LearnJava.CourseDesign.ServerCmd;

import JinXuLiang.LearnJava.CourseDesign.KernelData;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * out用printStream, in用Reader
 * Created by 41237 on 2016/6/10.
 */
public abstract class Cmd {

    protected KernelData kernelData;

    public Cmd(KernelData kernelData)
    {
        this.kernelData = kernelData;
    }

    public abstract void execute(InputStream in, OutputStream out) throws Exception;
}
