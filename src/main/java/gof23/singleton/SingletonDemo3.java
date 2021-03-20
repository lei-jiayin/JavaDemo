package gof23.singleton;

/**
 * 双重检测锁实现 单例模式 因为底层原因偶尔出问题 弹幕说加volatile 关键字代替synchronized 会更好
 * @author adv
 * @date 2021/3/20 10:37
 */
public class SingletonDemo3 {
    private static SingletonDemo3 instance;
    private SingletonDemo3() {
    }
    public static synchronized SingletonDemo3 getInstance(){
        if (instance == null){
            SingletonDemo3 sd;
            synchronized (SingletonDemo3.class){
                sd = instance;
                if (sd == null){
                    synchronized (SingletonDemo3.class){
                        if (sd == null){
                            sd = new SingletonDemo3();
                        }
                    }
                    instance = sd;
                }
            }
        }
        return instance;
    }
}
