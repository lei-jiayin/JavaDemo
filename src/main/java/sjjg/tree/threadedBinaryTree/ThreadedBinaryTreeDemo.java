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
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);




        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        //前序遍历
//        threadedBinaryTree.preOrder();

        //后序遍历
        System.out.println("后序遍历");
        threadedBinaryTree.postOrder();

        //中序线索化
//        threadedBinaryTree.indifixThreadedNodes();
        //前序线索化
//        threadedBinaryTree.preThreadedNodes();
        //后序线索化
        threadedBinaryTree.postThreadedNodes();

        //测试 以node5为例
        System.out.println("测试线索化是否成功：");
        HeroNode node5Left = node3.getLeft();
        HeroNode node5Right = node3.getRight();
        System.out.println(node5Left);
        System.out.println(node5Right);

        //遍历中序线索化二叉树
       /* System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();*/

        //遍历前序线索化二叉树
        /*System.out.println("使用前序线索化的方式遍历线索化二叉树");
        threadedBinaryTree.preThreadedList();*/

        //遍历后序线索化二叉树
        System.out.println("使用后序线索化的方式遍历线索化二叉树");
        threadedBinaryTree.postThreadedList();
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

    public void indifixThreadedNodes(){
        this.indifixThreadedNodes(root);
    }

    public void preThreadedNodes(){
        this.preThreadedNodes(root);
    }

    public void postThreadedNodes(){
        this.postThreadedNodes(root);
    }

    //后序线索化遍历
    public void postThreadedList(){
        HeroNode node = root;
        HeroNode tmpe = null;
        while (node!=null){
            if (node == tmpe){
                System.out.println(node);
                break;
            }
            while (node.getLeftType() == 0){
                tmpe = node;
                node = node.getLeft();
            }
            System.out.println(node);
            /*while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }*/

            node = node.getRight();
//            System.out.println(node);
        }
        node = root;
        while (node != null){

        }
        System.out.println(root);
    }

    //编写后序线索化及 后序线索化遍历
    public void postThreadedNodes(HeroNode node){
        //节点不能为空 不能线索化
        if (node == null){
            return;
        }
        // 先线索化左子树
        postThreadedNodes(node.getLeft());
        // 再线索化右子树
        postThreadedNodes(node.getRight());

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
//        System.out.println(node);
    }

    //遍历前序线索化二叉树
    public void preThreadedList(){
        HeroNode node = root;
        while (node != null){
            //打印当前这个节点
            System.out.println(node);
            //循环遍历leftType==0的节点并打印
            while (node.getLeftType() == 0){
                node = node.getLeft();
                System.out.println(node);
            }
            //当前节点的右指针指向后继节点
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            //在从节点的右节点开始（或者是后继节点）
            node = node.getRight();
        }
    }

    //前序线索化二叉树
    public void preThreadedNodes(HeroNode node){
        //当节点为空时返回
        if (node == null){
            return;
        }
        //线索化当前节点
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
        if (node.getLeftType() != 1){
            preThreadedNodes(node.getLeft());
        }else {
            return;
        }
        if (node.getRightType() != 1){
            preThreadedNodes(node.getRight());
        }else {
            return;
        }
    }


    //中序线索化后的遍历顺序是线索化之前中序遍历的顺序
    /**
     * 遍历中序线索化二叉树
     */
    public void indifixThreadedList(){
        // 定义一个变量 存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环找到lefttype == 1的节点，因为是中序线索化，第一个节点是8
            //后面随着遍历而变化位置，因为当leftType==1，说明该节点已被中序线索化的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //当前节点的右指针指向的是后继节点，
            while (node.getRightType() == 1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();

        }
    }


    /**
     * 编写对二叉树进行中序线索化的方法
     * @param node 当前要线索化的节点
     */
    public void indifixThreadedNodes(HeroNode node){
        //节点不能为空 不能线索化
        if (node == null){
            return;
        }
        // 先线索化左子树
        indifixThreadedNodes(node.getLeft());
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
        indifixThreadedNodes(node.getRight());
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
                ", leftType=" + leftType +
                ", rightType=" + rightType +
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
