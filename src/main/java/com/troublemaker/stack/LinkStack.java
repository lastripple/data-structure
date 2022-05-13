package com.troublemaker.stack;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.stack
 * @Author: troublemaker
 * @CreateTime: 2022-05-05  18:54
 * @Description: TODO
 * @Version: 1.0
 */
public class LinkStack<T> {

    public Node<T> bottom = new Node<>();
    public Node<T> top = bottom;

    public boolean isEmpty() {
        return top == bottom;
    }

    public void push(T t) {
        Node<T> node = new Node<T>(t);
        node.next = top;
        top = node;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        } else {
            Node<T> temp = top;
            top = top.next;
            temp.next = null;
            return temp.t;
        }
    }

    public Node<T> getElement() {
        return top;
    }

    public void list() {
        Node<T> temp = top;
        while (temp != bottom) {
            System.out.print(temp.t + "\t");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkStack<Integer> stack = new LinkStack<>();
        stack.push(5);
        stack.push(6);
        stack.push(8);
        stack.push(7);
        stack.push(10);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(6);
        stack.push(8);
        stack.push(7);
        stack.push(10);
        stack.list();

    }
}

