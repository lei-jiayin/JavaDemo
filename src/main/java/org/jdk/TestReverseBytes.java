package org.jdk;

/**
 * 测试一下 reverseBytes 的结果
 * @author xw
 * @date 2020/7/1 9:46
 */
public class TestReverseBytes {

    public static void main(String[] args) {
        int i = -8;
        int i1 = Integer.reverseBytes(i);
        System.out.println(i1);

        int signum = Integer.signum(i);
        System.out.println(signum);
    }

}
