package sjjg.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树的实现
 * @author adv
 * @date 2020/9/18 13:49
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        System.out.println("前序遍历：");
        preOrder(root);
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
        // return o.value - this.value;  //表示从大到小排序
        return this.value - o.value;        //表示从小到大排序
    }
}
