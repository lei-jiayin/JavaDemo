package likou;


/**
 * 1529.灯泡开关 IV
 * 下标为i的值为1时灯亮 为0时灯灭
 * 翻转从i到n-1，状态翻转
 * 达到目标
 * @author adv
 * @date 2021/3/29 15:36
 */
public class BulbSwitcherIV {
    /**
     * 初始状态为 0000....0
     * 0到n-1
     * @param target
     * @return
     */
    public static int minFlips(String target) {
        char[] t = target.toCharArray();
        char[] chu = new char[target.length()];
        for(int i=0;i<target.length();i++){
            chu[i]='0';
        }
        int j=0;
        for (int i=t.length - j - 1; i > 0; i--){
            if (chu == t){
                break;
            }else {
                for (int k= i;k<t.length;k++){
                    if (chu[k] == '0'){
                        chu[k] = '1';
                    }else {
                        chu[k] = '0';
                    }
                }
            }
            j++;
        }
        return j;
    }

    /**
     * 标准答案
     * @param target
     * @return
     */
    public static int minFlips1(String target) {
        int flips = 0;
        char prev = '0';
        int length = target.length();
        for (int i = 0; i < length; i++) {
            char curr = target.charAt(i);
            if (curr != prev) {
                flips++;
                prev = curr;
            }
        }
        return flips;
    }

    /*作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/bulb-switcher-iv/solution/deng-pao-kai-guan-iv-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public static void main(String[] args) {
        String target = "101";
        System.out.println(minFlips1(target));
    }
}
