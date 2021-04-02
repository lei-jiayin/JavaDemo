package likou;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1021. 删除最外层的括号 栈
 * @author adv
 * @date 2021/4/1 10:30
 */
public class RemoveOutermostParentheses {
    /**
     * (()())(()) -> (()())+(()) -> ()()()
     * @param S
     * @return
     */
    public static String removeOuterParentheses(String S) {
        //第一步 将串转为原语
        Deque<String> deque = new ArrayDeque<>();
        String[] chars = S.split("");
        for (int i=0;i<chars.length;i++){
            deque.push(chars[i]);
            // System.out.println(chars[i]);
        }
        StringBuilder b= new StringBuilder();
        while (!deque.isEmpty()){
            String a = deque.pop();
            if ((a + deque.peek()) == "()"){
                b = new StringBuilder(a + deque.pop());
            }else {
                b.append(deque.pop());
            }

        }

        return b.toString();
    }

    public static String removeOuterParentheses1(String S) {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for (char c : S.toCharArray()) {
            if (c == ')') --level;
            if (level >= 1) sb.append(c);
            if (c == '(') ++level;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "(()())(())";
        System.out.println(removeOuterParentheses1(s));
    }
}
