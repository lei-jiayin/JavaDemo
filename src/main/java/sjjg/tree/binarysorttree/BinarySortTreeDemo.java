package sjjg.tree.binarysorttree;

/**
 * 二叉排序树
 *
 * @author adv
 * @date 2020/9/24 10:09
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        // int[] arr = {7, 3};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i1 : arr) {
            binarySortTree.add(new Node(i1));
        }
        // binarySortTree.infixOrder();
        // binarySortTree.add(new Node(2));
        // binarySortTree.add(new Node(11));
        binarySortTree.infixOrder();

        /*System.out.println("查找节点：");
        Node node = binarySortTree.search(2);
        System.out.println(node);*/

        /*System.out.println("查找父节点：");
        Node parent = binarySortTree.searchParent(6);
        System.out.println(parent);*/
        System.out.println("删除节点：");
        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(1);
        binarySortTree.deleteNode(10);
        binarySortTree.infixOrder();
    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree {
    private Node root;

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
            if (parent == null){
               if (target.left != null){
                   root = target.left;
               }else {
                   root = target.right;
               }
            }else {
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
