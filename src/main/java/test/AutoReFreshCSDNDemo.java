package test;

import util.CSDNUtils;



public class AutoReFreshCSDNDemo {

    public static void main(String[] args) {

        CSDNUtils csdnUtils = new CSDNUtils();
//        csdnUtils.autoRefresh(args[0],Integer.parseInt(args[1]));//csdn设置了时间大概1分钟
//        csdnUtils.autoRefresh("https://blog.csdn.net/qq_44772414",50);//csdn设置了增加访问量的时间，时间大概1分钟
        csdnUtils.autoRefresh("https://blog.csdn.net/qq_41813208/",50);//csdn设置了时间大概1分钟
    }

}
