package com.chen.linearstructure.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,13,11};
        binaryTree binaryTree = new binaryTree();
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(new Node(arr[i]));
        }
        System.out.println("删除结点前");
        //中序遍历
        binaryTree.midOrder();//1,3,5,7,9,10,12
        System.out.println("删除结点后");
        binaryTree.delNode(7);
        binaryTree.midOrder();
    }
}

//二叉排序树
class binaryTree {
    private Node root;//根节点

    //找到待删除的结点
    public Node search(int val){
        if(root != null){
            return root.search(val);
        }else {
            return null;
        }
    }
    //找到待删除结点的父结点
    public Node searchParent(int val){
        if(root != null){
            return root.searchParent(val);
        }else{
            return null;
        }
    }
    //删除以node为根节点的二叉排序树的最小结点
    public int delRightMin(Node node){
        Node targetNode = node;
        //循环查找左子结点,就会找到最小值
        while (targetNode.left != null){
            targetNode = targetNode.left;
        }
        delNode(targetNode.val);
        return targetNode.val;
    }
    //删除结点
    public void delNode(int val){
        if(root == null){
            return;
        }else {
            //找到待删除结点
            Node targetNode = search(val);
            if(targetNode == null){//如果待删除结点为null,就返回
                return;
            }
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //找到待删除结点的父结点
            Node parent = searchParent(val);
            //如果要删除的结点是叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //判断待删除的结点是其父结点的左子结点还是右子结点
                if(parent.left != null && parent.left.val == val){
                    parent.left = null;
                }else if(parent.right != null && parent.right.val == val){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){//要删除的结点是子树的根节点
                int minVal = delRightMin(targetNode.right);
                targetNode.val = minVal;
            }else{//要删除的结点有一个左子结点或右子结点
                if(targetNode.left != null){//待删除的结点有一个左子结点
                    if(parent != null){
                        if(parent.left.val == targetNode.val){//判断待删除结点是父结点的左子结点还是右子结点
                            parent.left = targetNode.left;
                        }else{
                            parent.right = targetNode.left;
                        }
                    }else{
                        root = targetNode.left;
                    }
                }else{//待删除的结点有一个右子结点
                    if(parent != null){
                        if(parent.left.val == targetNode.val){//判断待删除结点是父结点的左子结点还是右子结点
                            parent.left = targetNode.right;
                        }else{
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }
                }
            }
        }
    }

//    //删除结点
//    public void delNode(int no) {
//        if (root != null) {
//            if (root.val == no && root.getLeft() == null && root.getRight() == null) {
//                root = null;
//            } else if (root.val == no && root.getLeft() != null && root.getRight() == null) {
//                root = root.getLeft();
//            } else if (root.val == no && root.getLeft() == null && root.getRight() != null) {
//                root = root.getRight();
//            } else if (root.val == no && root.getLeft() != null && root.getRight() != null) {
//                root.getLeft().setRight(root.getRight());
//                root = root.getLeft();
//                if (root.getLeft().getLeft() != null && root.getLeft().getRight() != null) {
//                    root.setLeft(root.getLeft().getLeft());
//                }
//                root.setLeft(null);
//            } else {
//                root.delNode(no);
//            }
//        } else {
//            throw new RuntimeException("当前二叉树为null,无法删除");
//        }
//    }

    //添加
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void midOrder() {
        if (root == null) {
            throw new RuntimeException("当前二叉树为NULL");
        } else {
            root.midOrder();
        }
    }
}

//创建结点
class Node {
    int val;
    public Node left;//左子结点
    public Node right;//右子结点

    public Node(int val) {
        this.val = val;
    }


    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    //找到待删除的结点(targetNode)
    public Node search(int val) {
        //如果当前结点的值等于要找的结点的值就返回
        if (val == this.val) {
            return this;
        } else if (val < this.val) {//左递归寻找
            if (this.left == null) {
                return null;
            }
            return this.left.search(val);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(val);
        }
    }

    //找到待删除结点的父结点
    public Node searchParent(int val) {
        if ((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)) {
            return this;
        } else if (val < this.val && this.left != null) {//左递归寻找
            return this.left.searchParent(val);
        } else if (val > this.val && this.right != null) {
            return this.right.searchParent(val);
        } else {
            return null;
        }
    }

    //删除结点
    public void delNode(int no) {
        //如果当前结点的左子节点不为null并且是待删除结点
        if (this.left != null && this.left.val == no) {
            //如果待删除结点的左子结点不为null并且但删除结点的右子结点不为null
            if (this.left.left != null && this.right.right != null) {
                this.left.left.right = this.left.right;
                this.left = this.left.left;
                this.left.left = null;
                return;
            }
            if (this.left.left == null && this.right.right != null) {
                this.left = this.right.right;
                this.right.right = null;
                return;
            }
            if (this.left.left != null && this.right.right == null) {
                this.left = this.left.left;
                this.left.left = null;
                return;
            }
            if (this.left.left == null && this.right.right == null) {
                this.left = null;
                return;
            }
        }
        //如果当前结点的右子节点不为null并且是待删除结点
        if (this.right != null && this.right.val == no) {
            if (this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
                this.right.left = null;
                return;
            }
            if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
                this.right.right = null;
                return;
            }
            if (this.right.left != null && this.right.right == null) {
                this.right = this.right.left;
                this.right.left = null;
                return;
            }
            if (this.right.left == null && this.right.right == null) {
                this.right = null;
                return;
            }
        }
        //左递归寻找
        if (this.left != null) {
            this.left.delNode(no);
        }
        //右递归找
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //添加结点
    public void add(Node node) {
        if (node != null) {
            //如果要添加的结点小于当前结点,就把这个结点添加到当前结点的左子结点
            if (node.val < this.val) {
                if (this.left == null) {//如果当前结点的左子结点为null,就直接添加
                    this.left = node;
                } else {//否则向左递归
                    this.left.add(node);
                }
            } else {//如果要添加的结点大于等于当前结点,就把这个结点添加到当前结点的右子结点
                if (this.right == null) {//如果当前结点的右子结点为null,就直接添加
                    this.right = node;
                } else {//否则向右递归
                    this.right.add(node);
                }
            }
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }
}
