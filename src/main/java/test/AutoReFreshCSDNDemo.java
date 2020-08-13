package test;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class AutoReFreshCSDNDemo {


    public static void main(String[] args) throws Exception {
//        AccessUtils csdnUtils = new AccessUtils();
//        csdnUtils.autoRefresh("https://blog.csdn.net/ywl470812087", 65);//csdn设置了时间大概1分钟
//        AccessUtils csdnUtils2 = new AccessUtils();
//        csdnUtils2.autoRefresh("https://blog.csdn.net/qq_41813208/", 65);//csdn设置了时间大概1分钟


//        Document doc = Jsoup.connect("https://blog.csdn.net/qq_41813208/article/details/107933988").timeout(2000).get();
//        System.out.println(doc.toString());
//        System.out.println(doc.getElementById("is-like-span").text());


        String url="https://blog.csdn.net/qq_41813208/article/details/104645567";
        System.out.println(url);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(2000).get();
            System.out.println(doc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element like = doc.getElementById("is-like-span");
        System.out.println(like.toString());

//        return like.text();

    }

}
