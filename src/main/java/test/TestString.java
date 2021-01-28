package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试字符串长度截取
 *
 * @author adx
 * @date 2020/8/26 16:55
 */
public class TestString {
    public static void main(String[] args) {
       /* String str=",111,79,1,0";
        // 截取从1到第2个逗号的位置
        String a1 = str.substring(1,str.indexOf(",",str.indexOf(",")+1));
        System.out.println(a1);*/
        String str = "[[{&quot;lng&quot;:114.34394272873244,&quot;lat&quot;:30.551567195600565},{&quot;lng&quot;:114.34419494412191,&quot;lat&quot;:30.551567195600565},{&quot;lng&quot;:114.34419494412191,&quot;lat&quot;:30.551345133099872},{&quot;lng&quot;:114.34394272873244,&quot;lat&quot;:30.551345133099872}],[{&quot;lng&quot;:114.34398727610491,&quot;lat&quot;:30.55129151236107},{&quot;lng&quot;:114.34419365251722,&quot;lat&quot;:30.55129151236107},{&quot;lng&quot;:114.34419365251722,&quot;lat&quot;:30.551137448818537},{&quot;lng&quot;:114.34398727610491,&quot;lat&quot;:30.551137448818537}]]";
        String s = quQuot(str);
//      s = quFirstAndLast(s);
        System.out.println(s);
        List<List<Map<String, Object>>> jsonArray = string2listlist(s);
        System.out.println(jsonArray);
        System.out.println(jsonArray.size());
    }

    public static String listToString(List<String> arr) {
        return String.join(",", arr);
    }

    /**
     * 去掉首尾
     *
     * @param str
     * @return
     */
    public static String quFirstAndLast(String str) {
        if (str.length() > 2) {
            return str.substring(1, str.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * 将jsonArray(jsonArray) 转换为list(list)
     * @param str json字符串
     * @return List<List<Map<String, Object>>>
     */
    public static List<List<Map<String, Object>>> string2listlist(String str) {
        List<List<Map<String, Object>>> lati = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(str);
        for (Object obj : jsonArray) {
            List<Map<String, Object>> list = new ArrayList<>();
            JSONArray jsonArray1 = (JSONArray) obj;
            for (Object obj1 : jsonArray1) {
                Map<String, Object> map = new HashMap<>();
                JSONObject jsonObject = (JSONObject) obj1;
                System.out.println(jsonObject.getString("lng") + ":" + jsonObject.getString("lat"));
                map.put("lng", jsonObject.getString("lng"));
                map.put("lat", jsonObject.getString("lat"));
                list.add(map);
            }
            lati.add(list);
        }
        return lati;
    }

    public static String quQuot(String str) {
        /*
         * [[{&quot;lng&quot;:114.34394272873244,&quot;lat&quot;:30.551567195600565},{&quot;lng&quot;:114.34419494412191,&quot;lat&quot;:30.551567195600565},{&quot;lng&quot;:114.34419494412191,&quot;lat&quot;:30.551345133099872},{&quot;lng&quot;:114.34394272873244,&quot;lat&quot;:30.551345133099872}],[{&quot;lng&quot;:114.34398727610491,&quot;lat&quot;:30.55129151236107},{&quot;lng&quot;:114.34419365251722,&quot;lat&quot;:30.55129151236107},{&quot;lng&quot;:114.34419365251722,&quot;lat&quot;:30.551137448818537},{&quot;lng&quot;:114.34398727610491,&quot;lat&quot;:30.551137448818537}]]
         * */
        return str.replaceAll("&quot;", "\"");

    }

    public static String sub(String str, int begin, int end) {
        return str.substring(begin, end);
    }
}
