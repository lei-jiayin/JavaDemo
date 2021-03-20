package gof23.singleton;

/**
 * @author adv
 * @date 2021/3/20 11:07
 */
public class Client {
    public static void main(String[] args) {
        SingletonDemo4 s1 = SingletonDemo4.getInstance();
        SingletonDemo4 s2 = SingletonDemo4.getInstance();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
