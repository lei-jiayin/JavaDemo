package sjjg.linkedlist;

import lombok.Data;

/**
 * 约瑟夫 环 出圈问题
 *
 * @author adx
 * @date 2020/8/12 11:40
 */
public class JosepHu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleLinkedList = new CircleSingleLinkedList();
        circleLinkedList.add(25);
        circleLinkedList.showBoy();

        circleLinkedList.countBoy(1,7,25);//24153
    }
}

/**
 * 单向环形链表
 */
class CircleSingleLinkedList{
    public Boy first = null;

    /**
     * 向环中添加孩子
     * @param nums 孩子个数
     */
    public void add(int nums){
        if (nums < 1){
            System.out.println("添加节点数不小于1");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++){
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(boy);
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 循环遍历环形链表
     * 结束循环条件为 curBoy.getNext() = first;
     */
    public void showBoy(){
        if (first == null){
            System.out.println("环形链表为空！");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy.toString());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 出圈
     * @param startNo 从第几个小孩开始
     * @param countNum 数多少下
     * @param nums 初始圈内有多少小孩
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (startNo < 1 || countNum < 0 || startNo > nums){
            System.out.println("参数有误");
            return;
        }
        //建立辅助指针 helper
        Boy helper = first;
        // 将helper指向环中最后一个节点
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        // 循环报数出圈
        while (true){
            if (helper == first){
                break;
            }
            // 报数时 helper指针及first指针随着报数向前移动 countNum - 1 下
            for (int j = 0; j < countNum - 1; j++){
                helper = helper.getNext();
                first = first.getNext();
            }
            System.out.printf("出圈的孩子 %d \n", first.getNo());
            // 每轮报数完出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后出圈的孩子为：" + first.getNo());
    }

}

@Data
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
