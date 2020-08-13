package util.digg;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import util.accessArticle.AccessUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.alibaba.fastjson.JSONObject;

/**
 * 博客点赞工具类
 */
public class DiggUtils {
    static HttpHeaders headers = new HttpHeaders();//创建请求头对象
    static HttpEntity<String> entity;//带cookie的请求体

    private static CopyOnWriteArraySet set = new CopyOnWriteArraySet();

    /**
     * 点赞的测试代码
     *
     * @param args
     */
    public static void main(String[] args) {
        entity = getHttpEntity(getHeader());//设置带有cookie的请求体
        AccessUtils csdnUtils = new AccessUtils();//创建工具类，为了获取所有文章
        String blogerUrl = "https://blog.csdn.net/qq_41813208";//博主文章
        CopyOnWriteArraySet<String> allArticleUrl = csdnUtils.getAllArticleUrl(blogerUrl);//获取博主所有文章链接
        System.out.println(allArticleUrl);
        allArticleUrl.stream().forEachOrdered((url) -> {
            digg(url);
        });
    }

    /**
     * 封装好的点赞功能，如果是点赞则进行点赞
     *
     * @param articleUrl
     */
    public static synchronized void digg(String articleUrl) {
        String[] split = articleUrl.split("article/details/");
        String acticleId = split[1];//获取文章id
        String diggText = getLikeByArticleURL(articleUrl);//获得文章articleUrl是点赞还是已赞

        System.out.println("获得的信息{"+diggText+"}");

        if (diggText.strip().equals("点赞")) {//说明可以点赞
            String url = new StringBuffer(split[0]).append("/phoenix/article/digg?ArticleId=").append(acticleId).toString();
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
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(2000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element like = doc.getElementById("is-like-span");
        if (like == null) {
            System.out.println("文章为空");
            return null;
        }

        return like.text();
    }


    /**
     * 构造的一个请求头
     * 需要替换cookie
     *
     * @return
     */
    public static HashMap<String, String> getHeader() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("cookie", "从浏览器中复制自己的cookie值");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
        return headers;
    }

    /**
     * 发送点赞请求，将返回的数据直接返回
     *
     * @param url 点赞的url
     * @return
     */
    public static JSONObject getResponseStringByRestTemplate(String url) {
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class);//发送请求
        JSONObject result = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            result = JSONObject.parseObject(responseEntity.getBody());
        }
        return result;
    }

    /**
     * 设置请求头，包括cookie
     * 返回一个请求体对象
     *
     * @param headerMap
     * @return
     */
    private static HttpEntity<String> getHttpEntity(HashMap<String, String> headerMap) {
        Set<String> set = headerMap.keySet();
        set.forEach((key) -> {
            headers.add(key, headerMap.get(key));//添加请求头
        });
        return new HttpEntity<String>("", headers);
    }
}
