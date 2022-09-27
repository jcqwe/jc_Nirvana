package com.chen.linearstructure.avltree;

/**
 * 平衡二叉树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.midOrder();
        System.out.println("平衡处理后");
        System.out.println("树的高度=" + avlTree.getRoot().height());//4
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());//1
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());//3
        System.out.println("当前的根结点=" + avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;//根节点

    public Node getRoot() {
        return root;
    }

    //找到待删除的结点
    public Node search(int val) {
        if (root != null) {
            return root.search(val);
        } else {
            return null;
        }
    }

    //找到待删除结点的父结点
    public Node searchParent(int val) {
        if (root != null) {
            return root.searchParent(val);
        } else {
            return null;
        }
    }

    //删除以node为根节点的二叉排序树的最小结点
    public int delRightMin(Node node) {
        Node targetNode = node;
        //循环查找左子结点,就会找到最小值
        while (targetNode.left != null) {
            targetNode = targetNode.left;
        }
        delNode(targetNode.val);
        return targetNode.val;
    }

    //删除结点
    public void delNode(int val) {
        if (root == null) {
            return;
        } else {
            //找到待删除结点
            Node targetNode = search(val);
            if (targetNode == null) {//如果待删除结点为null,就返回
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到待删除结点的父结点
            Node parent = searchParent(val);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断待删除的结点是其父结点的左子结点还是右子结点
                if (parent.left != null && parent.left.val == val) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.val == val) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//要删除的结点是子树的根节点
                int minVal = delRightMin(targetNode.right);
                targetNode.val = minVal;
            } else {//要删除的结点有一个左子结点或右子结点
                if (targetNode.left != null) {//待删除的结点有一个左子结点
                    if (parent != null) {
                        if (parent.left.val == targetNode.val) {//判断待删除结点是父结点的左子结点还是右子结点
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//待删除的结点有一个右子结点
                    if (parent != null) {
                        if (parent.left.val == targetNode.val) {//判断待删除结点是父结点的左子结点还是右子结点
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

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
    //获取当前结点的左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }
    //获取当前结点的右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }
    //获取当前树的高度,及以当前结点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
    //左旋转
    public void leftRotate(){
        //创建一个新的结点(新结点的值等于当前结点的值);
        Node newNode = new Node(val);
        //新结点的左子结点等于当前结点的左子结点
        newNode.left = left;
        //新结点的右子结点等于当前结点的右子结点的左子结点
        newNode.right = right.left;
        //把当前结点的值改为当前结点的右子树
        val = right.val;
        //当前结点的左子结点指向新结点
        left = newNode;
        //当前结点的右子结点指向右子结点的右子结点
        right = right.right;
    }
    //右旋转
    public void rightRotate(){
        //创建一个新的结点(新节点的值等于当前结点);
        Node newNode = new Node(val);
        //新结点的右子结点指向当前结点的右子结点
        newNode.right = right;
        //新结点的左子结点指向当前结点的左子结点的右子结点
        newNode.left = left.right;
        //当前结点的值设置为左子结点的值
        val = left.val;
        //当前结点的左子结点指向左子结点的左子结点
        left = left.left;
        //当前结点的右子结点指向新的结点
        right = newNode;
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
        //添加新结点时  如果(右子树的高度 - 左子树的高度)>1 左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果当前结点的右子树的左子树的高度大于当前结点的右子树的右子树
            if(right !=null && right.leftHeight() > right.rightHeight()){
                //先对当前结点的右子树进行右旋转
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate();
            }
            return;
        }
        //添加新结点时  如果(左子树的高度 - 右子树的高度)>1 右旋转
        if(leftHeight() - rightHeight() > 1){
            //如果当前结点的左子树的右子树的高度大于当前结点的左子树的左子树
            if(left !=null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左子树进行左旋转
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
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

