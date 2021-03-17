package likou;

/**
 * 不同的子序列
 *
 * @author adv
 * @date 2021/3/17 13:56
 */
public class DistinctSubsequences {
    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];// + 1 是因为有空集
        //s为空 空集不存在非空子序列
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;
        }
        //t为空 空集为任何序列的子序列
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 关键在这 s[i-1] == t[j-1] 若匹配 dp[i-1][j-1] 若不匹配 dp[i-1][j]
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // 关键在这 s[i-1]!=t[j-1] dp[i-1][j]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }

    /* 作者：Booooo_
     链接：https://leetcode-cn.com/problems/distinct-subsequences/solution/bu-tong-de-zi-xu-lie-dong-tai-gui-hua-zi-lsqb/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public static void main(String[] args) {
        String s = "abcdefghide";
        String t = "de";
        int n = numDistinct(s, t);
        System.out.println(n);
    }
}
