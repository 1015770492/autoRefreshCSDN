package test;



import util.accessArticle.AccessUtils;



public class AutoReFreshCSDNDemo {


    public static void main(String[] args) throws Exception {
        AccessUtils csdnUtils = new AccessUtils();
        csdnUtils.autoRefresh("https://blog.csdn.net/ywl470812087", 65);//csdn设置了时间大概1分钟
        AccessUtils csdnUtils2 = new AccessUtils();
        csdnUtils2.autoRefresh("https://blog.csdn.net/qq_41813208/", 65);//csdn设置了时间大概1分钟

    }

}
