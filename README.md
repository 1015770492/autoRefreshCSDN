# autoRefreshCSDN 利用爬虫程序自动访问所有文章的博客
csdn博客访问量自动刷新程序，通过jsoup爬虫框架，获取对于的链接，spring的RestTemplete发送http请求



引入的依赖
```xml
    <dependencies>
    
        <!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.13.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        
    </dependencies>
```
# 使用的示例代码
调用CSDNUtils中的autoRefresh(String url,long sleepTime)传入CSDN的主页地址，以及定时刷新的时间，时间设置的太短刷不了访问量
博客越多提升的越快
```java
import org.springframework.web.client.RestTemplate;
import util.CSDNUtils;

public class AutoReFreshCSDNDemo {
    
    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();
        CSDNUtils csdnUtils = new CSDNUtils();
        csdnUtils.autoRefresh("https://blog.csdn.net/qq_41813208/",40);//csdn设置了时间大概1分钟
    }
    
}

```
实测有用！
![https://blog.csdn.net/qq_41813208](https://blog.csdn.net/qq_41813208)
几个小时增加8万多访问量，从6万涨到14万

