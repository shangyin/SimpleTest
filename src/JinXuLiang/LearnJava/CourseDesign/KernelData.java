package JinXuLiang.LearnJava.CourseDesign;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by 41237 on 2016/6/10.
 */
public class KernelData {

    private String root;
    private Map<String, FileRecord> fileRecords;

//    public static void main(String[] args) throws Exception
//    {
//        KernelData k = new KernelData("d:\\ftp");
//        k.list();
//        k.write();
//
//    }

    public void addRecord(String name, FileRecord r)
    {
        fileRecords.put(name, r);
    }

    /**
     * 首先读取note上边的记录，然后检查ftp目录下有没有note记录上面遗漏的
     * @param root 比如d:/ftp, ftp为目录
     * @throws Exception
     */
    public KernelData(String root) throws Exception
    {
        this.root = root;
        fileRecords = new TreeMap<>();

        File f = new File(root + "\\note.txt");
        Scanner in = new Scanner(f);
        while (in.hasNextLine())
        {
            String t = in.nextLine();
            String[] split = t.split("\\s");
            fileRecords.put(split[0],
                    new FileRecord(split[1],
                            Long.parseLong(split[2]),
                            new AtomicInteger(Integer.parseInt(split[3]))));
        }

        for (File e : f.getParentFile().listFiles(File::isFile))
        {
            fileRecords.computeIfAbsent(e.getName(), x -> new FileRecord(e.getName(), e.length()));
        }
    }

    /**
     *
     * @param name 要修改的文件
     * @param note 要修改的文件的备注
     */
    public void modifyNote(String name, String note)
    {
        FileRecord t =  fileRecords.get(name);
        t.setNote(note);
    }

    public void write() throws Exception
    {
        FileWriter r = new FileWriter(root + "\\note.txt");

        Iterator i = fileRecords.entrySet().iterator();
        while (i.hasNext())
        {
            Map.Entry<String, FileRecord> t = (Map.Entry)i.next();
            r.write(t.getKey() + "\t");
            r.write(t.getValue().toString() + "\r\n");
        }
        r.close();
    }

    /**
     *
     * @return String数组，可以直接输出
     */
    public Object[] list()
    {
        return fileRecords.
                entrySet().
                stream().
                map(x -> new String(x.getKey() + "\t" + x.getValue().toString())).toArray();
    }
}
