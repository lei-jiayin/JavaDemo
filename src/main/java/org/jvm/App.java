package org.jvm;

/**
 * @author xw
 * @date 2020/6/29 11:50
 */
public class App {

    // 栈帧 有多少个方法就有多少栈帧


    public int add(){
        int a = 1;
        int b = 2;
        int c = (a + b) * 100;
        return c;
    }

    public static void main(String[] args) {
        App app = new App();
        int add = app.add();
        System.out.println(add);
    }
}
