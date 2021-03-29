package likou;

import java.util.HashMap;
import java.util.Map;

/**
 * 1512. 好数对的数目
 * @author adv
 * @date 2021/3/26 16:45
 */
public class NumberofGoodPairs {
    /**
     * 暴力求解 自写
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        int size = nums.length;
        int count = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(nums[i] == nums[j]){
                    if(i < j){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 组合计数 v(v-1)/2
     * @param nums
     * @return
     */
    public int numIdenticalPairs1(int[] nums) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            int v = entry.getValue();
            ans += v * (v - 1) / 2;
        }

        return ans;
    }

    /*作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/number-of-good-pairs/solution/hao-shu-dui-de-shu-mu-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
