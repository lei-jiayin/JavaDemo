package sjjg.tree;

/**
 * 顺序存储二叉树
 *
 * @author adv
 * @date 2020/9/8 15:19
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
        System.out.println("中序遍历");
        arrBinaryTree.infixOrder();
        System.out.println("后序遍历");
        arrBinaryTree.postOrder();
    }
}

/**
 * 顺序存储二叉树
 * 满足：(n从0开始，n其实为数组的下标)
 * 1.完全二叉树
 * 2.第n个元素的左子节点为 2*n+1
 * 3.第n个元素的右子节点为 2*n+2
 * 4.第n个元素的父节点为 (n-1)/2 向下取整
 */
class ArrBinaryTree {
    public int arr[];

    // 传入数组
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载
    public void preOrder() {
        preOrder(0);
    }

    public void infixOrder() {
        infixOrder(0);
    }

    public void postOrder() {
        postOrder(0);
    }

    //前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，前序遍历失败！");
            return;
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //中序序遍历
    public void infixOrder(int index) {
        //判断条件
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，中序遍历失败！");
            return;
        }
        // 向左递归遍历
        if (index * 2 + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if (index * 2 + 2 < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，后序遍历失败！");
            return;
        }
        // 向左递归遍历
        if (index * 2 + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归遍历
        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
    }

}
