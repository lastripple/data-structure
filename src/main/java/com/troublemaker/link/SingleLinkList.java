package com.troublemaker.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.link
 * @Author: troublemaker
 * @CreateTime: 2022-05-04  20:54
 * @Description: TODO
 * @Version: 1.0
 */
public class SingleLinkList {

    private Node head = new Node();

    //尾插法
    //找到最后一个节点，即 node.next=null
    public void tailAdd(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void headAdd(Node node) {
        node.next = head.next;
        head.next = node;
    }

    //按照编号来进行插入
    //由于链表是单向的，所以找到待插入位置的前一个位置
    // 1 4 -> 2
    public void addByNo(Node node) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.hero.no > node.hero.no) {
                node.next = temp.next;
                temp.next = node;
                break;
            } else {
                temp = temp.next;
            }
        }
        temp.next = node;
    }

    public void showList() {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp.hero);
        }
    }

    public void update(Node node) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.hero.no != node.hero.no) {
                temp = temp.next;
            } else {
                node.next = temp.next.next;
                temp.next = node;
                break;
            }
        }
    }

    public void delete(Node node) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.hero.no == node.hero.no) {
                temp.next = temp.next.next;
                break;
            } else {
                temp = temp.next;
            }
        }
    }

    public int size() {
        Node temp = head;
        int count = 0;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public Node getNode(int reverseK) {
        Node temp = head;
        int count = 0;
        while (temp.next != null) {
            count++;
            temp = temp.next;
            if (count == size() - reverseK + 1) {
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SingleLinkList list = new SingleLinkList();
        Node node1 = new Node(new Hero(1, "亚索", "疾风剑豪"), null);
        Node node2 = new Node(new Hero(2, "丽桑卓", "冰霜女巫"), null);
        Node node3 = new Node(new Hero(3, "奈德丽", "奶大力"), null);
        Node node4 = new Node(new Hero(4, "瑟提", "蓄意轰拳"), null);
        Node node5 = new Node(new Hero(5, "诺手", "小学生之手"), null);
        Node node6 = new Node(new Hero(5, "诺手", "诺克萨斯之手"), null);

        list.addByNo(node4);
        list.addByNo(node1);
        list.addByNo(node3);
        list.addByNo(node5);
        list.addByNo(node6);
        list.showList();
//        System.out.println(list.size());
//        System.out.println(list.getNode(6));
        //尾插改头插
        Node temp = list.head.next;
        Node t;
        list.head.next = null;
        while (temp != null) {
            t = temp;
            temp = temp.next;
            list.headAdd(t);
        }
        System.out.println("======================================");
        list.showList();

    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Node {
    public Hero hero;
    public Node next;

    @Override
    public String toString() {
        return "Node{" +
                "hero=" + hero +
                '}';
    }
}

@Data
@AllArgsConstructor
class Hero {
    public int no;
    public String name;
    public String nickName;

}
