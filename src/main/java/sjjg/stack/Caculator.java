package sjjg.stack;

import java.util.Scanner;

import static sjjg.stack.ArrayStack1.isOper;
import static sjjg.stack.ArrayStack1.propity;

/**
 * 计算器 中缀表达式
 * 完成输入一串 计算串 得出结果
 * @author adx
 * @date 2020/8/13 16:02
 */
public class Caculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String experion = scanner.next().trim();
        int res = caculator(experion);
        scanner.close();
        System.out.printf("运算表达式 %s = %d", experion, res);
    }

    /**
     * 简易版 10以内 无括号 正整数
     * @param experion
     * @return
     */
    private static int caculator(String experion) {
        System.out.println("开始计算：" + experion);
        // 数值栈
        ArrayStack1 numsStack = new ArrayStack1(10);
        // 运算符栈
        ArrayStack1 operStack = new ArrayStack1(10);

        int index = 0;
        int num1 = 0;//出栈1
        int num2 = 0;// 出栈2
        int oper = 0;// 操作符
        int res  = 0;// 运算结果
        String keepNum="";
        //入栈 * / + -
        char[] ec= experion.toCharArray();
        char ch;
        while (true){
            ch = experion.substring(index,index+1).charAt(0);
            // 判断是数值还是运算符
            if (isOper(ch)) {
                //运算符
                if (operStack.isEmptyStack()){
                    operStack.pushStack(ch);
                }else {
                    if (propity(ch) >= propity(operStack.peek())){
                        // 入栈操作符优先级是大于栈顶的操作符优先级
                        // 直接入栈
                        operStack.pushStack(ch);
                    }else {
                        // 优先级小于当前则 弹出值及操作符进行计算 在将结果入栈 且 将当前操作符入栈
                        num1 = numsStack.popStack();
                        num2 = numsStack.popStack();
                        oper = operStack.popStack();
                        res = numsStack.cal(num1,num2,oper);
                        numsStack.pushStack(res);
                        operStack.pushStack(ch);
                    }
                }
            }else {
                //数值
                //numsStack.pushStack(ch - 48);
                // 判断该值后面是否还有值
                // 加入keepNum 拼接字符串
                keepNum +=ch;
                if (index == experion.length() - 1) {
                    numsStack.pushStack(Integer.parseInt(keepNum));
                }else {
                    if (isOper(experion.substring(index+1,index+2).charAt(0))){
                        numsStack.pushStack(Integer.parseInt(keepNum));
                        keepNum="";
                    }
                }
            }
            index++;
            if (index >= experion.length()){
                break;
            }
        }

        while (true){
            if (operStack.isEmptyStack()){
                res = numsStack.popStack();
                break;
            }else {
                num1 = numsStack.popStack();
                num2 = numsStack.popStack();
                oper = operStack.popStack();
                res = numsStack.cal(num1,num2,oper);
                numsStack.pushStack(res);
            }
        }
        return res;

        //String[] str = new String[experion.length()];
        /*do {
            ch = experion.substring(index, index + 1);
            str[index] = ch;
            index++;
        } while (index < experion.length());
        ch = "";
        for (int i = 0; i < str.length; i++){
            if (i == 0){
                if ("-".equals(str[i])){
                    ch += str[i];
                }
            }
            if (Integer.valueOf(str[i]) >= 0 && Integer.valueOf(str[i]) <= 9){
                ch += str[i];
            }
        }*/

    }
}

class ArrayStack1 {
    public int maxSize;
    public int top;
    public int[] quen;

    /**
     * 初始栈
     * @param maxSize
     */
    public ArrayStack1(int maxSize){
        this.maxSize = maxSize;
        this.quen = new int[maxSize];
        this.top = -1;
    }

    public boolean pushStack(int a){
        if (isFullStack()){
            System.out.println("栈满");
            return false;
        }
        top ++;
        quen[top] = a;
        return true;
    }

    public int popStack(){
        if (isEmptyStack()){
            System.out.println("栈空");
            throw new RuntimeException("栈空");
        }
        int t = quen[top];
        top --;
        return t;
    }

    public int peek(){
        return quen[top];
    }

    public boolean isEmptyStack(){
        if (top == - 1){
            return true;
        }
        return false;
    }

    public boolean isFullStack(){
        if (top == maxSize - 1){
            return true;
        }
        return false;
    }

    public void list(){
        if (isEmptyStack()){
            System.out.println("栈空，没有数据~");
            return;
        }
        // 从栈顶显示数据
        for (int i=top; i>=0; i--){
            System.out.printf("quen[%d]=%d\n",i,quen[i]);
        }
    }

    //优先级的确认 优先级越高 值越大
    public static int propity(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是数还是运算符
    public static boolean isOper(int oper){
        return oper == '-' || oper == '+' || oper == '*' || oper == '/';
    }

    public int cal(int num1, int num2, int oper){
        int data = -1;
        switch (oper){
            case '+':
                data = num1 + num2;
                break;
            case '-':
                data = num2 - num1;
                break;
            case '*':
                data = num1 * num2;
                break;
            case '/':
                if (num1 == 0){
                    System.out.println("除数不能为0");
                    break;
                }
                data = num2 / num1;
                break;
            default:
                break;
        }
        return data;
    }
}
