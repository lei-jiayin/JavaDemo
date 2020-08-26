package test;

/**
 * 测试字符串长度截取
 *
 * @author adx
 * @date 2020/8/26 16:55
 */
public class TestString {
    public static void main(String[] args) {
        String str=",111,79,1,0";
        System.out.println(str.indexOf(","));
        System.out.println(sub(str,str.indexOf(",")+1,str.length() - 7));
    }

    public static String sub(String str, int begin, int end){
        return str.substring(begin,end);
    }
}
