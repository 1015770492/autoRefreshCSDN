# autoRefreshCSDN 利用爬虫程序自动访问所有文章的博客
100多行实现的csdn博客访问量自动刷新程序，原理很简单通过jsoup爬虫框架，获取对于的链接，spring的RestTemplete发送http请求



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

import util.accessArticle.CSDNUtils;

public class AutoReFreshCSDNDemo {
    
    public static void main(String[] args) {
        CSDNUtils csdnUtils = new CSDNUtils();
        csdnUtils.autoRefresh("https://blog.csdn.net/qq_41813208/",50);//csdn设置了时间大概1分钟
    }
    
}

```
输出示例：
```bash
总共有78个专栏
css笔记专栏有19篇文章
java设计模式专栏有10篇文章
Mongodb专栏有9篇文章
大数据专栏有15篇文章
花生鼠的日常建站专栏有15篇文章
IDEA专栏有20篇文章
关系型数据库专栏有0篇文章
gateway专栏有3篇文章
值得收藏的几个软件专栏有1篇文章
Ribbon专栏有0篇文章
Jenkins专栏有0篇文章
Mybatis/Mybatis-Plus专栏有4篇文章
Vue学习笔记专栏有40篇文章
Consul专栏有1篇文章
hadoop专栏有34篇文章
Dubbo专栏有2篇文章
java专栏有0篇文章
Vue源码分析专栏有3篇文章
服务器 和 集群专栏有19篇文章
Redis专栏有4篇文章
NIO （New Input Output）专栏有8篇文章
Eureke专栏有6篇文章
win10专栏有5篇文章
汇编专栏有12篇文章
springCloud专栏有9篇文章
非关系想数据库专栏有0篇文章
java8新特性专栏有7篇文章
安装各种操作系统专栏有7篇文章
Webpack笔记专栏有3篇文章
ES6专栏有16篇文章
Zookeeper专栏有6篇文章
Maven专栏有14篇文章
Shell编程专栏有2篇文章
微信小程序专栏有2篇文章
JUC (java.util.concurrent)专栏有4篇文章
前端专栏有4篇文章
fastdfs专栏有9篇文章
Git专栏有5篇文章
密码学专栏有0篇文章
线程专栏有4篇文章
Java高级笔记专栏有13篇文章
MySQL和MySQL高级专栏有21篇文章
CSS特效专栏专栏有16篇文章
数据结构和算法专栏有4篇文章
Nginx专栏有10篇文章
云博前端专栏有10篇文章
微信支付专栏有2篇文章
groovy专栏有1篇文章
Centos专栏有18篇文章
springboot+vue整合问题总结专栏有1篇文章
云博后端专栏有0篇文章
宽带账号专栏有1篇文章
Ubuntu专栏有24篇文章
项目总结专栏有1篇文章
谷粒商城笔记专栏有1篇文章
Java面试题系列专栏有8篇文章
JVM专栏专栏有3篇文章
Docker专栏有29篇文章
Emmet语法专栏有1篇文章
云博运维专栏有2篇文章
activeMQ专栏有1篇文章
Linux笔记专栏有23篇文章
SpringBoot专栏有22篇文章
spring注解专栏有17篇文章
yaml专栏有2篇文章
Elasticsearch专栏有2篇文章
deepin专栏有20篇文章
Linux专栏有0篇文章
Nacos专栏有1篇文章
springSecurity专栏有0篇文章
Mycat专栏有0篇文章
微服务相关专栏有0篇文章
spring专栏有8篇文章
OSS对象服务专栏有2篇文章
虚拟化专栏有0篇文章
Vmware专栏有9篇文章
Sentinel专栏有7篇文章
Kubernates专栏有1篇文章
总共有454篇文章


======访问了1次=======


https://blog.csdn.net/qq_41813208/article/details/102714006访问成功
https://blog.csdn.net/qq_41813208/article/details/104832869访问成功
https://blog.csdn.net/qq_41813208/article/details/107364891访问成功
总共有78个专栏
https://blog.csdn.net/qq_41813208/article/details/103003781访问成功
https://blog.csdn.net/qq_41813208/article/details/101079074访问成功
https://blog.csdn.net/qq_41813208/article/details/102575933访问成功
https://blog.csdn.net/qq_41813208/article/details/104420914访问成功
https://blog.csdn.net/qq_41813208/article/details/102714485访问成功
https://blog.csdn.net/qq_41813208/article/details/104418974访问成功
https://blog.csdn.net/qq_41813208/article/details/107379960访问成功
https://blog.csdn.net/qq_41813208/article/details/102710935访问成功
https://blog.csdn.net/qq_41813208/article/details/106844623访问成功
https://blog.csdn.net/qq_41813208/article/details/102693869访问成功
https://blog.csdn.net/qq_41813208/article/details/101167047访问成功
https://blog.csdn.net/qq_41813208/article/details/100269756访问成功
https://blog.csdn.net/qq_41813208/article/details/105480515访问成功
花生鼠的日常建站专栏有15篇文章
```
![https://blog.csdn.net/qq_41813208](https://blog.csdn.net/qq_41813208)
现已刷到了600多万

将示例的地址改成args[0] args[1]分别替代url和50，然后打包成jar运行
```bash
java -jar jar包路径 "https://blog.csdn.net/qq_41813208/" 50
```
https://human-resource-manage.oss-cn-shenzhen.aliyuncs.com/autoRefreshCSDN-1.0-SNAPSHOT-jar-with-dependencies.jar


### 增加点赞功能
由于csdn的登录功能我暂时没有做，通过postman模仿请求头，发现能实现手机+短信验证码的方式登录，过段时间再做这个功能
#### 第一步、添加用户的cookie
点赞功能需要用户的cookie，因此将登录了csdn账号的用户cookie复制出来
添加到`util.request.RequestUtils`中的  
```java
public static HashMap<String, String> getHeader() {
    //输入用户的cookie
    headers.put("cookie","传入cookie");

```
#### 第二步、调用写好的工具类`util.digg.DiggUtils`，将自己的个人博客主页替换成自己的

需要注意的是同一个用户一天点赞的次数又上限一般是30左右、可能还根据csdn账号等级限制
等级越高点赞的次数也越多，总之如果文章比较多，一天是无法完成所有文章的点赞行为，需要每天都运行程序。如果点赞过的文章则不会
再次点赞，会再控制台打印获取数据`点赞`该文章已经点赞过
没有点赞过的则会打印点赞后获得到的json数据

替换`blogerUrl`为自己的地址
```java
    public static void main(String[] args) {
        AccessUtils csdnUtils = new AccessUtils();//创建工具类，为了获取所有文章
        String blogerUrl = "https://blog.csdn.net/qq_41813208";//博主文章
        CopyOnWriteArraySet<String> allArticleUrl = csdnUtils.getAllArticleUrl(blogerUrl);//获取博主所有文章链接
        System.out.println(allArticleUrl);
        allArticleUrl.stream().parallel().forEach((url) -> {
            digg(url);
        });
    }
```





