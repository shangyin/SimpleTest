package JinXuLiang.LearnJava.CourseDesign;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 41237 on 2016/6/10.
 */
public class FileRecord {

    private String note;
    private AtomicInteger totalDown;
    private AtomicInteger currentDown;
    private long size;

    public FileRecord(String note, long size)
    {
        this.note = note;
        this.size = size;
        currentDown = new AtomicInteger(0);
        totalDown = new AtomicInteger(0);
    }

    public FileRecord(String note, long size, AtomicInteger totalDown)
    {
        this.note = note;
        this.size = size;
        this.totalDown = totalDown;
    }

    public void addTotal()
    {
        totalDown.addAndGet(1);
    }

    public void addCurrent()
    {
        currentDown.addAndGet(1);
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public String getNote() {
        return note;
    }

    public AtomicInteger getTotalDown() {
        return totalDown;
    }

    public AtomicInteger getCurrentDown() {
        return currentDown;
    }

    public long getSize() {
        return size;
    }


    public String toString()
    {
        return note + "\t" + size + "\t" + totalDown;
    }
}
