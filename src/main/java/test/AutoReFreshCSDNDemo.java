package test;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class AutoReFreshCSDNDemo {


    public static void main(String[] args) throws Exception {
//        AccessUtils csdnUtils = new AccessUtils();
//        csdnUtils.autoRefresh("https://blog.csdn.net/ywl470812087", 65);//csdn设置了时间大概1分钟
//        AccessUtils csdnUtils2 = new AccessUtils();
//        csdnUtils2.autoRefresh("https://blog.csdn.net/qq_41813208/", 65);//csdn设置了时间大概1分钟


        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("https://passport.csdn.net/login", String.class);
//        HttpHeaders headers = forEntity.getHeaders();
//        System.out.println(headers.toString());//打印请求头
//        List<String> cookies = headers.get("Set-Cookie");
//        AtomicReference<String> cookieStr = new AtomicReference<>("");
//        Stream<String> stringStream = cookies.stream().map((cookie) -> {
//            return cookie.split(";")[0] + ";";
//        });
//        stringStream.forEach((x) -> {
//            cookieStr.set(cookieStr.get() + x);
//        });



        ResponseEntity<String> cnvEntity = new RestTemplate().exchange("https://passport.csdn.net/v1/api/riskControl/checkNVC?nvcValue=%7B%22a%22%3A%22FFFF00000000016C467E%22%2C%22c%22%3A%221597336775326%3A0.4519652966517216%22%2C%22d%22%3A%22nvc_login%22%2C%22h%22%3A%7B%22key1%22%3A%22code0%22%2C%22nvcCode%22%3A400%2C%22umidToken%22%3A%22T2gA0oTI109434WC2q1RggMpJxcliSKF20IsNmavHsayXYblMQjobBFJAboGzFyFOflwS9Pqjq7qjnA7GuY8TgUH%22%7D%2C%22j%22%3A%7B%22test%22%3A1%7D%2C%22b%22%3A%22134%233AX03XXwXGirLIZ%2FUgCBoX0D3QROwKO9sE%2Fw4%2FPnTe5tw9yGueOLvyo31G5yyM64VKLkvraWxAubIqMkGoQeKeX6X%2BGYulmTw%2FPCnVkq0cUyrRb40%2FFt%2BTq6IgL7oJJqqH8iZzi%2BQWLFCqpAZtitzIxXqqK8oZG%2BXcp1qyCrI648p8fJZtXt%2BJWwqao5oXowqTVWMGCSyS6GJgIjhfabSknCjO%2FgMMqxKkPyIsJLV774oh1SE1S%2FpghghSrgoKM%2FOgz7ijdcy3Ntohrw%2FAGeTl7VQD0m9hLjdm4QHsVCWosHK8lKYHYu%2B0UqPfjgtPb9vXENnHSf3MpgP89UnFwmNKfRDb5rgVqF0yT8ia%2BfRzV45cSYvtNd5es8OT24%2BMturi73rgJbtbVWMXqrRGI%2FM88%2BKShQTD%2BNW0xh1JvQg7S4FRdN%2BIyn6U%2Bb8q7kz6Mo%2FvsPQXF70zjUx30LB%2BwacByhxxrfJA%2FM18rmIZ2IHElWzaER1IUSVr3ldz82kID%2FZuEKl6ZeR%2FF5dAozLdpE8w4Ma9lRJ0YLhHomlG8em1W9%2FdyUdrbZMLs3wkN7pzWVkk1fggy2C6yF%2BoF7wBmeHGOH7jVT14nDWzTwZWDeSw%2BTuMIjM%2FcptuRg08fIZR4KMVsD3iiTDOKJF7uOIjWj2CEMudoGv4j09CRR4NUzMSS8t8sxBWRfxHH7aHmdQXt%2Bff61BsMTJ5yd2661YWBkzUAejRNyjCOg5acpGUeluAsqRwznTMEx17kTGb12ljZZroqD41B27rtv0OFX2MkaFpYh3glzvv1d4PYmXhTJQkRAznOWhS84eu0ItAU7vx2TqYpkvN%2FjCLUc16C4M3kY9WeolTuF7JkZeLhmIoKFpdUBxBkkGu3ksyXz6iw8Cxfm4iHh5uDj5Jw8JWo68OUbe9TMRYYJUZVWa4CZG%2BVNkI0pOykeWJjQ3IFEDI9lbF94ZnqmapwdV1WneUxGSFtVVlph%2FLlrjD5VrHM2u1gxwjip922rrbWmaePEltj9Ok1CTrnzzEFcheA1qjYFuzigLxe%2BDNHhyrvI%2Fp9jV2ImuNzVNq2zLCcoru2z9PnqMNrg7AELbv5e9xyRt%2FQf%2F2EWbqoV95m9MXWWB5k708rwXu13kHjm5oRDlCRb3Iy0xWDcXHIKP8imTBim4OqsdWFK7vnhYlq9wxBMkaYxU4Kt4qk7%2FaToUgJHe7c2ROoRCdj2MFhSNzF8xLpjOxIvYdFvhmO%2BdpxsVGikUdNHqeaKMyi6NjBCA3X49Gc6ipOMtBYR170TZDZXXKEDZ5fFO0qJusNO%2BwqnHhmLs2S51hZ6DdTKd3JBq%2B3SmmYaeioWmMG5fRbNz3JcS9tTGMyvL%2FIazpe%2B1PAVOC8lBoxnC2Um8xxqJ9FM5mkRvIR71549%2FMCpJItU6OBdRxzkdnqLbCKqk9OL2aODw2A%2Bfj1KCnBOgqwPQ%2FBA7YXdOZWCLvD8AS54oF9cUtU6r%2FABV9bX25VYyLpeUuzUGGxQL5h5kumW195i%2FLF3G8kMiGB3a9cARV%2FrShi5xOZMFVlR9LwL%2FCVrYndOFp6va66Ek%2BF4qS6s%2BdnT9%2B93VQc4t%2FYVOBpGViXl7mG5U7vIfHX%2BOC3m9nASTpJRl2%2FE1JG3Z9MgXTs8omjbLKITOPg97xZJOub0NWunuJOQyxe5W%2FBA3uFt2wj%2BJHDNDyIhKJ2KKC%2BqYfoN7oJWf6Gx%2B%2Bx%2BWX8PWh8HmXtW%2FZytwIBqXZBR9HGbUWuyu%2B7RwwJLRR5a9z00RPohYA0Ulg6Dg8CyJk1jNgi8B3TUYuwVkhm4wuW23SbIGFudMb8iQ4sKRt4fY%2F7p7LoYp1ZRyZOhPpSNp5nZxM0DyIK67xbstE9sQG3XM88fXL9JqrAONNYhUuldcdtsdyCvmy%2FvgSoSqauYaSZBei8a0rMUcuPeY3iQC%2FAC80AF3cOFa97xNzn5jKCsboTcqe1dUwe1ElzplYDDt0ve6E6JiwWsL6i1aq2pOqWXDrPzh%2FzDLxtRD%2BSQhIgvsgKWrer%2BOHOqmIbblQ70PQDWLt0tOxMhORlGL6iw8zJHITWl8Flqr0AXa2GNmgMkK6HEVumQG8OFz8hT5abF3tuN6Bpe7DeF%2BmGT%2FX%3D%3D%22%2C%22e%22%3A%22E59TSDCgh4xti_mXGHNfIGVo9SDqvhQqzTLmgFihqgp9tuEUdiugeouLEJG0uvIa-9Z-k-lQ6fL3lpbcTxPN4fk9Wu-Q71ExCDYni5eDYwVSG_W5NXXiw0SnMK_GknTTTFhOiVDaaNOdAoCLDoRnww3KjFr3yugF92wGhTMN8b4Hcr_04k9jbrPa-eGRlbAkYizM9JlKkpiqJYbEB2zQGA%22%7D&callBackMethod=jsonp_0607518340006429&source=pc_password", HttpMethod.GET, null, String.class);
        System.out.println("cnv:");
        System.out.println(cnvEntity.getHeaders());
        List<String> list = cnvEntity.getHeaders().get("Set-Cookie");
        AtomicReference<String> cookieStr2 = new AtomicReference<>("");
        Stream<String> stringStream2 = list.stream().map((cookie) -> {
            return cookie.split(";")[0] + ";";
        });
        stringStream2.forEach((x) -> {
            cookieStr2.set(cookieStr2.get() + x);
        });
        System.out.println("\n");

        //1.设置请求头
        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.add("cookie", cookieStr2.get());
        loginHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36");
        loginHeaders.add("accept", "application/json, text/plain, */*");
        loginHeaders.add("origin", "https://passport.csdn.net");
        loginHeaders.add("referer", "https://passport.csdn.net/login?code=public");
//        loginHeaders.add("sec-fetch-dest", "empty");
//        loginHeaders.add("sec-fetch-mode", "cors");
//        loginHeaders.add("sec-fetch-site", "same-origin");
        loginHeaders.add("x-requested-with", "XMLHttpRequest");
        Instant instant = Instant.now();//获取时间戳
        String s = String.valueOf(instant.toEpochMilli());
        loginHeaders.add("X-Tingyun-Id", "im-pGljNfnc;r="+s.substring(4, s.length()));
        System.out.println(loginHeaders);
        //2.设置请求体
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginType", "1");
        jsonObject.put("userIdentification", "15727742367");
        jsonObject.put("pwdOrVerifyCode", "yjh1234567890");
        jsonObject.put("uaToken", "134#q6XtMJXwXGiu0xoqU6nsoX0D3QROwKO9sE/w4/PnTe5tw9yGueOLvyo31G5yyCte2HWAUF7ig/EngnRrgPHsHZz0H0lKOPy5oobd6T2OUvpMx6BPggwbK6owqqX3IkuU+4qAqvg1Ogwxzsefqc77UJjwI64qyyvU9Xn+qj5LTvX0XHpRKqo5oXGw5XRSAyvj+WxBXH7YwtJwLgFXqFHR+JXw6xc1ALfftg4KycRvZGqn4X4Kt6J8oYJMXT6kBdvUVEhMXT1AZtw44Tv2qq75oJJwqjK298Z26givXOOz/lasOL0DM2bL+dIltbss05nPJ8/U/feYL9uHQjlkm4VSPfPfR53Ow/w7l8mx8OG8/gSCDffrfvIkG3bXjqyJjWxNlungunSbMpfsSV4RjH8SsaQrzDY3OxrWSxfnbKM3oDhxc0b5xlA9Fgiwt/a48iWIBpvePutDKSe8geaIGjD3ERcRrbHewfBeHk60cChn7EroLp+J19+gPOvblKcFTV3Q2RzrI6FFLT5EneASfmRqJQxwIOYnvw7syE9TDL7nYGwJjWgnwNEvpp1cHfQp84CGxqVHJIDDZPvMh9QLCadiBvtMG+xkoYtgFrYhvh1YFR0aYEF0VFLshDvbdKpySmn96w71i/VaXOy9J52T7nbvp7WL1HnTwFcyVYZaTQygzxEQ5kRfqzfmigdjZ11/kmpkp6JgAhVnn4nR7d2ACPcytow6VRhi5+GMHs2haZeoHWNvax9m/YeXHAvAzOjQsd0g499WKYqe9JfG6CANd06DPjfd8+Suth6y84cfC/aAYoXtpmX2CDEzSj2dyFB753Vt7oPVUYpK03trzODv1px/JmRCbPlSGnnSBNSAD8w9NIJsJwKwrerJZp7i2gr4C1Wx+ea744zS0Qxdw8y944IQURsq6ufv5AySfjDYQ7TpWxIPeu024UBh7DAdXIWecweY9hUvtCNEd71xikfMEfAVImaKco4fdPz98/uFL56lZeOgh4sY++UoCTeVeBMKQWMdZVoNNMNcqX4EllXzEBpzHhW73vFKlGBDKbluWBoYGqNwojG39zjzvXeUutBzqSk/R5kqi+auPZkX6Hnkc1kZznKAL1lK9RHxpkYTFcXRPpfajQXiMOsT4UvZy1LhV1nAeIewlA7PXXD9/pfFUtrUxzyNIakD08f9gsc/6AbR517mzHoRRp4HjHhw542UT6BU4ciyKxT/puzvkl5hpTAnxLfZ7qBg3cfiVjeaPhpOXylM2qToDkazpfcShW0UJCraB6Qur81zlqX34TPdXfsa70m5pYV9XY0Owtl6yb3cW5ythx4hfJQBbd31XqLQ0sDsYxlDTm9tArtHU5eLCR11oJk1rsuw49sxqa/9yAeDIxiwHb0Q7q4KJjTTv6DdXgDdnCXXABvevWklwPgAMAQPtg1D7o8Qhfztwhj5wmgms93UEQzqgEgw44c9hsAWRSZ1OQVvlrpUl+kqSdRruoCHV3QFfF4PJ54Ob+65QLhC/G+JqadhAsFr/xir7JzIDr39bb/7vylP5web+RL21uXTklq/OQNAly8qJRzk3/ZJwGbd7RW2P7ELffHSWhbepvvmO5E8bSKW0WtsrBQptquui5olr8/SITWPmouF/EsOmvJ8uz6k0mu93HhmmN3vCLbTFx/4xe3hd6UEy8CPcBg/K/7eOgm9EqJ68qIBFHMdtxodtikhzgfAiBwVBWlfiAsMP6E37362biKTL5niFjtQGWuHyY7w1lvS57I740j2LHSlf7rMZlB9/CrNy9+gQFv96EGFDJSicPM5obBo9jF7sp9FLp+nOTjUvH/8LT/TXFxsM8CtYB0ueWA3T1LhNPeFI6==");
        jsonObject.put("webUmidToken", "");

        System.out.println(jsonObject.toString());


        //3.加入登录请求头 和 请求体
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject.toJSONString(), loginHeaders);
        //4.发送请求
        ResponseEntity<String> loginEntity = new RestTemplate().exchange("https://passport.csdn.net/v1/register/pc/login/doLogin", HttpMethod.POST, httpEntity, String.class);

        System.out.println("==============");
        System.out.println(loginEntity.getHeaders());
        System.out.println("-------------------");
        System.out.println(loginEntity.getBody());




    }

}
