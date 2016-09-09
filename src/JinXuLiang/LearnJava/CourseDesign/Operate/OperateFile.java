package JinXuLiang.LearnJava.CourseDesign.Operate;


import java.io.*;
import java.util.*;

/**
 * 操作记录对象。对操作记录进行操作
 * Created by 41237 on 2016/6/9.
 */
public class OperateFile {

    private File record;
    private ArrayList<OperateRecord> operate;

    public static void main(String[] args) throws Exception
    {
//        OperateFile r = new OperateFile("d:\\ftp\\hello.txt");
//        r.add(new OperateRecord("hello", "good", "you!"));
//        r.add(new OperateRecord("Good", "bye", "you"));
//        r.write();

        OperateFile r = new OperateFile("d:\\ftp\\hello.txt");
        r.read();
        r.getAll().stream().forEach(System.out::print);


    }

    public OperateFile(String name)
    {
        record = new File(name);
        operate = new ArrayList<>();
    }

    public int read() throws IOException
    {
        int count = 0;
        Scanner in = new Scanner(record);
        while (in.hasNextLine())
        {
            String temp = in.nextLine();
            String[] split = temp.split("\\t");

            OperateRecord r = new OperateRecord(split[0], split[1], split[2]);
            operate.add(r);
            count += 1;
        }
        return count;
    }

    /**
     *
     * @return 最近的五条记录
     */
    public List recent()
    {
        if (operate.size() < 5)
        {
            return operate;
        }
        else
        {
            return operate.subList(operate.size()-5, operate.size()-1);
        }
    }

    /**
     *
     * @return 所有记录
     */
    public List getAll()
    {
        return operate;
    }

    public boolean add(OperateRecord r)
    {
        return operate.add(r);
    }

    /**
     * 将记录写进文件
     * @throws IOException
     */
    public void write() throws IOException
    {
        FileWriter out = new FileWriter(record);
        Iterator e = operate.iterator();
        while (e.hasNext())
        {
            out.write(((OperateRecord)e.next()).toString());
        }
        out.close();
    }
}
