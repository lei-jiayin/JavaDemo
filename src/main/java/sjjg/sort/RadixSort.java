package sjjg.sort;


/**
 * 基数排序（桶排序扩展）
 * 赫尔曼·何乐礼
 * @author adx
 * @date 2020/8/21 11:17
 */
public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {214,57,105,4,13};
        //int[] arr = {53,3,542,748,14,214};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        System.out.println("开始基数排序：");
        Long start = System.currentTimeMillis();
        radixSort(arr);
        Long end = System.currentTimeMillis();
        Long res = end - start;
        System.out.println("基数排序用时：" + res);//8W 0.025s左右 800W0.6s左右 8000W 堆内存溢出
        //System.out.println("排序结果：");
        //System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (max < arr[i]){
                max = arr[i];
            }
        }
        // 定义十个桶
        int[][] buck = new int[10][arr.length];
        // 定义每个通的计数器 buckElementCount[0]=2 说明第0个桶中有2个数
        int[] buckElementCount = new int[10];

        // 最大数的位数
        int maxLength = (max + "").length();
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10){
            // 将每个数的每位的值放入对应的桶中
            for (int j = 0; j < arr.length; j++){
                // 求出对应的位值
                int buckElement =  arr[j] / n % 10;
                // 将值放入对应的桶中
                buck[buckElement][buckElementCount[buckElement]] = arr[j];
                // 计数器加1
                buckElementCount[buckElement]++;
            }
            // 取出桶中的数据 放入原来的arr中
            int index = 0;
            // 遍历计数器
            for (int k = 0; k < buckElementCount.length; k++){
                // 若某个通的计数不为0 则将桶中的数据赋给arr
                if (buckElementCount[k] != 0){
                    // 第k个桶有 buckElementCount[k] 个数据
                    for (int h = 0; h < buckElementCount[k]; h++){
                        // 第k个桶的第h个数据
                        arr[index] = buck[k][h];
                        index++;
                    }
                }
                // 数据回归到arr中时计数器置为0
                buckElementCount[k] = 0;
            }
            //System.out.println(Arrays.toString(arr));
        }
    }
}
