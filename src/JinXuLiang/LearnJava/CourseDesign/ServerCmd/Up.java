package JinXuLiang.LearnJava.CourseDesign.ServerCmd;

import JinXuLiang.LearnJava.CourseDesign.FileRecord;
import JinXuLiang.LearnJava.CourseDesign.KernelData;

import java.io.*;

/**
 * Created by 41237 on 2016/6/10.
 */
public class Up extends Cmd
{

    public Up(KernelData kernelData)
    {
        super(kernelData);
    }

    /**
     * 给out用PrintStream返回内容，成功为OK，否者为NO
     * @param in 假设s已经接up,
     *           接下来的规则是文件名的长度——文件名——文件长度——文件
     */
    public void execute(InputStream in, OutputStream out) throws Exception
    {
//        DataInputStream data = new DataInputStream(in);
        BufferedReader data = new BufferedReader(new InputStreamReader(in));
        PrintStream p = new PrintStream(out);

        long nameL = Long.parseLong(data.readLine());
        StringBuilder name  = new StringBuilder();
        name.append(data.readLine());

        File file = new File(name.toString());
        if (file.exists())
        {
            p.println("No");
        }
        else
        {
            p.println("Yes");
            System.out.println(file.getAbsoluteFile());
        }
        file.createNewFile();
        BufferedWriter fileOut = new BufferedWriter(new FileWriter(file));
        long fileL = Long.parseLong(data.readLine());
        for (int i = 0; i < fileL; i++)
        {
            fileOut.write(data.read());
        }
        fileOut.close();

        kernelData.addRecord(name.toString(), new FileRecord(name.toString(), nameL));
        kernelData.write();
    }
}
