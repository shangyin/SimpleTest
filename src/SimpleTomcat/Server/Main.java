package SimpleTomcat.Server;

/**
 * Created by 41237 on 2016/6/22.
 */



public class Main
{
    public static void main(String[] args) throws Exception
    {
        Tomcat tomcat = new Tomcat(8889);
        tomcat.init();
        tomcat.start();
    }
}


