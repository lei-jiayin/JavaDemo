package sjjg.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * @author adx
 * @date 2020/8/25 11:04
 */
public class FibonacciSearch {

    // 定义斐波那契数列大小
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000};
        System.out.println(fibSearch(arr, 1));
    }

    // 创建一个斐波那契数列
    // 非递归
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i = 2; i < f.length; i++){
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 使用非递归的方式实现算法
     * @param arr 数组
     * @param key 关键码
     * @return 下标
     */
    public static int fibSearch(int[] arr, int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;// 斐波那契数列分割数值的下标
        int[] f = fib();//获取斐波那契数列
        int mid = 0;//存放mid的值
        //获取斐波那契分割数值的下标 f[k] 的值大于arr数组的长度 为终止条件
        while (arr.length > f[k] - 1){
            k++;
        }

        // 将目标数组拷贝到 f[k]大小的辅助数组中 f[k]可能会大于arr的长度 多的位数会补零
        int[] tmpe = Arrays.copyOf(arr, f[k]);

        // 将多的位数补齐 赋予 arr的最大索引的值 满足有序前提
        for (int i = high+1; i < tmpe.length; i++){
            tmpe[i] = arr[high];
        }

        // 循环判断key所满足的条件
        while (low <= high){
            mid = low + f[k-1] - 1;
            if (key < tmpe[mid]){
                // 在左边查找
                high = mid - 1;
                // f[k] = f[k-1] + f[k-2]
                // 继续拆分 f[k-1] = f[k-2] + f[k-3]
                k--;
            }else if (key > tmpe[mid]){
                // 在右边查找
                low = mid + 1;
                // f[k] 指的是这个数组的长度 这个长度又可以分段
                // f[k] = f[k-1] + f[k-2]
                // 继续拆分 f[k-2] = f[k-3] + f[k-4]
                k-=2;
            }else {
                // 已经找到 确定是哪个下标 因为tmpe可能会被补长 直接返回mid可能会超出原有arr的长度
                if (mid < high){
                    return mid;
                }else {
                    return high;
                }
            }
        }

        return -1;
    }
}
