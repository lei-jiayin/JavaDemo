package sjjg.recursion;

/**
 * 递归解决 迷宫问题
 *
 * @author adx
 * @date 2020/8/18 9:29
 */
public class MiGong {
    public static void main(String[] args) {
        // 初始化数组 将墙置为1
        int[][] map = new int[8][7];
        initArray(map);
        print(map);
        System.out.println("开始");
        //setWay(map,1,1,6,5);// 下右上左
        setWay2(map,1,1,6,5);// 上右下左
        //System.out.println(map[0][0]);
    }

    /**
     * 找到通路
     * 1.map[i][j] 为0时表示该点没有走过 为1表示墙 为2通路可以走 为3表示点已走过，走不通
     * 2.规则 下-》右-》上-》左
     * @param map 地图
     * @param i 初始位置
     * @param a 目标位置
     * @return 找到即为真
     */
    public static boolean setWay(int[][] map, int i, int j, int a, int b){
        if (map[a][b] == 2){
            System.out.println();
            print(map);
            // 已找到目标
            return true;
        }else {
            //表示i,j还没走过
            if (map[i][j] == 0){
                map[i][j] = 2;
                //System.out.println();
                //print(map);
                // 下 右 上 左
                if (setWay(map,i + 1,j,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else if (setWay(map, i, j+1,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else if (setWay(map, i-1, j,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else if (setWay(map, i, j-1,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else {
                    map[i][j] = 3;
                    //System.out.println();
                    //print(map);
                    return false;
                }
            }else {
                //print(map);
                return false;
            }
        }
    }

    /**
     * 找到通路
     * 1.map[i][j] 为0时表示该点没有走过 为1表示墙 为2通路可以走 为3表示点已走过，走不通
     * 2.规则 上-》右-》下-》左
     * @param map 地图
     * @param i 初始位置
     * @param a 目标位置
     * @return 找到即为真
     */
    public static boolean setWay2(int[][] map, int i, int j, int a, int b){
        if (map[a][b] == 2){
            System.out.println();
            print(map);
            // 已找到目标
            return true;
        }else {
            //表示i,j还没走过
            if (map[i][j] == 0){
                map[i][j] = 2;
                //System.out.println();
                //print(map);
                // 上 右 下 左
                if (setWay2(map,i - 1,j,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else if (setWay2(map, i, j+1,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else if (setWay2(map, i+1, j,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else if (setWay2(map, i, j-1,a,b)){
                    //System.out.println();
                    //print(map);
                    return true;
                }else {
                    map[i][j] = 3;
                    //System.out.println();
                    //print(map);
                    return false;
                }
            }else {
                //print(map);
                return false;
            }
        }
    }

    private static void initArray(int[][] map) {
        //map = new int[map.length][map[0].length];
        // 上下置为1 0行 7行
        for (int i = 0; i < map[0].length; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右置为1 0列 6列
        for (int i = 0; i < map.length; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //map[1][2] = 1;
        //map[2][2] = 1;
    }

    public static void print(int[][] map){
        // 打印
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
