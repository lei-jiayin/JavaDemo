package sjjg.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树（特殊的二叉树，WPL最小的二叉树）的实现
 * 节点的权=赋给节点某个具有含义的值
 * 节点的带权路径长度=根节点到该节点之间的路径长度与该节点权的乘积
 * WPL 树的带权路径长度=所有叶子节点的带权路径长度之和
 * @author adv
 * @date 2020/9/18 13:49
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        // System.out.println("前序遍历：");
        // preOrder(root);
        System.out.println("赫夫曼树的叶子节点：");
        leafOrder(root);
    }

    /**
     * 赫夫曼树的创建
     * @param arr 原数组
     * @return 返回赫夫曼树的根结点
     */
    public static Node createHuffmanTree(int[] arr){
        //准备工作
        //1，遍历arr数组
        //2，将arr数组的每个元素构造成一个Node
        //3，将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value:arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //排序
            Collections.sort(nodes);

            System.out.println(nodes);
            //1 3 6 7 8 13 29
            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新构建的二叉树parent放入arrayList
            nodes.add(parent);
        }

        //最终arrayList的数组中只存在赫夫曼树的头结点
        return nodes.get(0);
    }

    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树！");
        }
    }

    private static void leafOrder(Node node){
        if (node==null){
            return;
        }
        if (node.left == null && node.right == null){
            System.out.println(node);
        }
        leafOrder(node.left);
        leafOrder(node.right);
    }
}

//节点 支持排序
//支持集合collections排序
//实现Comparable接口
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // return o.value - this.value;  //表示从大到小排序 若这里是逆序 上面代码需要改变 最先取得两个值得倒序取，取最小两个
        return this.value - o.value;        //表示从小到大排序
    }
}
