package gof23.singleton;

/**
 * 懒汉式 单例模式 调用效率低 延迟加载
 * @author adv
 * @date 2021/3/20 10:37
 */
public class SingletonDemo2 {
    // 类初始化时 不初始化（延时加载，在用时加载）这个对象
    private static SingletonDemo2 instance;

    private SingletonDemo2() {
    }
    //方法同步 效率低
    public static synchronized SingletonDemo2 getInstance(){
        if (instance == null){
            return new SingletonDemo2();
        }else {
            return instance;
        }
    }
}
