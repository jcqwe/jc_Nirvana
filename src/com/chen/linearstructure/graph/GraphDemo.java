package com.chen.linearstructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图
 */
public class GraphDemo {
    private List<String> vertexList;//存放顶点
    private int[][] edges;//表示顶点之间的关系
    private int numOfEdge;//边的数量

    private boolean[] isVisited;//是否被访问过

    public static void main(String[] args) {
        String[] vertexs = {"A","B","C","D","E"};
        GraphDemo graph = new GraphDemo(5);
        //添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //创建边
        graph.vertexRelation(0,1,1);
        graph.vertexRelation(0,2,1);
        graph.vertexRelation(1,2,1);
        graph.vertexRelation(1,3,1);
        graph.vertexRelation(1,4,1);

        //展示图
        graph.showGraph();

        System.out.println("深度优先搜索");
        graph.dfs();
    }

    //构造器,初始化;n 表示 有几个顶点
    public GraphDemo(int n) {
        vertexList = new ArrayList<>();
        edges = new int[n][n];
        numOfEdge = 0;
        isVisited = new boolean[n];
    }
    //得到当前结点的邻接结点
    /**
     *
     * @param i 当前结点对应的下标
     * @return
     */
    public int getFirstNeighborNode(int i){
        for (int j = 0; j < vertexList.size(); j++) {
            if(edges[i][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标获取下一个邻接结点的下标
    public int getNextNeighborNode(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    /*
    步骤:
        1.访问初始结点v,并标记结点v为已访问
        2.查找结点v的第一个邻接结点w
        3.若w存在,则继续执行4,如果w不存在,则回到1,从v的下一个结点继续
        4.若w未被访问,对w进行深度优先遍历递归(即把w当作另一个v,进行步骤123)
        5.若w被访问过,查找结点v的邻接结点w的下一个邻接结点,转到3
     */
    private void dfs(boolean[] isVisited,int i){
        //访问初始结点
        System.out.print(getVertexByIndex(i) + "->");
        //设置当前结点为已访问
        isVisited[i] = true;
        //得到当前结点的邻接结点
        int w = getFirstNeighborNode(i);
        //w存在
        while (w != -1){
            //w未被访问
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //w被访问
            w = getNextNeighborNode(i, w);
        }
    }
    //对dfs进行重载遍历所有的结点,并进行dfs
    public void dfs(){
        for (int i = 0; i < getVertex(); i++) {
            //遍历所有结点看是否被访问过;
            if(!isVisited[i]){
                //未被访问进行dfs[]回溯
                dfs(isVisited,i);
            }
        }
    }

    //得到顶点的个数
    public int getVertex() {
        return vertexList.size();
    }

    //得到边的个数
    public int getNumOfEdge() {
        return numOfEdge;
    }

    //得到i下标对应的数据及0->"A" 1->"B"
    public String getVertexByIndex(int i) {
        return vertexList.get(i);
    }

    //得到v1,v2的权值及A和B的关系是0还是1
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    //展示图
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //添加顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边

    /**
     * @param v1     "A" -> "B"  "A"-> 0 "B"-> 1及A顶点对应的下标
     * @param v2     B顶点对应的下标
     * @param weight 0代表A和B没有连接  1代表A连接B
     */
    public void vertexRelation(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;
    }
}
