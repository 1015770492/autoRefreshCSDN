package util.login;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * 登录工具类
 */
public class LoginUtils {
    private static HashMap<String, String> header = new HashMap<>();

    public static void main(String[] args) {
        String phone="15727742367";
//        String phone = "18279774965";
//        JSONObject jsonObject = sendVerifyCode(phone);

        loginByPhoneAndSMSCode(phone, "yjh1234567890");
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     */
    public static JSONObject sendVerifyCode(String phone) {
        String api = "https://passport.csdn.net/v1/register/pc/sendVerifyCode";
        JSONObject json = new JSONObject();
        json.put("type", 1);
        json.put("code", "0086");
        json.put("mobile", phone);
        HttpEntity<String> request = new HttpEntity<String>(json.toString(), null);
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(api, request, String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        System.out.println(headers);
        List<String> list = headers.get("Set-Cookie");
        return JSONObject.parseObject(responseEntity.getBody());//返回发送短信验证码的消息
    }

    public static JSONObject loginByPhoneAndSMSCode(String phone, String SmsCode) {


        String api = "https://passport.csdn.net/v1/register/pc/login/doLogin";
        JSONObject userInfo = new JSONObject();
        userInfo.put("loginType", 1);
        userInfo.put("pwdOrVerifyCode", SmsCode);//短信验证码
        userInfo.put("code", "0086");//大陆
        userInfo.put("userIdentification", phone);
        userInfo.put("uaToken", "");
        userInfo.put("webUmidToken", "");

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
//        headers.add("cookie","uuid_tt_dd=10_19936288850-1597265590010-685128");
        HttpEntity<String> request = new HttpEntity<String>(userInfo.toString(), headers);
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(api, request, String.class);
        System.out.println(responseEntity.toString());
//        System.out.println(responseEntity.getHeaders());
        return JSONObject.parseObject(responseEntity.getBody());//返回发送短信验证码的消息
    }

}
