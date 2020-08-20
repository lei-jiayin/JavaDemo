package sjjg.sort;

import java.util.Arrays;

/**
 * 快速排序算法
 *
 * @author adx
 * @date 2020/8/20 13:42
 */
public class QuickSort {
    public static void main(String[] args) {
        //int arr[] = {0,-9,23,0,-56,-1,78,0};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        System.out.println("开始快速排序");
        Long start = System.currentTimeMillis();
        quickSort(arr,0,arr.length - 1);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("快速排序用时：" + res);// 0.023s左右
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序 时间复杂度 nlogn 时间稍微不稳定
     * @param arr 排序数据
     * @param left 左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right){
        // 数组开始下标0
        int l = left;
        // 数组最后的下标 arr.length - 1
        int r = right;
        int tmpe = 0;
        // 找到数组中间的基准值
        int pivot = arr[(left + right)/2];
        // 当循环中l >= r 时推出循环
        while (l<r){
            // 找到左边比基准值大的数的下标
            while (arr[l] < pivot){
                // 未找到 l 前移
                l+=1;
            }
            // 找到右边比基准值小的数的下标
            while (arr[r] > pivot){
                // 未找到 r 后移
                r-=1;
            }
            // 若两边都未找到符合要求的下标则，左边 < 基准值 < 右边 推出循环
            if (l>=r){
                break;
            }
            // 开始交换两边不符合基准值要求的值
            tmpe = arr[l];
            arr[l] = arr[r];
            arr[r] = tmpe;

            // 测试发现 当其中出现相等值时，若无下面的判断，会造成死循环，l和r的值永远符合 l < r 且 r-l = 1
            // 当循环完成后 发现 arr[l] == pivot 时 r要-- 避免死循环
            if (arr[l] == pivot){
                r-=1;
            }
            // 当循环完成后 发现 arr[r] == pivot 时 l要++ 避免死循环
            if (arr[r] == pivot){
                l+=1;
            }
        }
        // 递归循环避免 l r 相等 造成栈溢出 若 l==r 则下面会无限递归最终导致死归 java.lang.StackOverflowError
        if (l == r){
            l+=1;
            r-=1;
        }
        // 递归左边 r-- 到 left 结束递归
        if (left < r){
            quickSort(arr,left, r);
        }
        // 递归右边 l++ 到 right 结束递归
        if (right > l){
            quickSort(arr, l, right);
        }

    }
}
