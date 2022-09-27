package com.chen.linearstructure.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        huffTree huffTree = new huffTree();
        Node root = huffTree.createHuffTree(arr);
        huffTree.preOrder(root);
    }
}
//创建一个赫夫曼树
class huffTree{
    //前序遍历
    public void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("空树..");
        }
    }
    public Node createHuffTree(int[] arr){
        //先将arr数组里的每个元素生成一个结点放到list集合中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1){
            //对list集合进行从小到大的排序(根据Node的val)
            Collections.sort(nodes);
            //1.先取出前两个结点
            Node leftNode = nodes.get(0);//集合中val最小的结点
            Node rightNode = nodes.get(1);//集合中val第二小的结点

            //2.两个结点变成一个二叉树
            Node parent = new Node(leftNode.val + rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;
            //3.从集合中移除这两个结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //4.将parent结点加入到集合中去
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
//创建结点
//要对Node的val进行排序,所以实现Comparable<Node>
class Node implements Comparable<Node>{
    int val;//结点的权值
    Node left;//指向左子结点
    Node right;//指向右子结点

    public Node(int val) {
        this.val = val;
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.val - o.val;
    }
}
