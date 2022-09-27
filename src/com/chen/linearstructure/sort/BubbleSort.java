package com.chen.linearstructure.sort;




import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {10, 1, 0, 50, 30};
//        System.out.println("排序前arr=" + Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后arr=" + Arrays.toString(arr));
        //冒泡排序的时间复杂度=O(n²),测试冒泡排序在处理较大数组所消耗的时间
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000);//随机生成[0,80000)之间的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间" + date1Str);

//        bubbleSort(arr);
        sort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间" + date2Str);

    }

    //冒泡排序(从小到大)
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//优化冒泡排序
        //外层循环:需要循环比较的次数
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数大于后面的数就交换
                if (arr[j] > arr[j + 1]) {
                    //交换成功
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(flag){
                //如果交换成功则把flag充值为false,继续向下执行
                flag = false;
            }else {
                //如果没有发生交换,退出循环
                break;
            }
        }
    }

    //从大到小
    public static void sort(int[] arr){
        int temp = 0;
        boolean flag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                //如果前面的数小于后面的数就交换
                if(arr[j] < arr[j+1]){
                    //成功交换
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(flag){//如果成功交换,重置flag
                flag = false;
            }else{//如果没有交换,直接退出循环
                break;
            }
        }
    }
}
