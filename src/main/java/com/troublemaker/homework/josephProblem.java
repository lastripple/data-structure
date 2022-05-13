package com.troublemaker.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.homework
 * @Author: troublemaker
 * @CreateTime: 2022-05-05  16:00
 * @Description: 有 N 个人围成一圈，每个人都有一个编号，编号由入圈的顺序决定，
 * 第一个入圈的人编号为 1，最后一个为 N，从第 k (1<=k<=N)个人开始报数，数到 m (1<=m<=N)的人将出圈，
 * 然后下一个人继续从 1 开始报数，直至所有人全部出圈，求依次出圈的编号。
 * @Version: 1.0
 */
public class josephProblem {

    private Node head = new Node(1);
    private ArrayList<Integer> list = new ArrayList<>();

    private void initList(int n) {
        Node temp = head;
        for (int i = 2; i <= n; i++) {
            temp.next = new Node(i);
            temp = temp.next;
        }
        temp.next = head;
    }

    private void changeHead(int k) {
        Node temp = head;
        while (temp.next.no != k) {
            temp = temp.next;
        }
        head = temp.next;
    }

    private void deleteAndChangeHead(int m) {
        int deleteNode = m - 1;
        head = deleteByNo(deleteNode);
    }

    private Node deleteByNo(int deleteNode) {
        Node temp = head;
        for (int i = 0; i < deleteNode; i++) {
            temp = temp.next;
        }
        list.add(temp.no);
        Node delete = head;
        while (delete.next != temp) {
            delete = delete.next;
        }
        delete.next = delete.next.next;
        return temp.next;
    }

    public List<Integer> getOutList(int n, int k, int m) {
        //初始化
        initList(n);
        //将头指针一项第k个人
        changeHead(k);
        //删除
        for (int i = 0; i < n - 1; i++) {
            deleteAndChangeHead(m);
        }
        list.add(head.no);
        return list;
    }

    @Test
    public void test() {
        josephProblem josephProblem = new josephProblem();
        List<Integer> list = josephProblem.getOutList(5, 1, 2);
        list.forEach(System.out::println);
    }


}

class Node {
    public Node next;
    public int no;

    public Node() {
    }

    public Node(int no) {
        this.no = no;
    }
}
