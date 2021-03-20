package gof23.singleton;

/**
 * 静态内部类实现 单例模式 线程安全 调用效率高 延时加载
 * @author adv
 * @date 2021/3/20 10:37
 */
public class SingletonDemo4 {
    private static class SingletonDemo4Instance{
        private static final SingletonDemo4 instance = new SingletonDemo4();
    }
    private SingletonDemo4(){
    }
    public static SingletonDemo4 getInstance(){
        return SingletonDemo4Instance.instance;
    }

}
