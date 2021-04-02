package likou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90.子集II
 * @author adv
 * @date 2021/3/31 9:41
 */
public class SubsetsII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lss = new ArrayList<>();
        // list.add(null);
        // lss.add(list);
        // list.clear();
        int size = nums.length;
        int j=0;
        for (int i=0;i<size;i++){
            for (j=i;j<size;j++){
                List<Integer> list = new ArrayList<>();
                list.add(nums[j]);
                if (!lss.containsAll(list)){
                    lss.add(list);
                }else {
                    list.clear();
                }
            }
        }
        return lss;
    }

    static List<Integer> l;
    static List<List<Integer>> res;

    /**
     * 递归法实现
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        l = new ArrayList<>();
        res = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, 0, false);
        return res;
    }

    public static void subsetsWithDup(int[] nums, int i, boolean choosePre) {
        if(i == nums.length){
            res.add(new ArrayList(l));
            return;
        }
        // 2 2 2 2
        l.add(nums[i]);
        subsetsWithDup(nums, i+1, true);
        l.remove(l.size()-1);

        if(choosePre && nums[i-1] == nums[i])return;
        subsetsWithDup(nums, i+1, false);
    }

    /*作者：lonelyheart
    链接：https://leetcode-cn.com/problems/subsets-ii/solution/javacshuang-jie-jian-dan-jie-shi-by-lone-fjfb/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    static List<Integer> t = new ArrayList<Integer>();
    static List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 迭代法实现子集枚举 标
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<Integer>(t));
            }
        }
        return ans;
    }

    /*作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/subsets-ii/solution/zi-ji-ii-by-leetcode-solution-7inq/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{1, 2, 2};
        System.out.println(subsetsWithDup1(nums));
    }
}
