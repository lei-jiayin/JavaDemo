package sjjg.huffmanTree.code;

import java.util.*;

/**
 * 赫夫曼编码
 * @author adv
 * @date 2020/9/21 13:54
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);//40

        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        Node root = createHuffmanTree(nodes);
        System.out.println();
        // root.preOrder();
        // leafOrder(root);

        Map<Byte, String> codes = getCodes(root);
        System.out.println("生成的赫夫曼编码表" + codes);
    }

    //编写一个方法，将字符串对应的byte[]数组，通过生成赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     *
     * @param bytes 原始字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     */
    /*private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        Node huffmanTree = createHuffmanTree(getNodes(bytes));
        getCodes(huffmanTree);

    }*/

    /**
     * 将bytes数组构建为nodes
     * @param bytes 字节数组
     * @return 返回的就是List 形式Node(97,1)
     */
    private static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes,统计每一个byte出现的次数->map
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count + 1);
            }
        }

        //把每个键值对转为Node对象，并加入nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //生成赫夫曼树对应的赫夫曼编码
    //使用map保存
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder,存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static Map<Byte,String> getCodes(Node root){
        if (root == null){
            return null;
        }
        //处理root
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node的节点的所有叶子节点的赫夫曼编码存放到集合中
     * {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     * @param node 传入赫夫曼根节点
     * @param code 路径：左子节点是0 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder ){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null){
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null){
                //非叶子节点
                //左递归处理
                getCodes(node.left,"0",stringBuilder2);
                //右递归处理
                getCodes(node.right,"1",stringBuilder2);
            }else {
                //表示找到某个叶子节点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
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

/**
 * 创建带数据和权值的node节点
 */
class Node implements Comparable<Node>{
    //存放数据本身，'a'=>97
    Byte data;
    //权值表示字符出现的次数
    int weight;

    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

   /* public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                ", left=" + left +
                ", right=" + right +
                '}';
    }*/

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder(){
        Node node = this;
        System.out.println(node);
        if (node.left!=null){
            this.left.preOrder();
        }
        if (node.right != null){
            this.right.preOrder();
        }
    }
}
