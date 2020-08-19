package sjjg.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author adx
 * @date 2020/8/19 15:38
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1,78};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        Long start = System.currentTimeMillis();
        System.out.println("开始插入排序");
        insertSort(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("插入排序用时：" + res);// 0.6s左右
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        // 待插入的数
        int insertVal;
        // 插入位置的索引
        int insertIndex;
        // O(n^2)
        // 一共插入 length - 1 次 有序表当前只有一个数arr[0]索引为0  i 从无序表开始遍历索引为1
        for (int i = 1; i < arr.length; i++){
            insertVal = arr[i];
            insertIndex = i - 1;
            // insertIndex >= 0 防止索引越界
            // insertVal < arr[insertIndex] 未找到插入位置
            // 若要想从大到小 把insertVal < arr[insertIndex] 改为 insertVal > arr[insertIndex]
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                // 当待插入的值小于有序表中的值时 有序表中的值后移 索引加1 [101(待替换), 101(后移), 119, 1, 78]
                arr[insertIndex + 1] = arr[insertIndex];
                //System.out.println(Arrays.toString(arr));
                // 插入索引前移 直至达到终止条件-1或者insertVal > arr[inserIndex] 即 insertIndex+1 为insertVal插入位置
                insertIndex--;
            }
            // while循环结束找到插入位置 此时的insertIndex始终比插入位置少一个位置 insertIndex + 1
            // 判断是否需要赋值
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.println("第"+(i)+"轮排序");
            //System.out.println(Arrays.toString(arr));
        }

    }
}
