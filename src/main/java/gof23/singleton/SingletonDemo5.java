package gof23.singleton;

/**
 * 枚举实现 单例模式 线程安全 避免反射反序列化 无延时加载  效率高
 * @author adv
 * @date 2021/3/20 10:37
 */
public enum SingletonDemo5 {
    // 这个枚举元素 本身就是单例对象
    INSTANCE;

    //添加自己需要的操作
    public void singletonOperation(){

    }
}
