package sjjg.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 *
 * @author adx
 * @date 2020/8/19 10:18
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int array[] = {3,9,-1,10,-2};
        //int array[] = {3,9,-1,10,-2};

        // 测试大数据量排序
        // 创建随机数组
        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++){
            array[i] = (int)(Math.random() * 800000);
        }
        Long start = System.currentTimeMillis();
        bubbleSort(array);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("排序时间为：" + res); // 11s 左右
        System.out.println("排序结果" + array.length);
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array){
        boolean flag = false; // 标志数组 一次都没有进行过交换
        // i < array.length - 1 j < array.length - 1 o(n^2)
        // i < array.length - 1 j < array.length - 1 - i o(n^2 - n) true
        for (int i = 0; i < array.length - 1; i++){
            int tmp = 0;
            for (int j = 0; j < array.length - 1 - i; j++){
                if (array[j] > array[j+1]){
                    flag = true;
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            //System.out.println("打印数组");
            //System.out.println(Arrays.toString(array));

            if (!flag){ // 一趟排序中未发生交换
                break;
            }else {
                flag = false; // 重置flag 进行下躺排序判断
            }
        }
    }
}
