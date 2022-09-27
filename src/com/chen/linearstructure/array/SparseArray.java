package com.chen.linearstructure.array;

/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        /*
            二维数组转稀疏数组思路:
        1.创建一个二维数组,记录该二维数组是几行几列,并记录其有效值
        2.根据稀疏数组特点创建即：(稀疏数组的行是有效值+1,列是3列)
         */
        Arrs(8,8);
    }
    public static void Arrs(int row, int column){
        //该二维数组有几个非零值
        int value = 0;
        int[][] arrs = new int[row][column];
        arrs[2][3] = 1;
        arrs[3][4] = 2;
        arrs[4][5] = 3;
        System.out.println("二维数组:");
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                //计算该二维数组有几个非零值
                if(arrs[i][j] != 0){
                    value++;
                }
                System.out.print(arrs[i][j] + " ");
            }
            System.out.println();
        }
        /*
        稀疏数组:
          行:二维数组中有效值+1
          列:3列
         */
        int[][] sparseArr = new int[value + 1][3];
        //第一行第一列记录二维数组的行,列,有效值;
        sparseArr[0][0] = row;
        sparseArr[0][1] = column;
        sparseArr[0][2] = value;
        //让稀疏数组从第二行开始记录二维数组中有效值的行,列,值;
        int count = 1;
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                if(arrs[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arrs[i][j];
                    count++;
                }
            }
        }
        //打印稀疏数组
        System.out.println("稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.print(sparseArr[i][j] + " ");
            }
            System.out.println();
        }
        //还原成二维数组
        int[][] reArrs = new int[sparseArr[0][0]][sparseArr[0][1]];
        //从第二行开始读取稀疏数组
        for (int i = 1; i < sparseArr.length; i++) {
            reArrs[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出还原后的二维数组
        System.out.println("还原后的二维数组");
        for (int i = 0; i < reArrs.length; i++) {
            for (int j = 0; j < reArrs[i].length; j++) {
                System.out.print(reArrs[i][j] + " ");
            }
            System.out.println();
        }
    }
}
