package com.troublemaker.tree.binarytree;

import org.junit.jupiter.api.Test;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.tree
 * @Author: troublemaker
 * @CreateTime: 2022-05-11  19:13
 * @Description: TODO
 * @Version: 1.0
 */
public class BinaryTree {
    private Node root;
    private Node pre;


    public boolean isEmpty() {
        return root == null;
    }

    public void add(Hero hero) {

    }

    public void preorder() {
        if (!isEmpty()) {
            root.preorder();
        } else throw new RuntimeException("树为空");
    }


    public Node preorderSearch(String no) {
        if (!isEmpty()) {
            return root.preorderSearch(no);
        } else throw new RuntimeException("树为空");
    }

    public void inorder() {
        if (!isEmpty()) {
            root.inorder();
        } else throw new RuntimeException("树为空");
    }

    public Node inorderSearch(String no) {
        if (!isEmpty()) {
            return root.inorderSearch(no);
        } else throw new RuntimeException("树为空");
    }

    public void postorder() {
        if (!isEmpty()) {
            root.postorder();
        } else throw new RuntimeException("树为空");
    }

    public Node postorderSearch(String no) {
        if (!isEmpty()) {
            return root.postorderSearch(no);
        } else throw new RuntimeException("树为空");
    }

    public void delete(String no) {
        if (!isEmpty()) {
            if (root.hero.getNo().equals(no)) {
                root = null;
            } else {
                root.delete(no);
            }
        } else throw new RuntimeException("树为空");
    }

    public void threadedNode() {
        this.threadedNode(root);
    }

    public void threadedNode(Node node) {
        if (node == null) {
            return;
        }
        //线索化左子树
        threadedNode(node.left);
        //线索化当前节点
        if (node.left == null) {
            node.left = pre;
            node.lTag = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            node.rTag = 1;
        }
        pre = node;
        //线索化右节点
        threadedNode(node.right);
    }

    @Test
    public void treeTest() {
        BinaryTree binaryTree = new BinaryTree();
        Node node1 = new Node(new Hero("01", "疾风剑豪", "孤儿索"));
        Node node2 = new Node(new Hero("02", "影流之主", "儿童劫"));
        Node node3 = new Node(new Hero("03", "诡术妖姬", "勒布朗"));
        Node node4 = new Node(new Hero("04", "惩戒之箭", "韦鲁斯"));
        Node node5 = new Node(new Hero("05", "虚空之女", "卡沙狗"));
        node1.left = node2;
        node1.right = node3;
        node3.left = node5;
        node3.right = node4;
        binaryTree.root = node1;
        System.out.println("---------------先序遍历--------------");
        binaryTree.preorder();
        System.out.println("---------------中序遍历--------------");
        binaryTree.inorder();
        System.out.println("---------------后序遍历--------------");
        binaryTree.postorder();
        System.out.println("---------------先序查找--------------");
        System.out.println(binaryTree.preorderSearch("04"));
        System.out.println("---------------中序查找--------------");
        System.out.println(binaryTree.inorderSearch("04"));
        System.out.println("---------------后序查找--------------");
        System.out.println(binaryTree.postorderSearch("04"));
        System.out.println("---------------删除--------------");
        binaryTree.delete("02");
        System.out.println("---------------先序遍历--------------");
        binaryTree.preorder();
        System.out.println("---------------线索化二叉树--------------");
        binaryTree.threadedNode();
        System.out.println(node5.left.hero);
        System.out.println(node5.right.hero);
    }
}

