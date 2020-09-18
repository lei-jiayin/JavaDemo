package sjjg.sort;

import java.util.Arrays;

/**
 * 堆排序 基于完全二叉树 不稳定排序 O(nlogn)
 * i对应第几个节点（从0开始） 顺序存储树的应用
 * 大顶堆特点：arr[i]>=arr[2*i+1]&&arr[i]>=arr[2*i+2] 适用升序
 * 小顶堆特点：arr[i]<=arr[2*i+1]&&arr[i]<=arr[2*i+2] 适用降序
 * @author adv
 * @date 2020/9/17 14:00
 */
public class HeapSort {
    public static void main(String[] args) {
        // int[] arr = {4,6,8,5,9};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        Long start = System.currentTimeMillis();
        heapSort(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("排序时间为：" + res);//0.018s 8W 2.15s 800W
//        adjustHeap(arr,arr.length/2 - 1,arr.length);
    }

    public static void heapSort(int[] arr){
        System.out.println("堆排序");
        /*adjustHeap(arr,arr.length/2 -1,arr.length);
        System.out.println("第一次："+ Arrays.toString(arr));
        adjustHeap(arr,0,arr.length);
        System.out.println("第二次："+ Arrays.toString(arr));*/
        //开始排序
        //1.将无序序列构造成大顶堆 自下而上
        for(int i = arr.length / 2 - 1; i >= 0; i--){
            adjustHeap(arr,i,arr.length);
        }
        // System.out.println("大顶堆："+ Arrays.toString(arr));

        //2.将堆顶元素与末尾元素交换，将最大元素 沉 到数组末端
        //3.重新调整结构 使其满足堆定义，继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--){
            //交换 堆顶和堆尾的数据
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            /*temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;*/
            //去掉沉底的最大数据 继续构建大顶堆 自上而下
            adjustHeap(arr,0,j);
        }
        // System.out.println("排序："+ Arrays.toString(arr));

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
        //开始调整 类似于插入排序的移位
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
