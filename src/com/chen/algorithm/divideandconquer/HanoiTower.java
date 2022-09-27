package com.chen.algorithm.divideandconquer;

/**
 * 使用分治算法解决汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args){
        hanoiTower(5,'A','B','C');
    }

    /**
     *
     * @param num 塔的数量
     * @param a     A柱
     * @param b     B柱
     * @param c     C柱
     */
    public static void hanoiTower(int num,char a,char b, char c){
        /*
          思路:
          假如只有一个盘子num =1,直接A->C
          假如盘子的数量num >=2,可以把最下面的盘子和上面的所有盘子当作两部分
          1.先把上面的所有盘子移动到B
          2.再把最下面的那一个盘子移动到C
          3.最后把B的所有盘子移动到C
         */
        if(num == 1){
            System.out.println("第1个盘子从 " + a + "->" + c);
        }else{
            //1.先把上面的所有盘子移动到B
            hanoiTower(num - 1,a,c,b);
            //2.再把最下面的那一个盘子移动到C
            System.out.println("第" + num + "个盘子从 " + a + "->" + c);
            //3.最后把B的所有盘子移动到C
            hanoiTower(num-1,b,a,c);
        }
    }
}
