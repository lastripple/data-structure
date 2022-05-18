package com.troublemaker.tree.heapsort;

import javax.sql.rowset.BaseRowSet;
import java.util.Arrays;
import java.util.Random;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.tree.heapsort
 * @Author: troublemaker
 * @CreateTime: 2022-05-16  09:42
 * @Description: TODO
 * @Version: 1.0
 */
public class TreeSort {

    public void adjustHeap(int[] arr, int i, int length) {
        //方法一
//        int temp;
//        int max;
//        if (2 * i + 1 <= length - 1 && 2 * i + 2 <= length - 1) {
//            max = arr[2 * i + 1] > arr[2 * i + 2] ? 2 * i + 1 : 2 * i + 2;
//            if (arr[max] > arr[i]) {
//                temp = arr[i];
//                arr[i] = arr[max];
//                arr[max] = temp;
//                adjustHeap(arr, max, length);
//            }
//        } else if (2 * i + 1 <= length - 1 && 2 * i + 2 > length - 1) {
//            max = 2 * i + 1;
//            if (arr[max] > arr[i]) {
//                temp = arr[i];
//                arr[i] = arr[max];
//                arr[max] = temp;
//                adjustHeap(arr, max, length);
//            }
//        }
        //方法二
        int max = i;
        int temp;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < length && arr[left] > arr[max]) {
            max = left;
        }

        if (right < length && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
            adjustHeap(arr, max, length);
        }
    }

    public void heapSort(int[] arr) {
//        int temp;
//        for (int i = 0; i < arr.length - 1; i++) {
//            //构造大顶堆
//            for (int j = (arr.length - i) / 2 - 1; j >= 0; j--) {
//                adjustHeap(arr, j, (arr.length - i));
//            }
//            //交换
//            temp = arr[0];
//            arr[0] = arr[arr.length - i - 1];
//            arr[arr.length - i - 1] = temp;
//        }
        int temp;
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i >= 1; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[1] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        TreeSort treeSort = new TreeSort();
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        treeSort.heapSort(arr);
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println(currentTimeMillis2 - currentTimeMillis1);

    }
}

