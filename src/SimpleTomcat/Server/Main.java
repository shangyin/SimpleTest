package SimpleTomcat.Server;

/**
 * Created by 41237 on 2016/6/22.
 */



public class Main
{

    public static void main(String[] args) throws Exception
    {
        System.out.println("服务器启动");
        Tomcat tomcat = new Tomcat(8889);
        tomcat.init();
        System.out.println("服务器初始化完成");
        System.out.println("服务器开始监听");
        tomcat.start();
        System.out.println("服务器关闭");
    }


}


