package test;

import java.util.ArrayList;
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
      s = quFirstAndLast(s);
      System.out.println(s);
    }

    public static String listToString(List<String> arr){
        return String.join(",",arr);
    }

   /* public static List<List<Map<String,Object>>> jsonToListList(String json){

    }*/

    /**
     * 去掉首尾
     * @param str
     * @return
     */
    public static String quFirstAndLast(String str){
        if (str.length() > 2){
            return str.substring(1,str.length() - 1);
        }else {
            return null;
        }
    }

    public static String quQuot(String str){
        /*
        * [[{&quot;lng&quot;:114.34394272873244,&quot;lat&quot;:30.551567195600565},{&quot;lng&quot;:114.34419494412191,&quot;lat&quot;:30.551567195600565},{&quot;lng&quot;:114.34419494412191,&quot;lat&quot;:30.551345133099872},{&quot;lng&quot;:114.34394272873244,&quot;lat&quot;:30.551345133099872}],[{&quot;lng&quot;:114.34398727610491,&quot;lat&quot;:30.55129151236107},{&quot;lng&quot;:114.34419365251722,&quot;lat&quot;:30.55129151236107},{&quot;lng&quot;:114.34419365251722,&quot;lat&quot;:30.551137448818537},{&quot;lng&quot;:114.34398727610491,&quot;lat&quot;:30.551137448818537}]]
        * */
        return str.replaceAll("&quot;","\"");

    }

    public static String sub(String str, int begin, int end){
        return str.substring(begin,end);
    }
}
