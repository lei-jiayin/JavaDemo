package sjjg.stack;

import lombok.Data;

import java.util.Scanner;
import java.util.Stack;

/**
 * 单链表模拟栈
 *
 * @author adx
 * @date 2020/8/13 14:01
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        System.out.println("单链表模拟栈的测试");
        LinkedListStack linkedListStack = new LinkedListStack();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop){
            System.out.println("list(l)：打印栈的数据");
            System.out.println("push(p)：入栈");
            System.out.println("pop(o)：出栈");
            System.out.println("exit(e)：退出");
            key = scanner.next().charAt(0);
            switch (key){
                case 'l':
                    linkedListStack.reversePrint();
                    break;
                case 'p':
                    try{
                        System.out.println("请输入入栈值：");
                        int data = scanner.nextInt();
                        linkedListStack.push(data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'o':
                    try{
                        int data = linkedListStack.pop();
                        System.out.println(data);
                    }catch (RuntimeException e){
                        e.getMessage();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 单链栈
 * 尾部插入，逆序打印
 * 还有一个方法是 头插法，每插入一个数都插入链表的最前面，
 * 入栈：StackNode sn = head.next; head.next = newNode; newNode.next = sn;
 * 出栈：int data = head.next.data; StackNode sn = head.next.next; head.next = sn;
 * 遍历：单链表常规遍历
 */
class LinkedListStack{
    public StackNode head= new StackNode();
    //public int maxSize;
    //public int top;

    // 判断栈空
    public boolean isEmpty(){
        if (head.next == null){
            return true;
        }
        return false;
    }

    /**
     * 入栈
     * @param data
     */
    public void push(int data){
        StackNode stackNode = new StackNode(data);
        StackNode tmpe = head;
        while (true){
            if (tmpe.next == null){
                break;
            }
            tmpe = tmpe.next;
        }
        tmpe.next = stackNode;
        stackNode.pre = tmpe;
    }

    /**
     * 出栈
     * 这里使用的是双向链表(单向链表也可以实现栈，但需要多定义一个指针 指向链表倒数第二个节点好用来从链表中删掉该节点达到出栈的目的)
     * @return
     */
    public int pop(){
        if (isEmpty()){
            System.out.println("栈空，出栈失败~~");
            throw new RuntimeException("栈空，出栈失败~~");
        }
        StackNode stackNode = head;
        while (true){
            if (stackNode.next == null){
                break;
            }
            stackNode = stackNode.next;
        }
        int data = stackNode.data;
        stackNode.pre.next = null;
        stackNode.pre = null;
        return data;
    }

    /**
     * 从栈顶显示栈数据，倒叙打印，从栈顶到栈底
     */
    public void reversePrint(){
        if (isEmpty()){
            System.out.println("栈空，打印失败！");
            return;
        }
        StackNode tmpe = head.next;
        Stack<StackNode> stack = new Stack<StackNode>();
        while (tmpe != null){
            stack.push(tmpe);
            tmpe = tmpe.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}

/**
 * 栈节点（双向）
 */
@Data
class StackNode{
    int data;
    StackNode next;
    StackNode pre;
    public StackNode(){
    }
    public StackNode(int data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "data=" + data +
                '}';
    }
}