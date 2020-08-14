package util.favorite;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import util.accessArticle.AccessUtils;
import util.request.RequestUtils;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectUtils {


    public static void main(String[] args) {


        //举例一
        Consumer consumer = (x) -> System.out.println(x);
        Consumer consumer2 = System.out::println;

        //举例二
        BinaryOperator<Double> operator=(x,y)->Math.pow(x,y);
        BinaryOperator<Double> operator2=Math::pow;

        class Myclass{
            Myclass(String x){
                System.out.println("有参构造");
            }
        }

        Function<String,Myclass> fun1=(n)->new Myclass(n);
        Function<String,Myclass> fun2=Myclass::new;


//        AccessUtils csdnUtils = new AccessUtils();//创建工具类，为了获取所有文章
//        String blogerUrl = "https://blog.csdn.net/qq_41813208";//博主文章
////        String blogerUrl = "https://blog.csdn.net/ywl470812087";//博主文章
//        CopyOnWriteArraySet<String> allArticleUrl = csdnUtils.getAllArticleUrl(blogerUrl);//获取博主所有文章链接
//        allArticleUrl.forEach((articleUrl) -> {
//            JSONObject jsonObject = setFavoriteInfo(articleUrl, "");
//            System.out.println(jsonObject);
//
//            HttpEntity<String> httpEntity = RequestUtils.getHttpEntity(jsonObject.toJSONString(), RequestUtils.getHeader());
//            String url = "https://me.csdn.net/api/favorite/create";//收藏文章的api
//            System.out.println("===========");
//            System.out.println(httpEntity);
//            ResponseEntity<String> forEntity = new RestTemplate().exchange(url, HttpMethod.POST, httpEntity, String.class);
//            if (forEntity.getStatusCode() == HttpStatus.OK) {
//                System.out.println(forEntity.getBody());
//            }
//        });



    }

    public static JSONObject setFavoriteInfo(String url, String collectionName) {
        JSONObject jsonObject = new JSONObject();
//        url="https://blog.csdn.net/qq_41813208/article/details/107777623";

        String[] split = url.split("blog.csdn.net/");
        String id = split[1].split("/article/details/")[1];
        jsonObject.put("url", url);
        jsonObject.put("source_id", id);
        jsonObject.put("title", id);
        jsonObject.put("author", split[1].split("/article/details/")[0]);//作者
        jsonObject.put("source", "blog");
//        JSONArray jsonArray = getCollectionName();//获取收藏夹
//        List<JSONObject> collect = null;
//        if (collectionName == null || collectionName.equals("")) {//如果没有指定收藏夹名
//            if (null != jsonArray.getJSONObject(0)) {
//                collect = List.of(jsonArray.getJSONObject(0));//默认第一个文件夹
//            } else {
//                System.out.println("收藏夹为空，需要创建收藏夹");
//            }
//        } else {
//            collect = jsonArray.stream().map((obj) -> {
//                return (JSONObject) obj;
//            }).filter((json) -> {
//                System.out.println(json);
//                return json.getString("Name").equals(collectionName.strip());
//            }).limit(1).collect(Collectors.toList());//获取指定的收藏夹
//        }


        return jsonObject;
    }


    public static JSONArray getCollectionName() {
        String url = "https://me.csdn.net/api/favorite/folderListWithCheck?url=https://blog.csdn.net/qq_41813208/article/details/107651652";//这个url随便，关键在于cookie
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, RequestUtils.httpEntity, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            return jsonObject.getJSONObject("data").getJSONArray("result");
        } else {
            System.out.println("状态码：" + responseEntity.getStatusCode() + " 获取数据数据失败");
            return new JSONArray();
        }

    }

}
