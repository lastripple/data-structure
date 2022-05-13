package com.troublemaker.recursion;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.recursion
 * @Author: troublemaker
 * @CreateTime: 2022-05-07  16:22
 * @Description: TODO
 * @Version: 1.0
 */
public class EightQueens {

    //默认皇后在不同行
    private int MAX = 8;
    private int[] arr = new int[MAX];
    private int n;
    private int count = 0;

    //判断第n个皇后是否可以摆放
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //不能和前面的皇后在同列 arr[i]==arr[n]
            //不能和其他皇后在同一斜线 Math.abs(n-i)==Math.abs(arr[i]-arr[n]
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    public void eightQueens(int n) {
        if (n == MAX) {
            count++;
        } else {
            for (int i = 0; i < MAX; i++) {
                arr[n] = i;
                if (judge(n)) {
                    eightQueens(n + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.eightQueens(0);
        System.out.println(eightQueens.count);

    }

}

