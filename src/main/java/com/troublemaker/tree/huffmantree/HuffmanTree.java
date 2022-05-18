package com.troublemaker.tree.huffmantree;


import com.sun.corba.se.pept.encoding.OutputObject;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.*;


/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.tree.huffmantree
 * @Author: troublemaker
 * @CreateTime: 2022-05-16  15:13
 * @Description: TODO
 * @Version: 1.0
 */
public class HuffmanTree {

    public Node getHuffmanTree(int[] arr) {
        ArrayList<Node> nodeList = new ArrayList<>();
        for (int i : arr) {
            nodeList.add(new Node(i));
        }
        Collections.sort(nodeList);
        for (int i = 0; i < arr.length - 1; i++) {
            Node node1 = nodeList.get(0);
            Node node2 = nodeList.get(1);
            Node node = new Node(node1.data + node2.data);
            node.left = node1;
            node.right = node2;
            nodeList.add(node);
            nodeList.remove(node1);
            nodeList.remove(node2);
            Collections.sort(nodeList);
        }
        return nodeList.get(0);
    }

    public Node getHuffmanTree(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (Byte aByte : bytes) {
            map.merge(aByte, 1, Integer::sum);
        }
        ArrayList<Node> nodeList = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Byte key = entry.getKey();
            Integer value = entry.getValue();
            Node node = new Node(key, value);
            nodeList.add(node);
        }
        Collections.sort(nodeList);
        for (int i = 0; i < map.size() - 1; i++) {
            Node node1 = nodeList.get(0);
            Node node2 = nodeList.get(1);
            Node node = new Node(node1.data + node2.data);
            node.left = node1;
            node.right = node2;
            nodeList.add(node);
            nodeList.remove(node1);
            nodeList.remove(node2);
            Collections.sort(nodeList);
        }
        return nodeList.get(0);
    }

    public void getHuffmanCode(Node node, StringBuilder stringBuilder, Map<Byte, StringBuilder> map) {
        StringBuilder tempStr1 = new StringBuilder(stringBuilder);
        if (node.left != null) {
            tempStr1.append(0);
            getHuffmanCode(node.left, tempStr1, map);
        }
        StringBuilder tempStr2 = new StringBuilder(stringBuilder);
        if (node.right != null) {
            tempStr2.append(1);
            getHuffmanCode(node.right, tempStr2, map);
        }
        if (node.bytes != null && node.left == null && node.right == null) {
            map.put(node.bytes, tempStr2);
        }
    }

    public byte[] zip(byte[] bytes, Map<Byte, StringBuilder> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Byte aByte : bytes) {
            stringBuilder.append(map.get(aByte));
        }
        int len;
        int remainder = stringBuilder.length() % 8;
        if (remainder == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] zipBytes = new byte[len + 1];
        zipBytes[len] = (byte) (remainder == 0 ? 8 : remainder);
        for (int i = 0, j = 0; i < stringBuilder.length(); i += 8, j++) {
            String substring;
            if (i + 8 > stringBuilder.length()) {
                substring = stringBuilder.substring(i);
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            byte b = (byte) Integer.parseInt(substring, 2);
            zipBytes[j] = b;
        }
        return zipBytes;
    }

    public byte[] decode(byte[] bytes, Map<Byte, StringBuilder> map) {
        StringBuilder stringBuilder = new StringBuilder();
        String string;
        //先处理前面的数据
        for (int j = 0; j < bytes.length - 1; j++) {
            byte aByte = bytes[j];
            if (aByte < 0) {
                string = Integer.toBinaryString(aByte).substring(24);
            } else {
                string = Integer.toBinaryString(aByte);
                if (j == bytes.length - 2) {
                    for (int i = 0; i < bytes[bytes.length - 1] - string.length(); i++) {
                        stringBuilder.append(0);
                    }
                } else {
                    for (int i = 0; i < 8 - string.length(); i++) {
                        stringBuilder.append(0);
                    }
                }
            }
            stringBuilder.append(string);
        }

        char[] chars = stringBuilder.toString().toCharArray();
        ArrayList<Byte> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            builder.append(aChar);
            for (Map.Entry<Byte, StringBuilder> entry : map.entrySet()) {
                if (builder.toString().equals(new String(entry.getValue()))) {
                    list.add(entry.getKey());
                    builder = new StringBuilder();
                }
            }
        }
        int size = list.size();
        byte[] bytes1 = new byte[size];
        for (int i = 0; i < list.size(); i++) {
            Byte aByte = list.get(i);
            bytes1[i] = aByte;
        }
        return bytes1;
    }

    /**
     * @description: read 过程中使用 stringBuilder.append(new string(bytes,0,len))  new string过程中造成编码方式的改变
     *               read 结束后使用 getBytes()
     *               编码不同，造成字符长度不一致
     * @author: troublemaker
     * @date:  15:57
     * @param: [srcJpg, dstJpg]
     * @return: java.util.Map<java.lang.Byte,java.lang.StringBuilder>
     **/
    public Map<Byte, StringBuilder> pictureZip(File srcJpg, File dstJpg) {
        BufferedInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        Map<Byte, StringBuilder> zipMap = new HashMap<>();
        try {
            inputStream = new BufferedInputStream(new FileInputStream(srcJpg));
            outputStream = new ObjectOutputStream(new FileOutputStream(dstJpg));
            byte[] strBytes = new byte[inputStream.available()];
            inputStream.read(strBytes);
//            int len;
//            StringBuilder builder = new StringBuilder();
//            while ((len = inputStream.read(strBytes))!=-1){
//                builder.append(new String(strBytes,0,len));
//            }
//            byte[] bytes = builder.toString().getBytes();
            System.out.println("未处理的数据：" + Arrays.toString(strBytes));
            Node tree = getHuffmanTree(strBytes);
            getHuffmanCode(tree, new StringBuilder(), zipMap);
//            for (Map.Entry<Byte, StringBuilder> entry : zipMap.entrySet()) {
//                System.out.println(entry.getKey() + "  " + entry.getValue());
//            }
            byte[] zip = zip(strBytes, zipMap);
            System.out.println("压缩后的数据：" + Arrays.toString(zip));
            outputStream.writeObject(zip);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return zipMap;
    }

    /**
     * @description: write 和 writeObject和区别
     *               writeObject以对象的形式写入
     *               write
     * @author: troublemaker
     * @date:  16:32
     * @param: [srcJpg, dstJpg, map]
     * @return: void
     **/
    public void pictureDecode(File srcJpg, File dstJpg, Map<Byte, StringBuilder> map) {
        ObjectInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(srcJpg));
            outputStream = new BufferedOutputStream(new FileOutputStream(dstJpg));
            byte[] readBytes = (byte[]) inputStream.readObject();
            System.out.println("读取的元数据：" + Arrays.toString(readBytes));
            byte[] decode = decode(readBytes, map);
            outputStream.write(decode);
            System.out.println("解压后的数据：" + Arrays.toString(decode));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
//        String str = "面试法";
//        StringBuilder builder = new StringBuilder();
//        Map<Byte, StringBuilder> map = new HashMap<>();
//        byte[] bytes = str.getBytes();
        HuffmanTree tree = new HuffmanTree();
//        Node node = tree.getHuffmanTree(bytes);
//        tree.getHuffmanCode(node, builder, map);
//        byte[] zip = tree.zip(bytes, map);
//        byte[] decode = tree.decode(zip, map);
//        String s = new String(decode);
        Map<Byte, StringBuilder> map = tree.pictureZip(new File("src/main/resources/01.txt"), new File("src/main/resources/02.zip"));
        tree.pictureDecode(new File("src/main/resources/02.zip"), new File("src/main/resources/03.txt"), map);
    }

}

class Node implements Comparable<Node> {
    @NotNull
    Node left;
    @NotNull
    Node right;
    int data;
    Byte bytes;

    public Node(int data) {
        this.data = data;
    }

    public Node(Byte bytes, int data) {
        this.data = data;
        this.bytes = bytes;
    }

    @Override
    public int compareTo(Node o) {
        return this.data - o.data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", bytes=" + bytes +
                '}';
    }

    public static void inOrder(Node node) {
        //根
        System.out.println(node);
        //左
        if (node.left != null) {
            inOrder(node.left);
        }
        //右
        if (node.right != null) {
            inOrder(node.right);
        }
    }

}

