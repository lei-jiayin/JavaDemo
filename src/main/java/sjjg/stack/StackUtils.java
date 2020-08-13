package sjjg.stack;


/**
 * 栈工具类
 *
 * @author adx
 * @date 2020/8/5 10:41
 */
public class StackUtils {

    public static ArrayStack<?> ss;

    /**
     * 将栈中元素全部出栈
     * @param a
     * @return
     */
    public static Object[] popAll(ArrayStack<?> a){
        ss = a;
        if (ss.isEmptyStack()){
            return null;
        }else {
            Object[] array = new Object[ss.top + 1];
            int i = 0;
            while (!ss.isEmptyStack()){
                array[i] = ss.popStack();
                i++;
            }
            return array;
        }
    }


    /**
     * 栈实现进制转换
     * @param num
     * @param hex
     * @return
     */
    public static String intToNHex(int num, int hex){
        if (hex <= 0 || hex > 36){
            return "请输入有效的进制";
        }else if (num == 0){
            return "0";
        }else if (num > 0){
            ArrayStack<Integer> stack = new ArrayStack<Integer>(16);
            int index = num;
            while (num != 0){
                num = num / hex;
                // 取余
                int ramainder = index % hex;
                stack.pushStack(ramainder);
                index = num;
            }
            Object[] objects = popAll(stack);
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<objects.length; i++){
                int in =(Integer) objects[i];
                if (in > 10){
                    char c = (char)('a'+in - 10);
                    sb.append(c);
                }else {
                    sb.append(objects[i]);
                }
            }
            return sb.toString();
        }else {
            num = -num;//先去负号
            ArrayStack<Integer> stack = new ArrayStack<Integer>(16);
            int index = num;
            while (num != 0) {
                num = num / hex;
                int remainder = index % hex;//取余压栈
                stack.pushStack(remainder);
                index = num;
            }
            Object[] o = popAll(stack);//弹栈取出余数
            StringBuilder sb = new StringBuilder();
            sb.append("-");//添加负号
            for (Object i : o) {
                int in = (Integer) i;
                //取出的数字如果>=10需要用字母代替
                if (in >= 10) {
                    char c = (char) ('a' + in - 10);
                    sb.append(c);
                } else {
                    sb.append(i);
                }
            }
            return sb.toString();

        }
    }

    public static void main(String[] args) {
        String a = intToNHex(8,2);
        System.out.println(a);
    }
}
