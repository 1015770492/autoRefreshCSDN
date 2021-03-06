package util.accessArticle;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 刷访csdn问量工具类
 */
public class AccessUtils {
    //    发送http请求的工具类
    private CopyOnWriteArraySet<String> myAllCategoryURL = new CopyOnWriteArraySet<>();//去重
    private CopyOnWriteArraySet<String> myAllArticleURL = new CopyOnWriteArraySet<>();//去重，并发访问
    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private AtomicLong count = new AtomicLong(0);
//    static Logger logger = Logger.getLogger(AccessUtils.class);

    /**
     * 获取博主所有的分类专栏访问地址
     * 例如：https://blog.csdn.net/qq_41813208/
     *
     * @param url
     */
    public void addAllBlogCategoryURL_By_URL(String url) {
        myAllCategoryURL.clear();//清空之前存的所有分类url集合
        ResponseEntity<String> htmlString = new RestTemplate().getForEntity(url, String.class);
        String html = htmlString.toString();//将获取的网页转换成字符串
        //获取html元素
        Document doc = Jsoup.parse(html);
        // 获取id=asideCategory的标签,这个标签下存放的是包含分类专栏的标签，缩小范围
        Element asideCategory = doc.getElementById("asideCategory");
        //  获取id=asideCategory的标签下的ul标签
        Elements ultag = asideCategory.getElementsByTag("ul");
        // 获取id=asideCategory的标签下的ul标签的a标签===>这个标签的href存放的就是分类专栏地址
        Elements a_s = ultag.get(0).getElementsByTag("a");
        //遍历a标签，获取a标签中的href属性值
        a_s.parallelStream().forEach((a) -> {
            String href = a.attr("href");
            if (!href.isEmpty()) {
                myAllCategoryURL.add(href);//并行流添加到集合中
            }
        });

        System.out.println("总共有" + myAllCategoryURL.size() + "个专栏");
//        logger.info("总共有" + myAllCategoryURL.size() + "个专栏");
    }

    /**
     * 根据分类专栏的地址获取分类专栏下的所有文章
     *
     * @param url
     * @return
     */
    public boolean addAllArticle_By_CategoryURL(String url) {
        ResponseEntity<String> htmlString = new RestTemplate().getForEntity(url, String.class);
        String html = htmlString.toString();//将获取的网页转换成字符串
        // 获取html元素
        Document doc = Jsoup.parse(html);
        String title = doc.getElementById("column").getElementsByClass("column_title oneline").get(0).text();//获取专栏标题

        //1、 获取类名为column_article_list的html标签，这个标签内存放的就是文章的列表
        Elements column_article_list = doc.getElementsByAttributeValue("class", "column_article_list");
        //2、 获取专栏中所有文章的li元素
        Elements li_s = column_article_list.get(0).getElementsByTag("li");
        //3、并行流处理，遍历获取文章url
        li_s.parallelStream().forEach((li) -> {
            String href = li.getElementsByTag("a").attr("href");
            if (!href.isEmpty()) {
                myAllArticleURL.add(href);//存入成员变量中
            }
        });

//        System.out.println(title + "专栏有" + li_s.size() + "篇文章");
//        logger.info(title + "专栏有" + li_s.size() + "篇文章");
        return true;
    }


    /**
     * 遍历所有专栏，获取所有文章
     * 会存到myAllArticleURL的成员变量上
     *
     * @param url
     * @return
     */
    public CopyOnWriteArraySet<String> getAllArticleUrl(String url) {
        //1、获取所有专栏url，存入成员变量myAllCategoryURL
        addAllBlogCategoryURL_By_URL(url);//阻塞式的获取专栏，因为这里专栏地址在html中就有，只需要发一次请求，这里就单线程完成即可。

        AtomicInteger atomicInteger=new AtomicInteger(myAllCategoryURL.size());//
        //2、流处理获取所有专栏下的url
        myAllCategoryURL.forEach((categoryUrl) -> {
            CompletableFuture<Boolean> booleanCompletableFuture = CompletableFuture.supplyAsync(() -> addAllArticle_By_CategoryURL(categoryUrl), executorService);
            booleanCompletableFuture.thenAcceptAsync((status) -> {
                if (status == true) {
//                    System.out.println(atomicInteger.get());
                    atomicInteger.set(atomicInteger.decrementAndGet());
                } else {
                    System.out.println(status);
                }
            }, executorService);

        });

        //阻塞主线程，等待任务完成
        while (true) {
            if (atomicInteger.get() == 0) {
                System.out.println("完成添加");
                break;
            }else continue;
        }
        //移除集合中分类专栏地址
        System.out.println("====>" + myAllCategoryURL.size());


        System.out.println("总共有" + this.myAllArticleURL.size() + "篇文章");
        long times = count.incrementAndGet();
        if (count.get() >= Long.MAX_VALUE - 1) {
            count.set(0);//超过最大值则重新初始化为0
        }
        System.out.println("\n\n======访问了" + times + "次=======\n\n");
        return myAllArticleURL;//所有文章地址
    }

    /**
     * 定时的访问文章详情，模拟用户点击文章
     *
     * @param url
     * @param scheduleTime
     */
    public void accessCSDNArticle(String url, Long scheduleTime) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
            if (forEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println(url + "访问成功");
            } else {
                System.out.println(url + "访问失败");
            }
        }, 0, scheduleTime, TimeUnit.SECONDS);
    }

    /**
     * 重新来一遍
     *
     * @param url
     */
    public void updateUrl(String url) {
        this.myAllCategoryURL.clear();//清空所有专栏地址
        this.myAllArticleURL.clear();//清空之前的文章
        getAllArticleUrl(url);//重新获取所有文章链接到myAllArticleURL中
    }


    /**
     * 前面一次性获取来所有博客链接，并保存了下来。这样就不需要再次发送获取所有文章的请求
     *
     * @param url          传入博主自己的url主页，例如：https://blog.csdn.net/qq_41813208/
     * @param scheduleTime 休眠时间，单位秒
     */
    public void autoRefresh(String url, long scheduleTime) {
        getAllArticleUrl(url);//获取所有文章链接，文章链接会存在成员变量myAllArticleURL中

        this.myAllArticleURL.stream().forEach(articleUrl -> {
            accessCSDNArticle(articleUrl, scheduleTime);//定时的访问，防止刷新太快被封ip
        });
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            updateUrl(url);//每2小时更新一次url
        }, 0, 2, TimeUnit.HOURS);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.gc();
        }, 0, 65, TimeUnit.SECONDS);
    }
}
