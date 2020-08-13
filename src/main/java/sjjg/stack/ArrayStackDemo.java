package sjjg.stack;

/**
 * 数组实现栈
 *
 * @author adx
 * @date 2020/8/12 16:06
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>(5);
        arrayStack.pushStack(1);
        arrayStack.pushStack(2);
        arrayStack.pushStack(3);
        arrayStack.pushStack(7);
        System.out.println(arrayStack.isFullStack());
        arrayStack.list();
        Integer a = arrayStack.popStack();
        System.out.println(a);
        arrayStack.list();
    }
}
