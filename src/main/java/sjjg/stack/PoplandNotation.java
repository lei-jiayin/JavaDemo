package sjjg.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式(后缀表达式)计算器实现
 *
 * @author adx
 * @date 2020/8/17 10:02
 */
public class PoplandNotation {
    public static void main(String[] args) {
        //(3+4)*(5+7) 考虑 如何将中缀表达式转换为后缀表达式
       /* String suffixExpersion = "3 22 %";
        // 将String 转换为 ArrayList
        List<String> list = strToList(suffixExpersion);
        // 计算结果
        System.out.println(caculator(list));*/
       String infixExpersion = "1+((2+3)*4)-5";
       List<String> list = getInfixExpersionList(infixExpersion);
        System.out.println(list);
        System.out.println("得到后缀表达式");
        List<String> list1 = parseSuffixExpersion(list);
        System.out.println(list1);
        System.out.println(caculator(list1));
    }

    /**
     * 将中缀表达式转换为后缀表达式
     * @param ls 中缀
     * @return 后缀
     */
    public static List<String> parseSuffixExpersion(List<String> ls){
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item:ls){
            if (item.matches("\\d+")){
                // 判断item是否是数字
                //System.out.println("shuzi=" + item);
                s2.add(item);
            }else if ("(".equals(item)){
                // 判断是否是(
                s1.push(item);
            }else if (")".equals(item)){
                // 判断是否是)
                while(s1.size() != 0 && !s1.peek().equals("(")){
                    // 将(之后 )之前的数 弹出栈加入到s2
                    s2.add(s1.pop());
                }
                //System.out.println(s1.size());
                s1.pop();// 将(弹出栈
            }else {
                // 判断运算符优先级 栈顶运算符优先级高于加入的运算符优先级时 s1弹出 s2加入
                while (s1.size() != 0 && (getValue(s1.peek()) >= getValue(item))){
                    s2.add(s1.pop());
                }
                // 将未加入的运算符加入
                s1.push(item);
            }
        }
        // 将剩余的字符加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将中缀表达式转换成List
     * @param expersion 中缀表达式
     * @return list
     */
    public static List<String> getInfixExpersionList(String expersion){
        List<String> list = new ArrayList<String>();
        String str;
        int i=0;
        char c;
        do {
            if ((c=expersion.charAt(i)) < 48 || (c=expersion.charAt(i)) > 57){
                list.add(c+"");
                i++;
            }else {
                // 是一个数
                str="";
                while (i < expersion.length() && (c=expersion.charAt(i)) >= 48 && (c=expersion.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < expersion.length());
        return list;
    }

    /**
     * 将String 转换成 List
     * @param suffixExpersion 后缀表达式 以空格隔开
     * @return list
     */
    public static List<String> strToList(String suffixExpersion){
        String[] s = suffixExpersion.split(" ");
        return new ArrayList<String>(Arrays.asList(s));
    }

    public static int caculator(List<String> list){
        Stack<String> stack = new Stack<String>();
        for (String str : list){
            if (str.matches("\\d+")){
                stack.push(str);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(str)){
                    res = num1 + num2;
                }else if ("-".equals(str)){
                    res = num1 - num2;
                }else if ("*".equals(str)){
                    res = num1 * num2;
                }else if ("/".equals(str)){
                    res = num1 / num2;
                }else if ("%".equals(str)){
                    res = num1 % num2;
                }
                else {
                    throw new RuntimeException("运算符错误！");
                }
                stack.push("" + res);
            }
            //stack.push(str);
        }
        return Integer.parseInt(stack.pop());
    }

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String oper){
        int result = 0;
        //System.out.println("运算表达式=" + oper);
        switch (oper){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("运算符错误！");
                break;
        }
        return result;
    }


}
