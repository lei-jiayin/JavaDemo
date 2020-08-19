package sjjg.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author adx
 * @date 2020/8/19 14:14
 */
public class SelectSort {
    public static void main(String[] args) {
        // 测试
        //int[] arr = {1000,34,119,122};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        Long start = System.currentTimeMillis();
        System.out.println("开始选择排序");
        selectSort(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("最终选择排序结果");
        System.out.println("排序时间为：" + res);// 3s 左右
        //System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){
        int min;//最小值
        int minIndex = 0;//最小值索引
        //排序次数
        //时间复杂度 o(n^2)
        for (int i = 0; i < arr.length - 1; i++){
            min = arr[i];
            minIndex = i;
            // 已假定索引为0的数为最小值，j要从索引为1开始遍历真的最小值（可能没有）与假定值交换
            for (int j = i + 1; j < arr.length; j++){
                // 若要是从大到小排序则只要将 min > arr[i] 改为 min < arr[i] 即可
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            // 最小值的索引没有发生变化时（上述遍历没有找到最小值及索引） 不进行交换
            if (minIndex != i){
                // 交换两个数的值
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第"+(i+1)+"轮排序之后");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
