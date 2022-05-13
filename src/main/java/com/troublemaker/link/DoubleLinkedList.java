package com.troublemaker.link;

import com.sun.jmx.mbeanserver.NamedObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.link
 * @Author: troublemaker
 * @CreateTime: 2022-05-04  23:02
 * @Description: TODO
 * @Version: 1.0
 */
public class DoubleLinkedList {
    //头节点
    public DoubleNode head = new DoubleNode();

    public void tailAdd(DoubleNode node) {
        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    public void headAdd(DoubleNode node) {
        if (head.next == null) {
            head.next = node;
            node.pre = head;
        } else {
            node.next = head.next;
            node.pre = head;
            head.next = node;
            node.next.pre = node;
        }

    }

    public void addByNo(DoubleNode node) {
        DoubleNode temp = head;
        if (head.next == null) {
            head.next = node;
            node.pre = head;
        } else {
            while (temp.next != null) {
                if (temp.next.data > node.data) {
                    node.next = temp.next;
                    temp.next = node;
                    node.next.pre = node;
                    node.pre = temp;
                    break;
                } else {
                    temp = temp.next;
                }
            }
            temp.next = node;
            node.pre = temp;
        }
    }

    public void delete(DoubleNode node) {
        DoubleNode temp = head;
        while (temp.next != null) {
            if (temp.next.data == node.data) {
                if (temp.next.next == null) {
                    temp.next.pre = null;
                    temp.next = null;
                    break;
                } else {
                    temp.next.next.pre = temp;
                    temp.next = temp.next.next;
                    break;
                }
            } else {
                temp = temp.next;
            }
        }

    }

    public void update(DoubleNode node) {
        DoubleNode temp = head;
        while (temp.next != null) {
            if (temp.next.data == node.data) {
                if (temp.next.next == null) {
                    temp.next = node;
                    node.pre = temp;
                    break;
                } else {
                    node.next = temp.next.next;
                    node.pre = temp;
                    temp.next = node;
                    node.next.pre = node;
                    break;
                }

            } else {
                temp = temp.next;
            }
        }

    }

    public void showList() {
        DoubleNode temp = head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        DoubleNode node1 = new DoubleNode(null, null, 1, "托儿索");
        DoubleNode node2 = new DoubleNode(null, null, 2, "火影劫");
        DoubleNode node3 = new DoubleNode(null, null, 3, "小洋人");
        DoubleNode node4 = new DoubleNode(null, null, 4, "古尔岛");
        DoubleNode node5 = new DoubleNode(null, null, 5, "赛季托");
        DoubleNode node6 = new DoubleNode(null, null, 6, "人头狗");
//        list.headAdd(node1);
//        list.headAdd(node2);
//        list.headAdd(node3);
//        list.headAdd(node4);
//        list.headAdd(node5);
//        list.tailAdd(node1);
//        list.tailAdd(node2);
//        list.tailAdd(node3);
//        list.tailAdd(node4);
//        list.tailAdd(node5);
//        list.showList();
//        list.delete(node2);
//        list.delete(node4);
        list.addByNo(node1);
        list.addByNo(node5);
        list.addByNo(node6);
        list.showList();

    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DoubleNode {
    public DoubleNode pre;
    public DoubleNode next;
    public int data;
    public String nickName;

    @Override
    public String toString() {
        return "DoubleNode{" +
                "data=" + data +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

