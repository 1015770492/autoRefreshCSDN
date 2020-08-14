package util.digg;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import java.util.concurrent.CopyOnWriteArraySet;
import org.springframework.web.client.RestTemplate;
import util.accessArticle.AccessUtils;
import com.alibaba.fastjson.JSONObject;
import util.request.RequestUtils;


/**
 * 博客点赞工具类
 */
public class DiggUtils {


    /**
     * 点赞的测试代码
     *
     * @param args
     */
    public static void main(String[] args) {
        AccessUtils csdnUtils = new AccessUtils();//创建工具类，为了获取所有文章
        String blogerUrl = "https://blog.csdn.net/qq_41813208";//博主文章
//        String blogerUrl = "https://blog.csdn.net/ywl470812087";//博主文章
        CopyOnWriteArraySet<String> allArticleUrl = csdnUtils.getAllArticleUrl(blogerUrl);//获取博主所有文章链接
        System.out.println(allArticleUrl);
        allArticleUrl.forEach((url) -> {
            digg(url);
        });
    }

    /**
     * 封装好的点赞功能，如果是点赞则进行点赞
     *
     * @param articleUrl
     */
    public static void digg(String articleUrl) {
        String[] split = articleUrl.split("/article/details/");
        String acticleId = split[1];//获取文章id
        String diggText = getLikeByArticleURL(articleUrl);//获得文章articleUrl是点赞还是已赞

        System.out.println("获得的信息{" + diggText + "}");
        if (diggText.strip().equals("点赞")) {//说明可以点赞
            String url = new StringBuilder(split[0]).append("/phoenix/article/digg?ArticleId=").append(acticleId).toString();
            System.out.println("点赞请求=======》" + url);
            JSONObject json = getResponseStringByRestTemplate(url);//进行点赞
            System.out.println("点赞完成：" + json.toJSONString());
        } else {
            System.out.println("文章已经点赞过");
        }


    }

    /**
     * 获取加载后的网页内容
     * <p>
     * 现如今好多网页都是通过js渲染页面的，如果直接访问网址则不会返回一个完整的网页
     * 点赞功能需要判断是否可以点赞，也就是图标边上是  点赞  还是  已赞   如果是点赞则说明可以进行点赞！
     *
     * @param url 文章的详情页面的url
     * @return 返回 点赞/已赞
     */
    public static String getLikeByArticleURL(String url) {

        ResponseEntity<String> forEntity = new RestTemplate().exchange(url, HttpMethod.GET, RequestUtils.httpEntity, String.class);
        Document doc = null;
        if (forEntity.getStatusCode() == HttpStatus.OK) {
            doc = Jsoup.parse(forEntity.getBody());
        } else {
            System.out.println("状态码" + forEntity.getStatusCode());
            return "";
        }

        Element like = doc.getElementById("is-like-span");
        if (like == null) {
            System.out.println("文章为空");
            return null;
        }
        return like.text();

    }


    /**
     * 发送点赞请求，将返回的数据直接返回
     *
     * @param url 点赞的url
     * @return
     */
    public static JSONObject getResponseStringByRestTemplate(String url) {
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, RequestUtils.httpEntity, String.class);//发送请求
        JSONObject result = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            result = JSONObject.parseObject(responseEntity.getBody());
        }
        return result;
    }


}
