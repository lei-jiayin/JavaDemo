package sjjg.queue;

import javax.sound.midi.SoundbankResource;

/**
 * 数组模拟队列
 *
 * @author adx
 * @date 2020/8/7 10:12
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        System.out.println(arrayQueue.isEmpty());
        System.out.println(arrayQueue.isFull());
        arrayQueue.addQuene(1);
        arrayQueue.addQuene(2);
        arrayQueue.addQuene(3);
        arrayQueue.addQuene(4);
        arrayQueue.addQuene(5);
        arrayQueue.addQuene(6);
        System.out.println(arrayQueue.isEmpty());
        System.out.println(arrayQueue.isFull());
        arrayQueue.showQueue();
        System.out.println(arrayQueue.getQuene());
        System.out.println(arrayQueue.isFull());
        System.out.println(arrayQueue.headQueue());
        arrayQueue.showQueue();
        arrayQueue.addQuene(8);
    }
}

/**
 * 队列类
 */
class ArrayQueue{
    private int maxSize;
    private int rear;// 负责加入 队尾
    private int front;// 负责弹出 队头
    private int[] arr;

    /**
     * 初始化队列
     * @param maxSize
     */
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        rear = -1;
        front = -1;
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
        if (front >= 0){
            if (rear == maxSize - 1){
                return false;
            }
        }
        return rear == maxSize - 1;
        //return (rear + 1) % maxSize == front;
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

        arr[++rear] = n;
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
        return arr[++front];
    }

    /**
     * 显示队列中的所有数据
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
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
        return arr[front + 1];
    }
}
