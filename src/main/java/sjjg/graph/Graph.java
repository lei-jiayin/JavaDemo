package sjjg.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图的实现
 * 邻接矩阵
 * @author adv
 * @date 2020/10/22 16:37
 */
public class Graph {

    //存储定点vertex
    private ArrayList<String> vertexs;
    //存储节点之间是否互通
    private int[][] edges;
    //存储边的条数
    private int numOfEdges;
    //记录某个节点是否被访问
    private boolean[] isVisited;

    //构造器 初始化矩阵参数
    public Graph(int n){
        vertexs = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接节点的下标

    /**
     *
     * @param index index
     * @return 如果存在就返回对应下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexs.size(); j++){
            if (edges[index][j] > 0){
                //存在邻接节点
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for (int j = v2 + 1; j < vertexs.size(); j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历

    /**
     *
     * @param isVisited 访问记载
     * @param i 初始0
     */
    private void dfs(boolean[] isVisited, int i){
        //输出
        System.out.print(getValueByIndex(i) + "->");
        //记录
        isVisited[i] = true;
        //查找节点v的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1){
            //未访问过
            if (!isVisited[w])
            {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }
    //dfs重载，进行dfs
    public void dfs(){
        //遍历所有的结点，进行dfs 回溯
        for (int i=0; i < getNumOfVertex(); i++){
            if (!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }



    /**
     * @return 返回节点的个数
     */
    public int getNumOfVertex(){
        return vertexs.size();
    }

    /**
     * @return 得到边的数目
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回节点i对应的数据
     * @param i 下标
     * @return 值
     */
    public String getValueByIndex(int i){
        return vertexs.get(i);
    }

    /**
     *
     * @param v1 下标
     * @param v2 下标
     * @return 返回v1 v2的权值
     */
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        /*for (int i = 0; i < vertexs.size(); i++){
            for (int j = 0; j < vertexs.size(); j++){
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }*/

        for (int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 插入顶点
     * @param vertex 顶点的值
     */
    public void insertVertex(String vertex){
        vertexs.add(vertex);
    }

    /**
     * 添加边
     * @param v1 第一个顶点的下标
     * @param v2 第二个顶点的下标
     * @param weight 约定 0 不通 1 想通
     */
    public void insertEdges(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


    public static void main(String[] args) {
        //结点个数
        int n = 5;
        //创建图对象
        Graph graph = new Graph(n);
        String[] vertexValue = {"A","B","C","D","E"};
        for (String va:vertexValue){
            graph.insertVertex(va);
        }
        //添加边
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);

        //显示
        graph.showGraph();

        //深度优先遍历
        System.out.println("深度优先遍历：");
        graph.dfs();
    }
}
