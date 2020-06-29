package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CSDNUtils {
    //    发送http请求的工具类
    private RestTemplate restTemplate = new RestTemplate();


    /**
     * 获取博主所有的分类专栏访问地址
     * 例如：https://blog.csdn.net/qq_41813208/
     *
     * @param url
     */
    public List<String> getAllBlogCategoryURL_By_URL(String url) {
        ResponseEntity<String> htmlString = restTemplate.getForEntity(url, String.class);
        String html = htmlString.toString();//将获取的网页转换成字符串
//        获取html元素
        Document doc = Jsoup.parse(html);
//        获取id=asideCategory的标签
        Element asideCategory = doc.getElementById("asideCategory");
//        获取id=asideCategory的标签下的ul标签
        Elements ultag = asideCategory.getElementsByTag("ul");
//        获取id=asideCategory的标签下的ul标签的a标签
        Elements a = ultag.get(0).getElementsByTag("a");
        List<String> categoryURL = new ArrayList<>();
//        遍历a标签，获取a标签中的href属性值
        for (int i = 0; i < a.size(); i++) {
            String href = a.get(i).attr("href");//获取href属性
            if (!href.isEmpty())
            {
                categoryURL.add(i, href);//存入数组中
            }
        }
        System.out.println(categoryURL.size());
        return categoryURL;
    }

    /**
     * 获取一个分类文章下的所有文章
     * @param url
     * @return
     */
    public List<String> getAllArticleBy_CategoryURL(String url) {
        ResponseEntity<String> htmlString = restTemplate.getForEntity(url, String.class);
        String html = htmlString.toString();//将获取的网页转换成字符串
//        获取html元素
        Document doc = Jsoup.parse(html);
        Elements column_article_list = doc.getElementsByAttributeValue("class", "column_article_list");
        Elements li_s = column_article_list.get(0).getElementsByTag("li");
        List<String> allArticle = new ArrayList<>();
        for (int i = 0; i < li_s.size(); i++) {
            Elements a = li_s.get(i).getElementsByTag("a");
            String href = a.attr("href");
            if (!href.isEmpty()) {
                allArticle.add(href);
            }
        }
        return allArticle;
    }

    /**
     * 访问文章详情，模拟用户点击文章
     *
     * @param url
     */
    public void accessCSDNArticle(String url) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        if (forEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println(url + "访问成功");
        } else {
            System.out.println(url + "访问失败");
        }
    }

    /**
     * 返回所有文章url列表
     * @param url
     * @return
     */
    public List<String> getAllArticleUrl(String url) {
        List<String> allBlogCategoryURL = getAllBlogCategoryURL_By_URL(url);//获取所有分类链接

        List<String> allArticleUrl = new ArrayList<>();

        for (int i = 0; i < allBlogCategoryURL.size(); i++) {
            List<String> allArticle = getAllArticleBy_CategoryURL(allBlogCategoryURL.get(i));//获取分类下的所有文章
            allArticleUrl.addAll(allArticle);//将分类文章的所有url加入数组中
        }
        return allArticleUrl;
    }

    public void autoRefresh(String url, long sleepTime) {
        List<String> allArticleUrl = getAllArticleUrl(url);
        while (true) {
            for (int i = 0; i < allArticleUrl.size(); i++) {
                if (allArticleUrl.get(i)!=null){
                    accessCSDNArticle(allArticleUrl.get(i));//访问文章
                }
            }
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
