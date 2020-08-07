package org.testF;

/**
 * 查看求余的变化
 * 结果：对x求余 余数为 0 ~ x-1
 * @author xw
 * @date 2020/7/6 15:52
 */
public class TestBFH {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++){
                System.out.println(i % 7);
                System.out.println("--------i值=" + i + "," + "当前类=TestBFH.main()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
