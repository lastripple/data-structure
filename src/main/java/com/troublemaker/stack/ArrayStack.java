package com.troublemaker.stack;

import org.junit.jupiter.api.Test;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.stack
 * @Author: troublemaker
 * @CreateTime: 2022-05-05  18:25
 * @Description: TODO
 * @Version: 1.0
 */
public class ArrayStack {
    private int maxSize;
    private int top;
    private int bottom;
    private int[] arr;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.bottom = -1;
        this.top = -1;
        this.arr = new int[maxSize];
    }

    public Boolean isFull() {
        return top == maxSize - 1;
    }

    public Boolean isEmpty() {
        return top == bottom;
    }

    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("栈已满");
        } else {
            arr[++top] = num;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        } else {
            return arr[top--];
        }
    }

    public void list() {
        for (int i = top; i > -1; i--) {
            System.out.printf("%d\t", arr[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(5);
        stack.push(4);
        stack.push(4);
        stack.push(4);
        stack.push(1);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.list();
    }
}

