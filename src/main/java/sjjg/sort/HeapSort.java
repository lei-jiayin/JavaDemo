package sjjg.sort;

import java.util.Arrays;

/**
 * 堆排序 基于树
 * @author adv
 * @date 2020/9/17 14:00
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
//        adjustHeap(arr,arr.length/2 - 1,arr.length);
    }

    public static void heapSort(int[] arr){
        System.out.println("堆排序");
        adjustHeap(arr,arr.length/2 -1,arr.length);
        System.out.println("第一次："+ Arrays.toString(arr));

        adjustHeap(arr,0,arr.length);
        System.out.println("第二次："+ Arrays.toString(arr));
    }

    /**
     * 完成 将 以 i 对应的非叶子节点为根的树调成大顶堆
     * i=1 {4,6,8,5,9} => adjustHeap => {4,9,8,5,6}
     * 再次调用adjustHeap 传入的是 i =0 => 49856 => 96854
     * @param arr 待调整的数组
     * @param i 表示最后一个非叶子节点在数组中的索引 length/2 - 1 (注：非叶子节点也可能是根节点，根节点是非叶子节点)
     * @param length 表示对多少个元素继续调整，length是逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length){
        //先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        //开始调整
        //k=i*2+1表示这是i节点的左子节点 每次调整的都是左子节点
        for(int k = i*2+1; k < length; k=k*2+1){
            //左子节点 < 右子节点 找到左右子节点的最大值
            if (k+1<length && arr[k] < arr[k+1]){
                //k指向右子节点
                k++;
            }
            //找到的子节点大于父节点
            if (arr[k] > temp){
                //将较大的值赋给当前节点
                arr[i] = arr[k];
                //继续循环比较
                i = k;
            }else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶上（局部）
        //将temp的值放到调整后的位置
        arr[i] = temp;
//        adjustHeap(arr,length/2 - 1,--length);
    }
}
