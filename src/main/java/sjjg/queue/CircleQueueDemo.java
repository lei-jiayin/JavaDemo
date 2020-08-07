package sjjg.queue;

import java.util.Scanner;

/**
 * 环形数组队列
 * 环形 与 取模(%)
 * @author adx
 * @date 2020/8/7 16:28
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        // 测试数组环形队列
        System.out.println("测试数组环形队列");
        CircleQueue circleQueue = new CircleQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加队列元素");
            System.out.println("g(get)：获取队列元素");
            System.out.println("h(head)：显示队列头元素");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    circleQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    circleQueue.addQuene(value);
                    break;
                case 'g':
                    int a = circleQueue.getQuene();
                    System.out.println("取出的数据是：" + a);
                    break;
                case 'h':
                    System.out.println("队列的头元素是：" + circleQueue.headQueue());
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("游戏结束！");
    }
}

class CircleQueue{
    private int maxSize;// 数组大小 实际最大有效数据 只有 maxSize - 1
    private int rear=0;// 负责加入 队尾 指向队列最后一个元素的后一个元素
    private int front=0;// 负责弹出 队头 指向队列的第一个元素
    private int[] arr;

    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 判满
     * @return
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    /**
     * 入队
     * @param n
     */
    public void addQuene(int n){
        if (isFull()){
            System.out.println("队列已满~");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 出队
     * @return
     */
    public int getQuene(){
        if (isEmpty()){
            System.out.println("队列为空~");
            throw new RuntimeException("队列为空！");
        }
        //front++;
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列中的所有数据
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + (rear + maxSize - front) % maxSize; i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    /**
     * 显示队头
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            System.out.println("数据为空~");
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
