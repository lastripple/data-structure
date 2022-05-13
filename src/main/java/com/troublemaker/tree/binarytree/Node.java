package com.troublemaker.tree.binarytree;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.tree
 * @Author: troublemaker
 * @CreateTime: 2022-05-11  19:14
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Node {
    public Node left;
    public Node right;
    public int lTag;
    public int rTag;
    Hero hero;

    public Node(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return "Node{" +
                "hero=" + hero +
                '}';
    }

    public void preorder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preorder();
        }
        if (this.right != null) {
            this.right.preorder();
        }
    }

    public Node preorderSearch(String no) {
        Node node = null;
        System.out.println("========");
        if (this.hero.getNo().equals(no)) {
            return this;
        } else {
            if (this.left != null) {
                node = this.left.preorderSearch(no);
            }
            if (node != null) {
                return node;
            }
            if (this.right != null) {
                node = this.right.preorderSearch(no);
            }
            return node;
        }

    }

    public void inorder() {
        if (this.left != null) {
            this.left.inorder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inorder();
        }
    }

    public Node inorderSearch(String no) {
        Node node = null;
        if (this.left != null) {
            node = this.left.inorderSearch(no);
        }
        if (node != null) {
            return node;
        }
        System.out.println("========");
        if (this.hero.getNo().equals(no)) {
            return this;
        }
        if (this.right != null) {
            node = this.right.inorderSearch(no);
        }
        return node;
    }

    public void postorder() {
        if (this.left != null) {
            this.left.postorder();
        }
        if (this.right != null) {
            this.right.postorder();
        }
        System.out.println(this);
    }

    public Node postorderSearch(String no) {
        Node node = null;
        if (this.left != null) {
            node = this.left.postorderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.postorderSearch(no);
        }
        if (node != null) {
            return node;
        }
        System.out.println("========");
        if (this.hero.getNo().equals(no)) {
            return this;
        }
        return null;
    }

    public void delete(String no) {
        if (this.left != null && this.left.hero.getNo().equals(no)) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.hero.getNo().equals(no)) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }
    }


}

