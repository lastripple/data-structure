package com.troublemaker.tree.arraybinarytree;


/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.tree.arraybinarytree
 * @Author: troublemaker
 * @CreateTime: 2022-05-12  21:32
 * @Description: TODO
 * @Version: 1.0
 */
public class ArrBinaryTree {
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void perOrder() {
        this.perOrder(0);
    }

    private void perOrder(int n) {
        int left = 2 * n + 1;
        int right = 2 * n + 2;
        //根
        System.out.printf("%d\t", arr[n]);
        //左
        if (left < arr.length) {
            perOrder(left);
        }
        //右
        if (right < arr.length) {
            perOrder(right);
        }
    }

    public void inOrder() {
        this.inOrder(0);
    }

    private void inOrder(int n) {
        int left = 2 * n + 1;
        int right = 2 * n + 2;
        //左
        if (left < arr.length) {
            inOrder(left);
        }
        //根
        System.out.printf("%d\t", arr[n]);
        //右
        if (right < arr.length) {
            inOrder(right);
        }
    }

    public void postOrder() {
        this.postOrder(0);
    }

    private void postOrder(int n) {
        int left = 2 * n + 1;
        int right = 2 * n + 2;
        //左
        if (left < arr.length) {
            postOrder(left);
        }
        //右
        if (right < arr.length) {
            postOrder(right);
        }
        //根
        System.out.printf("%d\t", arr[n]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        System.out.println("-----------------前序遍历-----------------");
        tree.perOrder();
        System.out.println();
        System.out.println("-----------------中序遍历-----------------");
        tree.inOrder();
        System.out.println();
        System.out.println("-----------------后序遍历-----------------");
        tree.postOrder();

    }
}

