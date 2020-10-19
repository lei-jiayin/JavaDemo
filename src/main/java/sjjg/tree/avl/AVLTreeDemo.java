package sjjg.tree.avl;

/**
 * 平衡二叉树实现
 *
 * @author adv
 * @date 2020/9/29 14:59
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        // int[] arr = {10,12,8,9,7,6};
        // int[] arr = {4,3,6,5,7,8};

        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i:arr) {
            avlTree.add(new Node(i));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在做平衡处理后");
        System.out.println("树的高度="+avlTree.getRoot().heigth());
        System.out.println("树的左子树高度="+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度="+avlTree.getRoot().rightHeight());
        System.out.println("当前的根节点的值为：" + avlTree.getRoot().value);
        avlTree.infixOrder();
    }
}

/**
 * 创建平衡二叉树
 */
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 删除节点
     *
     * @param value
     */
    public void deleteNode(int value) {
        if (root == null) {
            System.out.println("树为空！");
            return;
        }
        //找到该节点
        Node target = search(value);
        if (target == null) {
            System.out.println("未找到目标节点");
            return;
        }
        //判断是否为根节点
        if (searchParent(value) == null && target.left == null && target.right == null) {
            root = null;
            return;
        }
        if (target.left == null && target.right == null) {
            //删除的是叶子节点
            Node parent = searchParent(value);
            if (value < parent.value) {
                //父节点的左子节点
                parent.left = null;
            } else {
                //父节点的右子节点
                parent.right = null;
            }
        } else if (target.left != null && target.right != null) {
            // Node parent = root.searchParent(value);
            // if (parent.left.value == value){
            //从目标节点的右子树找到最小值
            Node tmpe = target.right;
            while (tmpe.left != null) {
                tmpe = tmpe.left;
            }
            deleteNode(tmpe.value);
            target.value = tmpe.value;

            // 从目标节点的 从左子树找到最大值
            /*Node temp = target.left;
            while (temp.right != null){
                temp = temp.right;
            }
            deleteNode(temp.value);
            target.value = temp.value;*/
        } else {
            //删除的是有一个子树的节点
            Node parent = searchParent(value);
            if (parent == null) {
                if (target.left != null) {
                    root = target.left;
                } else {
                    root = target.right;
                }
            } else {
                if (parent.left != null && parent.left.value == value) {
                    //父节点的左子节点是目标
                    if (target.left != null) {
                        parent.left = target.left;
                    } else {
                        parent.left = target.right;
                    }
                } else {
                    //父节点的右子节点是目标
                    if (target.left != null) {
                        parent.right = target.left;
                    } else {
                        parent.right = target.right;
                    }
                }
            }
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("该树为空！");
            return null;
        }
        return root.searchParent(value);
    }

    public Node search(int value) {
        if (root == null) {
            System.out.println("该树为空！");
            return null;
        }
        return root.search(value);
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空！");
        }
    }
}

/**
 * 树的节点
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left != null) {
            return left.heigth();
        }
        return 0;
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right != null) {
            return right.heigth();
        }
        return 0;
    }

    // 返回该节点为根节点树的高度
    public int heigth() {
        return Math.max(left == null ? 0 : left.heigth(), right == null ? 0 : right.heigth()) + 1;
    }

    /**
     * 左旋转
     * @return
     */
    private void leftRotate(){
        //创建新的结点，以当前结点的值
        Node newNode = new Node(this.value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = this.left;
        //把新的结点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值设置成当前节点的右节点的值
        this.value = this.right.value;
        //当前节点的右子树设置成当前节点的右子树的右子树
        this.right = this.right.right;
        //当前节点的左子树指向新的节点
        this.left = newNode;

    }

    /**
     * 右旋转
     */
    private void rightRotate(){
        //创建新的节点值为当前节点的值
        Node newNode = new Node(value);
        //新节点的右子节点设置为当前节点的右子节点
        newNode.right = right;
        //新节点的左子节点设置为当前节点的左子节点的右子节点
        newNode.left = left.right;
        //当前节点的值设置为当前节点的左子节点的值
        value = left.value;
        //设置当前节点的左子节点为当前节点的左子节点的左子节点
        left = left.left;
        //当前节点的右子节点指向新节点
        right = newNode;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 查询目标节点的父节点
     *
     * @param value 目标节点的值
     * @return 父节点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else if (value < this.value) {
            //若目标值小于当前节点，在左
            if (this.left != null) {
                return this.left.searchParent(value);
            }
            return null;
        } else {
            if (this.right != null) {
                return this.right.searchParent(value);
            }
            return null;
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 返回该节点或返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            //找到该节点
            return this;
        }
        if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }


    /**
     * 添加节点的方法
     * 递归的形式添加节点，需满足二叉排序树的要求
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加完一个节点后，如果右子树的高度-左子树的高度 > 1，左旋转
        if (rightHeight() - leftHeight() > 1){
            //如果当前节点的右子树的左子树高度大于它的右子树的右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                //对当前节点的右子树进行右旋
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        //当添加完一个节点后，如果左子树的高度-右子树的高度 > 1，右旋转
        if (leftHeight() - rightHeight() > 1){
            //判断特殊情况：
            //当前节点的左子树的右子树的高度大于它的左子树的左子树高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前节点的左子树进行左旋
                left.leftRotate();
                rightRotate();
            }else {
                //直接进行右旋
                rightRotate();
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        /*if (this == null){
            return;
        }*/
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}