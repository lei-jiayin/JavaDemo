package sjjg.search;

/**
 * 插值查找
 *
 * @author adx
 * @date 2020/8/24 11:08
 */
public class InsertionSearch {
    public static void main(String[] args) {
        int[] arr = {2,8,9,15,46,78};
        int resIndex = insertionSearch(arr, arr.length - 1, 88, 0);
        System.out.println(resIndex);
    }

    /**
     * 插值查找 排序列表需要为有序的
     * @param arr 要查找的数组
     * @param high 最高位索引
     * @param key 目标值
     * @param low 最低位索引
     * @return 目标值的下标
     */
    public static int insertionSearch(int[] arr, int high, int key,int low){
        // 判断传入的参数是否越界
        if (low > high || key < arr[low] || key > arr[high]) {
            return -1;
        }
        // 取得最接近查找值的中间值
        int mid=low+((key-arr[low])/(arr[high]-arr[low]))*(high-low);
        if(key==arr[mid]) {
            // 找到值相等返回索引
            return mid;
        }else
        if(key>arr[mid]) {
            //向右递归
            return insertionSearch(arr,high,key,mid+1);
        }else
        if(key<arr[mid]) {
            //向左递归
            return insertionSearch(arr,mid-1,key,low);
        }else {
            return -1;
        }
    }
}
