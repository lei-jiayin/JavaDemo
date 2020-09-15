package sjjg.tree.threadedBinaryTree;

/**
 * 线索化功能二叉树
 * @author adv
 * @date 2020/9/8 16:34
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试线索化二叉树
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        //二叉树后面我们要递归创建

    }
}

// 定义一个线索化功能的 二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //定义一个前驱指针 总是保留前一个指针
    private HeroNode pre = null;


    public void setRoot(HeroNode root){
        this.root = root;
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     * @param node 当前要线索化的节点
     */
    public void threadedNodes(HeroNode node){
        //节点不能为空 不能线索化
        if (node == null){
            return;
        }
        // 先线索化左子树
        threadedNodes(node.getLeft());
        // 线索化当前节点
        //处理当前节点的前驱节点
        // 叶子结点指向空
        if (node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型 指向前驱
            node.setLeftType(1);
        }
        // 处理后继节点
        if (pre!=null && pre.getRight()==null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点成为下一个 节点的前驱节点
        pre = node;
        // 再线索化右子树
        threadedNodes(node.getRight());
    }

    //删除节点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树不能删除！");
        }
    }

    //前序查找
    public HeroNode preSearch(int no){
        if (root != null){
            return root.preSearch(no);
        }
        return null;
    }

    //前序查找
    public HeroNode infixSearch(int no){
        if (this.root != null){
            return this.root.infixSearch(no);
        }
        return null;
    }

    //前序查找
    public HeroNode postSearch(int no){
        if (this.root != null){
            return this.root.postSearch(no);
        }
        return null;
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
//节点
/**
 * 创建英雄节点
 */
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //1.leftType==0 表示指向的是左子树，==1 表示指向前驱节点
    //2.rightType==0 表示指向的是右子树，==1 表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
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

    //前序查找
    public HeroNode preSearch(int no){
        System.out.println("进入前序查找~~");
        HeroNode resNode = null;
        if(this.no == no){
            return this;
        }
        if (this.left != null){
            resNode = this.left.preSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.preSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        return resNode;
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

    //中序查找
    public HeroNode infixSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        System.out.println("进入中序序查找~~");
        if(this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        return resNode;
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
    //后序查找
    public HeroNode postSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        System.out.println("进入后序查找~~");
        if(this.no == no){
            return this;
        }
        return resNode;
    }

    // 删除节点
    // 规定 若是叶子节点 直接删除 若是非叶子节点则删除子树
    public void delNode(int no){
        if (this.left != null) {
            if (this.left.no == no){
                this.left = null;
                return;
            }else {
                this.left.delNode(no);
            }
        }
        if (this.right != null){
            if (this.right.no == no){
                this.right = null;
            }else {
                this.right.delNode(no);
            }
        }
    }

}
