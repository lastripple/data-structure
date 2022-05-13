package com.troublemaker.queue;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.queue
 * @Author: troublemaker
 * @CreateTime: 2022-05-04  19:56
 * @Description: TODO
 * @Version: 1.0
 */
public class CircularQueue {

    private int sizeMax;
    private int front;
    private int rear;
    private int[] arr;

    public CircularQueue(int sizeMax) {
        this.sizeMax = sizeMax;
        this.front = 0;
        this.rear = 0;
        this.arr = new int[sizeMax];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % sizeMax == front;
    }

    public int size() {
        return (rear - front + sizeMax) % sizeMax;
    }

    public void add(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[(++rear) % sizeMax] = num;
    }

    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front = (front + 1) % sizeMax;
        return arr[front];
    }

    public int element() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[(++front) % sizeMax];
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("%d\t", arr[(i + 1) % sizeMax]);
        }
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.add(2);
        queue.add(5);
        queue.add(8);
//        System.out.println(queue.element());
        queue.add(10);
        queue.showQueue();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.add(8);
        queue.add(6);
        queue.add(3);
        queue.add(2);
        queue.showQueue();

    }

}

