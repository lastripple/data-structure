package com.troublemaker.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.serch
 * @Author: troublemaker
 * @CreateTime: 2022-05-09  21:06
 * @Description: TODO
 * @Version: 1.0
 */
public class MySearch {

    public int myBinarySearch(int[] arr, int left, int right, int num) {
        int mid = (left + right) / 2;
        if (mid <= right) {
            for (int i = left; i <= mid; i++) {
                if (arr[i] == num) {
                    return i;
                }
            }
            return myBinarySearch(arr, mid + 1, right, num);
        } else {
            return -1;
        }
    }

    //二分查找
    public List<Integer> realBinarySearch(int[] arr, int left, int right, int num, List<Integer> list) {
        if (left > right) {
            return Collections.singletonList(-1);
        } else {
            //插值查找
            //mid = left + alpha * (right - left)
//            int mid = (left + right) / 2;
            int mid = left + ((num - arr[left]) / (arr[right] - arr[left])) * (right - left);
            if (arr[mid] == num) {
                int index = mid;
                //向左扫描
                while (index >= 0 && arr[index] == num) {
                    list.add(index);
                    index--;
                }
                index = mid + 1;
                //向右扫描
                while (index <= arr.length - 1 && arr[index] == num) {
                    list.add(index);
                    index++;
                }
                return list;
            } else if (arr[mid] > num) {
                return realBinarySearch(arr, left, mid - 1, num, list);
            } else return realBinarySearch(arr, mid + 1, right, num, list);
        }
    }

    //斐波那契数列
    public int fibonacci(int n) {
        if (1 <= n && n <= 2) {
            return 1;
        } else if (n > 2) {
            return fibonacci(n - 1) + fibonacci(n - 2);
        } else {
            throw new RuntimeException("错误的n");
        }
    }

    public int[] fibonacciArray(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = fibonacci(i + 1);
        }
        return ints;
    }

    //斐波那契查找
    public int fibonacciSearch(int[] arr, int[] f, int left, int right, int key) {
        if (left > right) {
            return -1;
        } else if (left == right) {
            return arr[left] == key ? left : -1;
        } else {
            //先求k
            //f(k) = right - left + 1
            int k = 0;
            while (right - left + 1 > f[k]) {
                k++;
            }
            //求mid
            //mid = f(k-1) + left -1
            int mid = f[k - 1] + left - 1;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                return fibonacciSearch(arr, f, left, mid - 1, key);
            } else return fibonacciSearch(arr, f, mid + 1, right, key);
        }
    }

    @Test
    public void SearchTest() {
        int[] ints = {1, 3, 5, 7, 9, 10, 25, 25, 25, 54, 85, 96};
        System.out.println(myBinarySearch(ints, 0, ints.length - 1, 25));
        System.out.println("---------------------------");
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> search = realBinarySearch(ints, 0, ints.length - 1, 96, list);
        search.forEach(System.out::println);
        System.out.println("------------------");
        int[] f = fibonacciArray(20);
        System.out.println(fibonacciSearch(ints, f, 0, ints.length - 1, 96));
    }

    @Test
    public void fibonacciTest() {
        int[] ints = fibonacciArray(20);
        for (int anInt : ints) {
            System.out.printf("%d\t", anInt);
        }
    }
}

