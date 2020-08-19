package sjjg.sort;

import java.util.Arrays;

/**
 * 希尔排序（缩小增量排序）
 *
 * @author adx
 * @date 2020/8/19 17:27
 */
public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        System.out.println("开始希尔交换排序");
        Long start = System.currentTimeMillis();
        shellSort(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("希尔交换排序用时：" + res);// 9s左右
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序 在插入时使用交换法实现
     * @param arr
     */
    public static void shellSort(int[] arr){
        int a = arr.length;
        int tmpe = 0;
        /*for (int gap = a/2; gap > 0; gap/=2){
            for (int i = gap; i < arr.length; i++){
                for (int j = i - gap; j >=0; j -= gap){
                    // 交换 第j个元素 与 j+a 个元素比较
                    if (arr[j] > arr[j+gap]){
                        tmpe = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = tmpe;
                    }
                }
            }
        }*/

        while (a/2 != 0){
            a = a/2;// 获得增量 逐步缩小 a即为分组步长 每个分组之内的元素比较
            for (int i = a; i < arr.length; i++){
                for (int j = i - a; j >=0; j -= a){
                    // 交换 第j个元素 与 j+a 个元素比较 大于 则进行交换
                    if (arr[j] > arr[j+a]){
                        tmpe = arr[j];
                        arr[j] = arr[j+a];
                        arr[j+a] = tmpe;
                    }
                }
            }
            //System.out.println(Arrays.toString(arr));
            //System.out.println();
        }
    }
}
