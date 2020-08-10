package sjjg.linkedlist;

import lombok.Data;

import java.util.Stack;


/**
 * 单链表的实现
 * @author xw
 * @date 2020/8/8 12:58
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("单链表实现");
        LinkedNode linkedNode1 = new LinkedNode(1,"xw","weiwie");
        LinkedNode linkedNode2 = new LinkedNode(1,"xwx","wei");
        LinkedNode linkedNode3 = new LinkedNode(3,"xwxw","xxx");
        SingleLinkedList linkedList = new SingleLinkedList();
        System.out.println("该链表是否为空："+ linkedList.isEmpty());
        linkedList.addNode(linkedNode1);
        linkedList.addNode(linkedNode2);
        linkedList.addNode(linkedNode3);
        linkedList.list();
        /*System.out.println("该链表是否为空："+ linkedList.isEmpty());
        linkedList.addNodeByNo(new LinkedNode(2,"xss","111"));
        linkedList.list();
        linkedList.update(new LinkedNode(2,"sssssssss","222222"));
        linkedList.list();
        linkedList.update(new LinkedNode(22,"sssssssss","222222"));
        System.out.println("删除节点");
        linkedList.delete(new LinkedNode(1,"sssssssss","222222"));
        linkedList.delete(new LinkedNode(2,"sssssssss","222222"));
        linkedList.list();
        System.out.println(linkedList.count());
        System.out.println("统计节点个数：");
        System.out.println(getLength(linkedList.getHead()));
        System.out.println("倒数第1节点：" + findByK(linkedList.getHead(),1));*/
        /*System.out.println("反转节点");
        reverseLinkedList(linkedList.getHead());
        linkedList.list();
        System.out.println("逆序打印");
        reversePrint(linkedList.getHead());*/

        LinkedNode linkedNode11 = new LinkedNode(4,"xw","weiwie");
        LinkedNode linkedNode22 = new LinkedNode(5,"xwx","wei");
        LinkedNode linkedNode33 = new LinkedNode(5,"xwxw","xxx");
        SingleLinkedList linkedList1 = new SingleLinkedList();
        System.out.println("该链表是否为空："+ linkedList.isEmpty());
        linkedList1.addNode(linkedNode11);
        linkedList1.addNode(linkedNode22);
        linkedList1.addNode(linkedNode33);
        linkedList1.list();

        System.out.println("合并有序链表");
        LinkedNode newhead = mergeList(linkedList.getHead(), linkedList1.getHead());
        reversePrint(newhead);

    }

    /**
     * 合并两个 有序链表 合并之后依旧是有序链表
     * @param a
     * @param b
     * @return
     */
    public static LinkedNode mergeList(LinkedNode a, LinkedNode b){
        if (a.next == null && b.next == null){
            return null;
        }else if (a.next == null){
            return b;
        }else if (b.next == null){
            return a;
        }
        LinkedNode ca = a.next;
        LinkedNode cb = b.next;
        LinkedNode tmpe = null;
        LinkedNode c = new LinkedNode();
        LinkedNode ctmpe = c;

        // 终止条件
        while (ca != null && cb != null){
            if (ca.no > cb.no){
              /*  tmpe = cb;
                tmpe.next = null;*/
                tmpe = cb.next;
                ctmpe.next = cb;
                cb = tmpe;
            }else
            if (ca.no < cb.no){
                tmpe = ca.next;
                ctmpe.next = ca;
                ca = tmpe;
            }else {
                tmpe = ca.next;
                ctmpe.next = ca;
                ca = tmpe;
                ctmpe = ctmpe.next;
                tmpe = cb.next;
                ctmpe.next = cb;
                cb = tmpe;
            }
            ctmpe = ctmpe.next;
        }
        if (ca != null) ctmpe.next = ca;
        if (cb != null) ctmpe.next = cb;
        return c;
    }

    /**
     * 逆序打印单链表
     * 使用栈来实现
     * @param head 头节点
     */
    public static void reversePrint(LinkedNode head){
        if (head.next == null){
            return;
        }
        LinkedNode tmpe = head.next;
        Stack<LinkedNode> stack = new Stack<LinkedNode>();
        while (tmpe != null){
            stack.push(tmpe);
            tmpe = tmpe.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 腾讯面试，翻转单链表
     * @param head 目标头结点
     */
    public static void reverseLinkedList(LinkedNode head){
        // 当有效节点只有1个 或 链表为空
        if (head.next == null || head.next.next == null){
            return;
        }
        // 建立辅助节点
        LinkedNode tmpe = head.next;
        // 指向当前节点[tmpe]的下个节点
        LinkedNode next = null;
        // 翻转的辅助头节点
        LinkedNode reverse = new LinkedNode();
        // 循环遍历链表 将节点指向新节点的后面
        while (tmpe != null){
            next = tmpe.next;// 保存当前节点的下个节点
            tmpe.next = reverse.next;// 将tmpe节点指向新链表的头结点之后的最前端
            reverse.next = tmpe;// 将新链表的头结点与反转节点相连
            tmpe = next;// 指针后移
        }
        // 将要反转的头结点指向新的头结点所指向的节点 完成反转
        head.next = reverse.next;
    }

    /**
     * 新浪面试
     * 查找链表倒数第K个节点
     * 查找的就是总节点数（除头结点）- K 位置的节点
     * @param head 头结点
     * @param k 倒数第k个位置
     * @return
     */
    public static LinkedNode findByK(LinkedNode head,int k){
        if (head.next == null){
            return null;
        }
        int size = getLength(head);
        if (k <= 0 || k > size){
            return null;
        }

        LinkedNode tmpe = head.next;
        for (int i = 0; i < size - k; i++){
            tmpe = tmpe.next;
        }

        // 自定义的
       /*
        int index = size - k + 1;
        while (tmpe != null){
            index--;
            if (index == 0){
                break;
            }
            tmpe = tmpe.next;
        }*/
        return tmpe;
    }

    /**
     * 获取到单链表的节点的个数（如果有头结点则不统计头结点）
     * @param head 链表的头结点
     * @return 返回有效节点的个数
     */
    public static int getLength(LinkedNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        LinkedNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }
}

/**
 * 链表的基础操作
 */
class SingleLinkedList{

    /**
     * 初始化头节点 不存放具体数据
     */
    private LinkedNode head = new LinkedNode();

    public LinkedNode getHead() {
        return head;
    }

    /*public SingleLinkedList(){
        this.head = new LinkedNode();
    }*/

    /**
     * 判空
     * @return 空为真
     */
    public boolean isEmpty(){
        if (head.next == null){
            return true;
        }
        return false;
    }

    /**
     * 为链表添加节点（直接在链表之后添加节点）
     * @param linkedNode 要添加的节点
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
     * @param linkedNode 有排名的节点
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
     * @param linkedNode 新的节点
     * @return 成功为真
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
     * @param delNode 要删除的节点 根据NO删除
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

    /**
     * 统计链表节点个数
     * @return
     */
    public int count(){
        LinkedNode tmpe = head;
        int count = 0;
        while (true){
            if (tmpe.next == null){
                break;
            }
            count++;
            tmpe = tmpe.next;
        }
        return count;
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
