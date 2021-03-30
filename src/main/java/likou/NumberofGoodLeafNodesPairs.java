package likou;

/**
 * 1530. 好叶子节点对的数量 字节
 * @author adv
 * @date 2021/3/30 16:37
 */
public class NumberofGoodLeafNodesPairs {
    public int countPairs(TreeNode root, int distance) {
        Pair pair = dfs(root, distance);
        return pair.count;
    }

    // 对于 dfs(root,distance)，同时返回：
    // 1）每个叶子节点与 root 之间的距离
    // 2) 以 root 为根节点的子树中好叶子节点对的数量
    public Pair dfs(TreeNode root, int distance) {
        int[] depths = new int[distance + 1];
        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf) {
            depths[0] = 1;
            return new Pair(depths, 0);
        }

        int[] leftDepths = new int[distance + 1];
        int[] rightDepths = new int[distance + 1];
        int leftCount = 0, rightCount = 0;
        //遍历左子树
        if (root.left != null) {
            Pair leftPair = dfs(root.left, distance);
            leftDepths = leftPair.depths;
            leftCount = leftPair.count;
        }
        //遍历右子树
        if (root.right != null) {
            Pair rightPair = dfs(root.right, distance);
            rightDepths = rightPair.depths;
            rightCount = rightPair.count;
        }
        for (int i = 0; i < distance; i++) {
            depths[i + 1] += leftDepths[i];
            depths[i + 1] += rightDepths[i];
        }

        // 好叶子节点对的数量
        int cnt = 0;
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; j + i + 2 <= distance; j++) {
                //左子树和右子树符合条件的叶子节点的排列组合
                cnt += leftDepths[i] * rightDepths[j];
            }
        }
        return new Pair(depths, cnt + leftCount + rightCount);
    }
}

class Pair {
    int[] depths;
    int count;

    public Pair(int[] depths, int count) {
        this.depths = depths;
        this.count = count;
    }
}

        /*作者：LeetCode-Solution
        链接：https://leetcode-cn.com/problems/number-of-good-leaf-nodes-pairs/solution/hao-xie-zi-jie-dian-dui-de-shu-liang-by-leetcode-s/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
