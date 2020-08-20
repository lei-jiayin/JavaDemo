package sjjg.sort;

import java.util.Arrays;

/**
 * 归并排序 分治思想
 *
 * @author adx
 * @date 2020/8/20 16:56
 */
public class MergeSort {

    public static void main(String[] args) {
        //int[] arr = {8,4,5,7,1,3,6,2};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        int[] tmpe = new int[arr.length];
        System.out.println("开始归并排序：");
        Long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, tmpe);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("归并排序用时：" + res);// 0.018s左右
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序 8W数据 时间为 0.018s左右
     * @param arr
     * @param left
     * @param right
     * @param tmpe
     */
    public static void mergeSort(int[] arr,int left,int right, int[] tmpe){
        // left 和 right 相互靠近 若不添加限制 栈会溢出 不能是 while(left < right){}无限死循环
        if (left < right){
            // 获取中间索引
            int mid = (left + right) / 2;
            //递归左边
            mergeSort(arr,left,mid,tmpe);
            //递归右边
            mergeSort(arr,mid + 1, right, tmpe);
            //合并
            merge(arr, left, mid, right, tmpe);
            //System.out.println("****");
        }
    }

    // 归并
    public static void merge(int[] arr,int left, int mid, int right, int[] tmpe){
        int i = left;
        int j = mid + 1;
        int t = 0;
        // 将有序列表合并
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                tmpe[t] = arr[i];
                t+=1;
                i+=1;
            }else {
                tmpe[t] = arr[j];
                t+=1;
                j+=1;
            }
        }

        // 将其中剩余的有序列移到tmpe后面
        while (i <= mid){
            tmpe[t] = arr[i];
            t+=1;
            i+=1;
        }
        while (j <= right){
            tmpe[t] = arr[j];
            t+=1;
            j+=1;
        }
        // 将辅助数组 复制到 原来的数组
        // 在拷贝过程中 不是所有拷贝 是部分拷贝
        t = 0;
        int tmpeIndex = left;
        // tmpeIndex <= right 这里 不能改成 t <= right 会发生数组越界异常
        // 最后一次合并 tmpeIndex = 0 right = arr.length - 1 ;
        //System.out.println("tmpeIndex的值为=" + tmpeIndex + ",right的值为=" + right);
        while (tmpeIndex <= right){
            arr[tmpeIndex] = tmpe[t];
            t+=1;
            tmpeIndex+=1;
        }
    }
}
