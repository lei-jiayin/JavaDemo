package test.java8test;

/**
 * @author adv
 * @date 2020/11/19 10:46
 */
public class Caculate {
    public Caculate() {
    }

    public static void main(String[] args) {
        MathOperation mathOperation = (a,b) -> a-b;
        MathOperation add = (a,b) -> a + b;
        MathOperation dvi = (int a,int b) -> a + b;
        MathOperation chu = (a,b) -> {return a / b; };
        Caculate caculate = new Caculate();
        int res = caculate.operate(10,5,mathOperation);
        System.out.println(res);
    }
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
