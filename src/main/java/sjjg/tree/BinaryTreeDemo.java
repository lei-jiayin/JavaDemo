package sjjg.tree;

/**
 * @author adv
 * @date 2020/9/7 17:22
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode heroNode2 = new HeroNode(2,"吴用");
        HeroNode heroNode3 = new HeroNode(3,"卢俊义");
        HeroNode heroNode4 = new HeroNode(4,"林冲");
        HeroNode heroNode5 = new HeroNode(5,"关胜");

        // 先手动 后递归创建
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);

        //赋值
        binaryTree.setRoot(root);

        // 测试
        System.out.println("前序");
        binaryTree.preOrder();//12354
        System.out.println("中序");
        binaryTree.infixOrder();//21534
        System.out.println("后序");
        binaryTree.postOrder();//25431

    }
}

// 定义一个binaryTree 二叉树
class BinaryTree{
    private HeroNode root;
    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历调用
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("当前二叉树为空！");
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("当前二叉树为空！");
        }
    }

    // 后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("当前二叉树为空！");
        }
    }
}

/**
 * 创建英雄节点
 */
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    // 编写前序遍历的方法
    public void preOrder(){
        // 先输出父节点
        System.out.println(this);
        // 递归前序左子树
        if (this.left != null){
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if (this.left != null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null){
            this.right.postOrder();
        }
        // 打印父节点
        System.out.println(this);
    }

}