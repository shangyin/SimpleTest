package JinXuLiang.LearnJava.CourseDesign.Operate;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * 操作记录。内容有操作类型type, 操作日期date, 操作注释note
 * Created by 41237 on 2016/5/17.
 */
public class OperateRecord {

        private String type;
        private String date;
        private String note;

        public OperateRecord(String type, String date, String note)
        {
            this.type = type;
            this.date = date;
            this.note = note;
        }

        public String toString()
        {
            return type + "\t" + date + "\t" + note + "\r\n";
        }

        /*
        * 接下来是getter和setter
        * */
        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
}

