package JinXuLiang.LearnJava.CourseDesign.ClientCmd;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 41237 on 2016/6/19.
 */
public abstract class Cmd
{
    public abstract void execute(InputStream in, OutputStream out) throws Exception;
}
