package gof23.singleton;

/**
 * 饿汉式 单例模式 立即加载 线程安全
 * @author adv
 * @date 2021/3/20 10:27
 */
public class SingletonDemo1 {
    // 类初始化时 立即加载（没有延时加载）这个对象 由于加载类时是天然的线程安全
    private static SingletonDemo1 instance = new SingletonDemo1();

    private SingletonDemo1() {
    }

    // 方法没有同步调用效率高
    public static SingletonDemo1 getInstance(){
        return instance;
    }
}
