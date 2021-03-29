package likou;

/**
 * 191. 位1的个数
 * 位运算
 * @author adv
 * @date 2021/3/29 14:26
 */
public class Numberof1Bits {

    /**
     * 循环检查二进制位
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int ret = 0;
        // 32位循环
        for (int i = 0; i < 32; i++) {
            // 1 & 1 = 1 || 0 & 1 = 0
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * 位运算优化
     * @param n
     * @return
     */
    public int hammingWeight1(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    /*作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode-solution-jnwf/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}
