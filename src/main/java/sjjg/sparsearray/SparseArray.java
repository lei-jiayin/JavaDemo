package sjjg.sparsearray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 稀疏数组
 * 源二维数组 -》 稀疏数组 -》源二维数组
 * @author adx
 * @date 2020/8/6 16:18
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建初始数组
        int[][] rootArray = new int[11][11];
        rootArray[1][2] = 1;
        rootArray[2][3] = 2;
        rootArray[5][6] = 2;
        int sum = 0;
        for (int row[] : rootArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
                if (data != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        // 初始数组转换成 稀疏数组
        System.out.println("sum：" + sum);
        System.out.println("稀疏数组");
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //记录非零数据是第几个
        int count = 0;
        for (int i = 0; i < rootArray.length; i++) {
            for (int j = 0; j < rootArray[0].length; j++) {
                if (rootArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = rootArray[i][j];
                }
            }
        }

        for (int row[] : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将稀疏数组存放到磁盘中
        try {
            FileWriter fileWriter = new FileWriter("E:/map.txt");
            for (int i = 0; i < sparseArray.length; i++) {
                String line = sparseArray[i][0] + "," + sparseArray[i][1] + "," + sparseArray[i][2];
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.close();
            System.out.println("已保存在磁盘中，路径为：E:/map.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * 将稀疏数组 还原
         */
        // 从磁盘读取稀疏数组文件
        int[][] sparseArray2 = null;
        try {
            FileReader reader = new FileReader("E:/map.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            int row1 = 0;
            String line;
            Map<Integer, String[]> map = new HashMap<Integer, String[]>();
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(row1 + "---" +line);
                map.put(row1, line.split(","));
                row1++;
            }
            sparseArray2 = new int[row1][3];
            for (int i = 0; i < row1; i++) {
                sparseArray2[i][0] = Integer.parseInt(map.get(i)[0]);
                sparseArray2[i][1] = Integer.parseInt(map.get(i)[1]);
                sparseArray2[i][2] = Integer.parseInt(map.get(i)[2]);
            }
            System.out.println();
            System.out.println("从磁盘中取出的稀疏数组为：");
            for (int row[] : sparseArray2) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("还原之后的数组：");
        int rootArray2[][] = new int[sparseArray2[0][0]][sparseArray2[0][1]];
        // 有效值个数
        int isData = sparseArray2[0][2];
        /*while (isData > 0){
            for (int i = 0; i < sparseArray2[0][0]; i++){
                for (int j = 0; j < sparseArray2[0][1]; j++){
                    if (i == sparseArray2[isData][0] && j == sparseArray2[isData][1]){
                        rootArray2[i][j] = sparseArray2[isData][2];
                        isData --;
                    }
                }
            }
        }*/

        for (int i = 1; i < isData + 1; i++) {
            rootArray2[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
        }


        for (int row[] : rootArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }
}
