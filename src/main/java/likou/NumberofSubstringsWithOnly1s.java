package likou;

/**
 * 1513.仅含 1 的子串数
 * @author adv
 * @date 2021/3/26 17:06
 */
public class NumberofSubstringsWithOnly1s {

    /**
     * 求连续字符为1的字符串的组合个数
     * 如果一个所有字符都为 1 的字符串的长度为 k，则该字符串包含的所有字符都为 1 的子字符串（包括该字符串本身）的数量是 k * (k + 1) / 2。
     * @param s
     * @return
     */
    public int numSub(String s) {
        final int MODULO = 1000000007;
        long total = 0;
        int length = s.length();
        long consecutive = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                total += consecutive * (consecutive + 1) / 2;
                total %= MODULO;
                consecutive = 0;
            } else {
                consecutive++;
            }
        }
        total += consecutive * (consecutive + 1) / 2;
        total %= MODULO;
        return (int) total;
    }
}
