package com.troublemaker.queue;


/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.queue
 * @Author: troublemaker
 * @CreateTime: 2022-05-04  16:49
 * @Description: TODO
 * @Version: 1.0
 */
public class ArrayQueue {

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.rear = -1;
        this.front = -1;
        this.arr = new int[maxSize];
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public void add(int num) {
        if (isFull()) {
            throw new RuntimeException("抱歉,队列已满。");
        } else {
            arr[++rear] = num;
        }
    }

    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("抱歉,队列已空");
        } else {
            front++;
        }
        return arr[front];
    }

    public int element() {
        if (isEmpty()) {
            throw new RuntimeException("抱歉,队列为空");
        } else {
            return arr[front + 1];
        }
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            for (int i = front + 1; i <= rear; i++) {
                System.out.printf("%d\t", arr[i]);
            }
        }

    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.add(10);
        queue.add(3);
        System.out.println(queue.remove() + ",已被移除");
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();

    }

}



