package sjjg;

import java.util.List;

/**
 * 树节点
 *
 * @author adx
 * @date 2020/8/5 15:41
 */
public class TreeNode<T> {
    public T data;

    public TreeNode<T> leftChild;
    public TreeNode<T> rightChild;

    public TreeNode(T data){
        this.data = data;
    }
    public TreeNode(){}

    public void addLeftChild(T value){
        TreeNode<T> treeNode = new TreeNode<T>(value);
        this.leftChild = treeNode;
    }

    public void addRightChild(T value){
        TreeNode<T> rT = new TreeNode<T>(value);
        this.rightChild = rT;
    }
}
