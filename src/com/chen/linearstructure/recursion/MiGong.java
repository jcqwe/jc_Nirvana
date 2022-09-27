package com.chen.linearstructure.recursion;

/**
 * 递归解决迷宫问题
 */
public class MiGong {
    public static void main(String[] args){
        //创建一个二维数组表示迷宫
        int[][] map = new int[8][7];

        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //打印迷宫情况
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }

        findWay(map,1,1);
        System.out.println("小球找到路后的路线");
        //打印迷宫情况
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * @param map 二维数组表示迷宫
     * @param i  小球开始的位置
     * @param j
     * @return 返回true表示找到路出迷宫
     */
    public static boolean findWay(int[][] map,int i, int j){
        /*
          1.map[i][j] = 2说明路能走,map[i][j] = 3时表示路走过但是走不通,map[i][j] = 0说明路可以走
          2.指定找路策略右->下->上->左
         */
        if(map[6][5] == 2){
            return true;
        }else {//没找到按指定的找路策略找
            if(map[i][j] == 0){
                map[i][j] = 2;
                if(findWay(map,i,j+1)){//向右找
                    return true;
                }else if(findWay(map,i+1,j)){//向下找
                    return true;
                }else if(findWay(map,i-1,j)){//向上找
                    return true;
                }else if(findWay(map,i,j-1)){//向左找
                    return true;
                }else {//走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
