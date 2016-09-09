package SimpleTomcat.Server.ServletInstance;

import SimpleTomcat.DataConnection;
import SimpleTomcat.Server.FileManager.FileCRUD;
import SimpleTomcat.Server.FileManager.FileInit;
import SimpleTomcat.Server.FileManager.FileManager;
import SimpleTomcat.Server.Request;
import SimpleTomcat.Server.Response;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 41237 on 2016/7/11.
 */
public interface Servlet
{
    AtomicLong downloading = new AtomicLong(0);
    AtomicLong uploading = new AtomicLong(0);

    DataConnection dataConnection = new DataConnection(8890);

    /* for FileInit will init when it constructs */
    String homeDir = "d:\\ftp\\";
    FileInit init = FileManager.getInit();
    FileCRUD crud = FileManager.getCrud();


    public void service(Request request, Response response);
}
