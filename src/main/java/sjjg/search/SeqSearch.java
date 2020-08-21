package sjjg.search;

/**
 * 线性查找
 * 最简单的查找
 *
 * @author adx
 * @date 2020/8/21 14:40
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {3,4,21,4,7,-1};
        int index = seqSearch(arr, -1);
        if (index == -1){
            System.out.println("没有找到目标");
        }else {
            System.out.println("目标下标为：" + index);
        }
    }

    /**
     * 线性查找 知道满足条件的下标
     * @param arr 查找的数组
     * @param val 条件
     * @return
     */
    public static int seqSearch(int[] arr, int val){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == val){
                return i;
            }
        }
        return -1;
    }
}
