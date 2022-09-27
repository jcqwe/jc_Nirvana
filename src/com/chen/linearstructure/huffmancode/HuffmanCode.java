package com.chen.linearstructure.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码(压缩数据)
 */
public class HuffmanCode {
    /*
    思路:
    1.将字符串转换成字节数组,统计每个字节出现的次数,将每个字节和出现的次数生成一个结点
    2.将结点存放到list集合中
    3.对每个结点的次数进行从小到大的排序,创建赫夫曼数
     */
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//        byte[] zip = zip(contentBytes);
//        System.out.println(Arrays.toString(zip));
////        //得到一个List集合,里面存放的结点是[Node=[data=i;weight=5]...]
////        List<Node> nodeList = getNodeList(contentBytes);
////        System.out.println(nodeList);
////        //生成赫夫曼树
////        getHuffmanCode(turnHuffman(nodeList));
////        //压缩成字节数组
////        byte[] huffmanCodeByte = getHuffmanCodeByte(contentBytes, huffmanCode);
//        byte[] decode = decode(huffmanCode, zip);
//        System.out.println("解压后的=" + new String(decode));
//        decodeFile("e://123.png","e://z123.zip");
//        System.out.println("压缩ok---");
        returnFile("e://z123.zip","e://321.png");
        System.out.println("解压成功");
    }
    //解压文件
    public static void returnFile(String zipFIle, String decFile){
        //创建文件输入流
        InputStream is = null;
        ObjectInputStream ois = null;
        //创建文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFIle);
            //将输入流和对象输入流关联
            ois = new ObjectInputStream(is);
            //读取压缩文件的压缩后的字节数组
            byte[] zip = (byte[]) ois.readObject();
            //读取赫夫曼编码对照表
            Map<Byte, String> huffmanCode = (Map<Byte, String>) ois.readObject();

            byte[] res = decode(huffmanCode, zip);
            //输出
            os = new FileOutputStream(decFile);
            os.write(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //压缩文件
    /**
     * @param srcFile ：读取的文件路径
     * @param decFile : 压缩后文件的路径
     */
    public static void decodeFile(String srcFile, String decFile) {
        //创建文件输入流读取文件
        FileInputStream is = null;
        //创建文件输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            //将文件读取成一个字节数组
            is.read(b);
            //将该字节数组以赫夫曼编码的形式压缩成字节数组
            byte[] zip = zip(b);
            os = new FileOutputStream(decFile);
            //创建一个和os相关的对象输出流
            oos = new ObjectOutputStream(os);
            //将压缩后的字节数组输出
            oos.writeObject(zip);
            //将赫夫曼编码对照表输出
            oos.writeObject(huffmanCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param huffmanCode  :赫夫曼编码
     * @param huffmanBytes : 压缩后的字节数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @return
     */
    public static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        //将字节数组转换成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是否是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToStr(!flag, b));
        }
//        System.out.println(stringBuilder.toString());
        //创建一个map集合存放01=>32,100=>97(及赫夫曼编码对应的Asc码)
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println(map);
        List<Byte> list = new ArrayList<>();
        //遍历二进制字符串,扫描根据赫夫曼编码进行匹配
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                b = map.get(stringBuilder.substring(i, i + count));
                if (b == null) {//说明没有匹配到了
                    count++;
                } else {//匹配到了退出
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成二进制字符串
     *
     * @param b
     */
    public static String byteToStr(boolean flag, byte b) {
        int temp = b;
        //判断是否需要补位
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);
        if (flag) {//不需要
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    /**
     * @param bytes 原始字符串的字节数组
     * @return 返回压缩后的字节数组
     */
    public static byte[] zip(byte[] bytes) {
        List<Node> nodeList = getNodeList(bytes);
        getHuffmanCode(turnHuffman(nodeList));
        byte[] huffmanCodeByte = getHuffmanCodeByte(bytes, huffmanCode);
        return huffmanCodeByte;
    }

    //方法重载
    public static void getHuffmanCode(Node root) {
        if (root == null) {
            return;
        } else {
            getHuffmanCode(root.left, "0", stringBuilder);
            getHuffmanCode(root.right, "1", stringBuilder);
        }
    }

    //将字节数组转换成结点存放到list集合中
    public static List<Node> getNodeList(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        //创建一个map集合,key存放字节,value存放次数
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            Integer count = map.get(aByte);
            if (count == null) {
                map.put(aByte, 1);
            } else {
                map.put(aByte, count + 1);
            }
        }
        //遍历map
        Set<Map.Entry<Byte, Integer>> entries = map.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //将list结合中的结点转换成赫夫曼树
    public static Node turnHuffman(List<Node> nodeList) {
        while (nodeList.size() > 1) {
            //1.先对集合进行排序
            Collections.sort(nodeList);
            //2.取出集合中前两个最小的
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            //3.得到两个权值的和生成一个新的结点,并组合成一个新的二叉树
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            //4.从集合中移除前两个
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    //创建一个map集合,key存放字节,value存放赫夫曼编码
    static Map<Byte, String> huffmanCode = new HashMap<>();
    //拼接赫夫曼编码
    static StringBuilder stringBuilder = new StringBuilder();
    //生成对应的赫夫曼编码

    /**
     * @param node
     * @param code          code= 0时代表左子结点 code=1时代表右子结点
     * @param stringBuilder 用于拼接赫夫曼编码
     */
    public static void getHuffmanCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            //判断node是否是叶子结点
            if (node.data == null) {//不是叶子结点
                //向左递归
                getHuffmanCode(node.left, "0", stringBuilder1);
                //向右递归
                getHuffmanCode(node.right, "1", stringBuilder1);
            } else {//是叶子结点
                huffmanCode.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * @param bytes 原始字节数组
     * @param map   存放的是赫夫曼编码
     * @return 返回压缩后的编码
     */
    public static byte[] getHuffmanCodeByte(byte[] bytes, Map<Byte, String> map) {
        //用于拼接赫夫曼编码
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(map.get(b));
        }
        System.out.println("s=" + stringBuilder.toString());
        //每8位一组计算byte数组的大小来创建byte数组
        int len = (stringBuilder.length() + 7) / 8;
        byte[] resBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String substring;
            if (stringBuilder.length() < (i + 8)) {//不够8位
                substring = stringBuilder.substring(i);
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            resBytes[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return resBytes;
    }
}

//创建结点
class Node implements Comparable<Node> {
    Byte data;//存放字节
    int weight;//存放字节出现的次数
    Node left;//左子结点
    Node right;//右子结点

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
