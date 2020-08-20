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
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        System.out.println("开始希尔交换排序");
        Long start = System.currentTimeMillis();
        shellSort(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("希尔交换排序用时：" + res);// 9s左右
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        System.out.println("开始希尔移位排序");
        Long start = System.currentTimeMillis();
        shellSort2(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("希尔移位排序用时：" + res);// 0.02s左右
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序 在插入时使用交换法实现 8W数据 9s左右 在if else 中加入break 排序加快 为 0.025s左右
     * 个人认为 这是 分组加冒泡排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        int a = arr.length;
        int tmpe = 0;
        // for循环实现
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

        // while 实现
        while (a/2 != 0){
            a = a/2;// 获得增量 逐步缩小 a即为分组步长 每个分组之内的元素比较
            for (int i = a; i < arr.length; i++){
                for (int j = i - a; j >=0; j -= a){
                    // 交换 第j个元素 与 j+a 个元素比较 大于 则进行交换
                    if (arr[j] > arr[j+a]){
                        tmpe = arr[j];
                        arr[j] = arr[j+a];
                        arr[j+a] = tmpe;
                    }else {
                        // 在此加入break 成功加快排序
                        // 对于没找到的无效循环减少了
                        break;
                    }
                }
            }
            //System.out.println(Arrays.toString(arr));
            //System.out.println();
        }
    }

    /**
     * 希尔排序 移位 0.02s
     * 分组加插入排序
     * @param arr
     */
    public static void shellSort2(int[] arr){
        //int gap = arr.length;
        int tmpe;
        // 分组
        for (int gap = arr.length / 2; gap > 0; gap/=2){
            // 开始遍历每个组 gap为每组的开始
            for (int i = gap; i < arr.length; i++){
                // 类似插入排序的移位
                int j = i;
                tmpe = arr[j];
                while (j - gap >= 0 && tmpe < arr[j - gap]){
                    arr[j] = arr[j - gap];
                    j-=gap;
                }
                arr[j] = tmpe;
            }
        }
    }
}
