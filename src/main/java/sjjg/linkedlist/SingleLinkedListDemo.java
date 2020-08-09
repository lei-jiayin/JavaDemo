package sjjg.linkedlist;

import lombok.Data;

/**
 * 单链表的实现
 * @author xw
 * @date 2020/8/8 12:58
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("单链表实现");
        LinkedNode linkedNode1 = new LinkedNode(1,"xw","weiwie");
        LinkedNode linkedNode2 = new LinkedNode(4,"xwx","wei");
        LinkedNode linkedNode3 = new LinkedNode(7,"xwxw","xxx");
        LinkedList linkedList = new LinkedList();
        System.out.println("该链表是否为空："+ linkedList.isEmpty());
        linkedList.addNode(linkedNode1);
        linkedList.addNode(linkedNode2);
        linkedList.addNode(linkedNode3);
        linkedList.list();
        System.out.println("该链表是否为空："+ linkedList.isEmpty());
        linkedList.addNodeByNo(new LinkedNode(2,"xss","111"));
        linkedList.list();
        linkedList.update(new LinkedNode(2,"sssssssss","222222"));
        linkedList.list();
        linkedList.update(new LinkedNode(22,"sssssssss","222222"));
        System.out.println("删除节点");
        linkedList.delete(new LinkedNode(1,"sssssssss","222222"));
        linkedList.delete(new LinkedNode(2,"sssssssss","222222"));
        linkedList.delete(new LinkedNode(4,"sssssssss","222222"));
        linkedList.delete(new LinkedNode(1111,"sssssssss","222222"));
        linkedList.delete(new LinkedNode(7,"sssssssss","222222"));
        linkedList.delete(new LinkedNode(111,"sssssssss","222222"));
        linkedList.list();
    }
}

/**
 * 链表的基础操作
 */
class LinkedList{
    private LinkedNode head;

    public LinkedList(){
        this.head = new LinkedNode();
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty(){
        if (head.next == null){
            return true;
        }
        return false;
    }

    /**
     * 为链表添加节点（直接在链表之后添加节点）
     * @param linkedNode
     */
    public void addNode(LinkedNode linkedNode){
        LinkedNode tmpe = head;
        while (tmpe.next != null){
            tmpe = tmpe.next;
        }
        tmpe.next = linkedNode;
    }

    /**
     * 通过排名添加节点 若有排名 则添加失败
     * @param linkedNode
     */
    public void addNodeByNo(LinkedNode linkedNode){
        // 因为头结点不能动，因此我们仍然使用辅助指针来帮助我们找到添加的位置
        //我们找的temp是位于 添加位置的前一个节点，否则插入不了
        LinkedNode tmpe = head;
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
            tmpe.next = linkedNode;
        }
    }

    /**
     * 展示节点信息
     */
    public void list(){
        if (isEmpty()){
            System.out.println("链表为空!");
            return;
        }
        LinkedNode tmpe = head;
        while (tmpe.next != null){
            System.out.println(tmpe.next.toString());
            tmpe = tmpe.next;
        }
    }

    /**
     * 更新 节点数据
     * @param linkedNode
     * @return
     */
    public boolean update(LinkedNode linkedNode){
        // 判空
        if(head.next == null){
            System.out.println("链表为空~");
            return false;
        }
        boolean flag = false;
        LinkedNode tmpe = head;
        while (true){
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
     * @param delNode
     */
    public void delete(LinkedNode delNode){
        if (isEmpty()){
            System.out.println("链表为空~~~");
            return;
        }
        LinkedNode tmpe = head;
        boolean flag = false;
        while (true){
            if (tmpe.next == null){
                break;
            }
            if (tmpe.next.no == delNode.no){
                flag = true;
                break;
            }
            tmpe = tmpe.next;
        }
        if (flag){
            tmpe.next = tmpe.next.next;
        }else {
            System.out.println("节点不存在~~");
        }
    }
}

/**
 * 定义节点
 */
@Data
class LinkedNode{
    int no;
    String name;
    String nickName;
    LinkedNode next;

    public LinkedNode() {
    }

    public LinkedNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
