package test;

import org.springframework.web.client.RestTemplate;
import util.CSDNUtils;

public class AutoReFreshCSDNDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();
        CSDNUtils csdnUtils = new CSDNUtils();
//        csdnUtils.autoRefresh(args[0],Integer.parseInt(args[1]));//csdn设置了时间大概1分钟
        csdnUtils.autoRefresh("https://blog.csdn.net/qq_41813208/",50);//csdn设置了时间大概1分钟
    }

}
