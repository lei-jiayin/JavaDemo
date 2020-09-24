package sjjg.tree.binarysorttree;

/**
 * 二叉排序树
 * @author adv
 * @date 2020/9/24 10:09
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i1 : arr) {
            binarySortTree.add(new Node(i1));
        }
        binarySortTree.infixOrder();
        binarySortTree.add(new Node(2));
        binarySortTree.infixOrder();

    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree{
    private Node root;

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node){
        if (root==null){
            root = node;
        }else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root!= null){
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空！");
        }
    }
}


/**
 * 树的节点
 */
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找要删除的节点
     * @param value 希望删除的节点的值
     * @return 返回该节点或返回null
     */
    public Node search(int value){
        if (value == this.value){
            //找到该节点
            return this;
        }
        return null;
    }


    /**
     * 添加节点的方法
     * 递归的形式添加节点，需满足二叉排序树的要求
     * @param node
     */
    public void add(Node node){
        if (node==null){
            return;
        }
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        /*if (this == null){
            return;
        }*/
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
}
