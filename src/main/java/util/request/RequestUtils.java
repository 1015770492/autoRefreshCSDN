package util.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Set;

public class RequestUtils {
    public static HttpHeaders headers = new HttpHeaders();//创建请求头对象
    public static HttpEntity<String> httpEntity = getHttpEntity("",getHeader());

    /**
     * 构造的一个请求头
     * 需要替换cookie
     *
     * @return
     */

    public static HashMap<String, String> getHeader() {
        HashMap<String, String> headers = new HashMap<>();

        //输入用户的cookie
        headers.put("cookie","传入cookie");


        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
        return headers;
    }

    /**
     * 设置请求头，包括cookie
     * 返回一个请求体对象
     *
     * @param headerMap
     * @return
     */
    public static HttpEntity<String> getHttpEntity(String jsonString,HashMap<String, String> headerMap) {
        Set<String> set = headerMap.keySet();
        set.forEach((key) -> {
            headers.add(key, headerMap.get(key));//添加请求头
        });
        if (jsonString==null){
            jsonString="";
        }
        return new HttpEntity<String>(jsonString, headers);
    }


}
