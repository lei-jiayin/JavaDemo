package sjjg.linkedlist;

import lombok.Data;

/**
 * 双向链表实现
 *
 * @author adx
 * @date 2020/8/11 9:52
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("测试双向链表");
        DoubleLinkedNode doubleLinkedNode1 = new DoubleLinkedNode(1,"文曲","相似才");
        DoubleLinkedNode doubleLinkedNode2 = new DoubleLinkedNode(4,"qqq","相似才s");
        DoubleLinkedNode doubleLinkedNode3 = new DoubleLinkedNode(8,"aaa","相似才sw");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(doubleLinkedNode1);
        doubleLinkedList.addNode(doubleLinkedNode2);
        doubleLinkedList.addNode(doubleLinkedNode3);
        doubleLinkedList.list();

        System.out.println("测试更新");
        doubleLinkedList.update(new DoubleLinkedNode(1,"ccc","xscas"));
        doubleLinkedList.list();

        /*System.out.println("测试删除");
        doubleLinkedList.delete(new DoubleLinkedNode(1,"",""));
        doubleLinkedList.list();*/

        System.out.println("测试顺序添加");
        //doubleLinkedList.addNode(doubleLinkedNode1);
        DoubleLinkedNode doubleLinkedNode4 = new DoubleLinkedNode(9,"顺序","测试");
        doubleLinkedList.addNodeByNo(doubleLinkedNode4);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    /**
     * 初始化头节点 不存放具体数据
     */
    private DoubleLinkedNode head = new DoubleLinkedNode();
    public DoubleLinkedNode getHead() {
        return head;
    }

    // 遍历
    public void list(){
        if (head.next == null){
            System.out.println("链表为空!");
            return;
        }
        DoubleLinkedNode tmpe = head;
        while (tmpe.next != null){
            System.out.println(tmpe.next.toString());
            tmpe = tmpe.next;
        }
    }

    /**
     * 为双向链表添加节点（直接在链表之后添加节点）
     * @param linkedNode 要添加的节点
     */
    public void addNode(DoubleLinkedNode linkedNode){
        DoubleLinkedNode tmpe = head;
        while (tmpe.next != null){
            tmpe = tmpe.next;
        }
        tmpe.next = linkedNode;
        linkedNode.pre = tmpe;
    }

    /**
     * 通过排名添加节点 若有排名 则添加失败
     * @param linkedNode 有排名的节点
     */
    public void addNodeByNo(DoubleLinkedNode linkedNode){
        if (head.next == null){
            return;
        }

        // 因为头结点不能动，因此我们仍然使用辅助指针来帮助我们找到添加的位置
        //我们找的temp是位于 添加位置的前一个节点，否则插入不了
        DoubleLinkedNode tmpe = head;
        boolean flag = false;// 标志添加的编号是否存在，默认为false
        while (true){
            if (tmpe.next == null){
                break;
            }
            if (tmpe.next.no > linkedNode.no){ // 位置找到在tmpe后面添加
                break;
            }else if (tmpe.next.no == linkedNode.no){// 编号已存在
                flag = true;
                break;
            }
            tmpe = tmpe.next;
        }
        // 判断flag是否已存在
        if (flag){
            System.out.println("准备插入的编号已存在！" + linkedNode.no);
        }else {
            // 插入tmpe的后面
           linkedNode.next = tmpe.next;
           linkedNode.pre = tmpe;
           if(tmpe.next != null){
               tmpe.next.pre = linkedNode;
           }
           tmpe.next = linkedNode;
        }
    }

    /**
     * 更新 节点数据 与 单向链表差不多
     * @param linkedNode 新的节点
     * @return 成功为真
     */
    public boolean update(DoubleLinkedNode linkedNode){
        // 判空
        if(head.next == null){
            System.out.println("链表为空~");
            return false;
        }
        boolean flag = false;
        DoubleLinkedNode tmpe = head;
        while (true){
            // 循环到链表最后了
            if (tmpe == null){
                break;
            }
            if (tmpe.no == linkedNode.no){
                flag = true;
                break;
            }
            tmpe = tmpe.next;
        }
        if (flag){
            // 双向链表删除
            tmpe.name = linkedNode.name;
            tmpe.nickName = linkedNode.nickName;
            return true;
        }else{
            System.out.println("没有找到该节点~");
            return false;
        }
    }

    /**
     * 删除链表中的节点
     * @param delNode 要删除的节点 根据NO删除
     */
    public void delete(DoubleLinkedNode delNode){
        if (head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        DoubleLinkedNode tmpe = head.next;
        boolean flag = false;
        while (true){
            if (tmpe == null){
                break;
            }
            if (tmpe.no == delNode.no){
                flag = true;
                break;
            }
            tmpe = tmpe.next;
        }
        if (flag){
            tmpe.pre.next = tmpe.next;
            // 避免节点为最后一个 造成空指针异常
            if (tmpe.next != null){
                tmpe.next.pre = tmpe.pre;
            }
        }else {
            System.out.println("节点不存在~~");
        }
    }
}

/**
 *
 */
@Data
class DoubleLinkedNode{
    int no;
    String name;
    String nickName;
    DoubleLinkedNode next; // 指向下一个节点
    DoubleLinkedNode pre; // 指向上一个节点

    public DoubleLinkedNode() {
    }

    public DoubleLinkedNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleLinkedNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
