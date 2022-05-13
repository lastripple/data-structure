package com.troublemaker.sort;

import com.sun.xml.internal.bind.v2.runtime.output.C14nXmlOutput;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.sort
 * @Author: troublemaker
 * @CreateTime: 2022-05-04  08:30
 * @Description: TODO
 * @Version: 1.0
 */
public class MySort {

    //初始化数据
    public int[] initArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Random().nextInt(8000);
        }
        return arr;
    }

    //冒泡排序基础版
    public void bubbleSort(int[] arr) {
        int temp;
        //冒泡排序总共进行 n - 1 轮
        for (int i = 0; i < arr.length - 1; i++) {
            //每轮进行交换 n - 1 - i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //冒泡排序优化版
    public void bubbleSortOptimization(int[] arr) {
        int temp;
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    //快排
    public static void quickSort(int[] arr, int front, int rear) {
        //递归退出条件
        //假设最后两个数索引为 (1 2)  (0 1)
        //在1处退出
        //进入递归
        // QuickSort(arr,front:1,0);
        // QuickSort(arr,2,rear:1);
//        if(front >= rear){
//            return;
//        }
        //以arr[0]为基准点
        //每一轮排序将确定该基准的在有序序列的位置
        int temp = arr[front];
        //定义俩指针
        int i = front;
        int j = rear;
        int t;
        while (i != j) {
            //右边找到小于key的值
            while (arr[j] >= temp && i < j) {
                j--;
            }
            //左边找到大于key的值
            while (arr[i] <= temp && i < j) {
                i++;
            }
            //退出循环有两种情况 i = j (即没有找到,此时j右边全是大于等于key,左边都是小于key),  i < j（找到了）
            //交换俩数位置
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            } else {
                // i = j
                //交换arr[front]和退出时的位置
                arr[front] = arr[i];
                arr[i] = temp;
            }
        }
        if ((i - 1) > front) {
            quickSort(arr, front, i - 1);
        }
        if ((i + 1) < rear) {
            quickSort(arr, i + 1, rear);
        }
    }

    //选择排序
    public void selectSort(int[] arr) {
        int min;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    //插入排序
    public void insertSort(int[] arr) {
        //分为有序和无序两部分
        //遍历无序区
        int temp;
        for (int i = 1; i < arr.length; i++) {
            int j;
            temp = arr[i];
            //比无序区都要大
//            if (temp > arr[i-1]) {
//                continue;
//            }
            //无序区中大于它往后移动
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    //希尔排序(插入排序升级版)
    public void shellSort(int[] arr) {
        int gap = arr.length / 2;
        int temp;
        while (gap > 0) {
            //无序区
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                int j;
                //有序区 所有分组在有序区的第一个在[0,gap)
                for (j = i - gap; j >= 0; j -= gap) {
                    if (temp < arr[j]) {
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + gap] = temp;
            }
            gap /= 2;
        }
    }

    //归并排序
    //合
    public static void merge(int left, int right, int[] arr, int[] temp) {
        int mid = (right + left) / 2;
        int i = left;
        int j = mid + 1;

        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        t = 0;
        for (i = left; i <= right; i++) {
            arr[i] = temp[t];
            t++;
        }
    }

    //分
    //类似于先序遍历
    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(left, right, array, temp);
        }

    }


    //桶排序
    public void bucketSort(int[] arr) {
        //用数组下标表示分布情况
        //由于数组是有序的
        //所以导致数据有序
        int[] baseArr = new int[arr.length];
        //将数据存入数组中,在数组中记录数据出现的次数
        for (int num : arr) {
            baseArr[num]++;
        }
        //依据不同次数进行不同的打印
        for (int i = 0; i < baseArr.length; i++) {
//            for (int j = 0; j < baseArr[i]; j++) {
//                System.out.printf("%d\t", i);
//            }
            if (baseArr[i] > 0) {
                System.out.printf("%d\t", i);
            }
        }
    }

    //桶排序升级版
    public void bucketSortPlus(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts;
        int max = 0;
        //求最大数位数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        //最大数
        max = arr[max];
        int i = 10;
        //位数
        int count = 1;
        while (max / i != 0) {
            i *= 10;
            count++;
        }
        //开始排序
        int digit;
        for (int j = 0, k = 1; j < count; j++, k *= 10) {
            bucketElementCounts = new int[10];
            for (int num : arr) {
                //各个位数
                digit = num / k % 10;
                //放置数据并记录
                bucket[digit][bucketElementCounts[digit]++] = num;
            }
            //把数据放回原数据
            int index = 0;
            for (int m = 0; m < bucketElementCounts.length; m++) {
                if (bucketElementCounts[m] != 0) {
                    for (int n = 0; n < bucketElementCounts[m]; n++) {
                        arr[index] = bucket[m][n];
                        index++;
                    }
                }
            }
        }
    }

    @Test
    public void mySortTest() {
        long start = System.currentTimeMillis();
        int[] arr = initArray(80000000);
//        BubbleSort(arr);                    //耗时: 13530
//        BubbleSortOptimization(arr);          //耗时: 13013
//        selectSort(arr);                       //耗时: 3655
//        insertSort(arr);                        //耗时: 1015
//        shellSort(arr);                         //耗时: 31
//        quickSort(arr, 0, arr.length - 1);      //耗时: 31
//        bucketSort(arr);                   //耗时: 16
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]); //耗时: 31
        bucketSortPlus(arr);                      //耗时: 26
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
//        for (int i : arr) {
//            System.out.printf("%d\t", i);
//        }
    }

    @Test
    public void mergeSortTest() {
        int[] arr = new int[]{3, 5, 7, 102, 4856, 6584, 2315, 1151};
        mergeSort(arr, 0, arr.length - 1, new int[8]);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void bucketSortPlusTest() {
        int[] ints = {3, 6, 84, 4654654, 154, 54654123, 16542, 21541, 56465, 1231564, 6542, 3135468};
        bucketSortPlus(ints);
        System.out.println(Arrays.toString(ints));
    }
}

