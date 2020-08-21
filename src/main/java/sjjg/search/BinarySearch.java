package sjjg.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找算法
 * 使用前提是数组有序 此处 从小到大排列
 * @author adx
 * @date 2020/8/21 16:14
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,1,8,10,89,150,1000,1000};
        //System.out.println(binarySearch(arr, 0, arr.length - 1, 10));
        List<Integer> resList = binarySearch2(arr,0, arr.length - 1, 12);
        for (int a:
             resList) {
            System.out.println(a);
        }
    }

    /**
     * 二分查找实现
     * @param arr 数组
     * @param left 左下标
     * @param right 右下标
     * @param value 查找目标值
     * @return
     */
    public static int binarySearch(int[] arr,int left, int right, int value){
        if (left <= right){
            int mid = (left + right) / 2;
            if (value < arr[mid]){
                // 左边
               return binarySearch(arr,left, mid - 1, value);
            }
            else if (value > arr[mid]){
                // 右边
               return binarySearch(arr,mid + 1, right, value);
            }else {
                return mid;
            }
        }else {
            return -1;
        }
    }

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value){
        if (left <= right){
            int mid = (left + right) / 2;
            if (value < arr[mid]){
                // 左边
               return binarySearch2(arr,left, mid - 1, value);
            }
            else if (value > arr[mid]){
                // 右边
               return binarySearch2(arr,mid + 1, right, value);
            }else {
                List<Integer> resIndexList = new ArrayList<>();
                int tmpe = mid - 1;
                while (true){
                    if (tmpe < 0 || arr[tmpe] != value){
                        break;
                    }
                    resIndexList.add(tmpe);
                    tmpe -=1;
                }
                resIndexList.add(mid);

                tmpe = mid + 1;
                while (true){
                    if (tmpe > arr.length - 1 || arr[tmpe] != value) {
                        break;
                    }
                    resIndexList.add(tmpe);
                    tmpe +=1;
                }

                return resIndexList;
            }
        }else {
            return new ArrayList<>();
        }
    }
}
