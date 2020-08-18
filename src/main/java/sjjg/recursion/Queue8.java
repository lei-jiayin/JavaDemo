package sjjg.recursion;

/**
 * 八皇后问题解决
 *
 * @author adx
 * @date 2020/8/18 14:41
 */
public class Queue8 {

    // 皇后数量
    private static int max = 8;
    // 存放结果数组 索引为行 值为列(第几个皇后) 从0开始
    private static int[] array = new int[max];
    // 统计解法数量
    private static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        check(0);
        System.out.println(count);
    }

    // 放置n个皇后
    public static void check(int n){
        // 递归终止条件 当n大于8时 n的下次放置就是第九位皇后 即终止
        if (n == max){
            print();
            return;
        }
        // 放置皇后 i 为列 n 为行
        for (int i = 0; i < max; i++){
            // 将皇后 放在第i列
            array[n] = i;
            // 判断放置n皇后的时候是否冲突
            if (judge(n)){
                // 继续放置n+1个皇后
                check(n+1);
            }
            // 若冲突 就将皇后继续放置在下一列 i++
        }


    }

    //判断冲突条件 n 即第n个皇后
    public static boolean judge(int n){
        // 判断条件
        for (int i = 0; i < n; i ++){
            // 1.在同一列
            // 2.在同一个斜线
            if (array[n] == array[i] || Math.abs(array[n] - array[i]) == Math.abs(n - i)){
                return false;
            }
        }
        return true;
    }

    private static void print(){
        count++;
        for (int i = 0; i < max; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
