package com.chen.linearstructure.tree.threadedBinaryTree;

/**
 * 线索二叉树
 */
public class ThreadedBinaryTree {
    public static void main(String[] args){
        HeroNode root = new HeroNode(1,"jc");
        HeroNode node2 = new HeroNode(3,"tom");
        HeroNode node3 = new HeroNode(6,"jack");
        HeroNode node4 = new HeroNode(8,"L");
        HeroNode node5 = new HeroNode(10,"j");
        HeroNode node6 = new HeroNode(14,"k");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //线索化二叉树
        bTree bTree = new bTree();
        bTree.setRoot(root);
        bTree.threadedNodes();
        //前驱结点
        HeroNode left = node5.getLeft();
        //后继结点
        HeroNode right = node5.getRight();

        System.out.println("10前驱结点=" + left);
        System.out.println("10后继结点=" + right);

        bTree.orderThreadedNodes();
    }
}
//二叉树
class bTree {
    private HeroNode root;//根结点

    //在递归进行线索化时,pre总是保留前一个结点
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //重载
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //遍历线索化二叉树
    public void orderThreadedNodes(){
        HeroNode node = root;
        while (node != null){
            //如果node的leftType==0就一直向左找,直到leftType==1
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    //线索化二叉树
    public void threadedNodes(HeroNode node){
        //如果node == null不能线索化
        if(node == null){
            return;
        }
        //1.线索化左子树
        threadedNodes(node.getLeft());
        //2.线索化当前结点
        //处理前驱结点
        if(node.getLeft() == null){
            //设置其前驱结点
            node.setLeft(pre);
            //修改当前结点左指针的类型
            node.setLeftType(1);
        }
        //处理后继结点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个结点后,让当前结点是下一个结点的前驱结点
        pre = node;
        //3.线索化右子树
        threadedNodes(node.getRight());
    }

    //删除结点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
                return;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("二叉树为null");
        }
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为null,无法遍历");
        }
        this.root.preOrder();
    }

    //中序遍历
    public void midOrder() {
        if (root == null) {
            System.out.println("二叉树为null,无法遍历");
        }
        this.root.midOrder();
    }

    //后序遍历
    public void afterOrder() {
        if (root == null) {
            System.out.println("二叉树为null,无法遍历");
        }
        this.root.afterOrder();
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root == null) {
            return null;
        } else {
            return this.root.preOrderSearch(no);
        }
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {
        if (root == null) {
            return null;
        } else {
            return this.root.midOrderSearch(no);
        }
    }

    //后序遍历查找
    public HeroNode afterOrderSearch(int no) {
        if (root == null) {
            return null;
        } else {
            return this.root.afterOrderSearch(no);
        }
    }
}

//结点
class HeroNode {
    private int no;//编号
    private String name;//名字
    private HeroNode left;//左子结点
    private HeroNode right;//右子结点
    //1.leftType = 0 是说明指向的是左子树, = 1说明指向的是前驱结点
    //2.rightType = 0 是说明指向的是右子树, = 1说明指向的是后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除结点
    //1.如果删除的是叶子结点就直接删除
    //2.如果删除的是非叶子结点则直接删除该子树
    public void delNode(int no) {
        /*
            1.先判断当前结点的左子结点是否为null并且判断左子结点是否为待删除结点
            2.判断当前结点的右子结点是否为null并且判断右子结点是否为待删除结点
            3.如果前两步都没有删除成功,则递归左子树
            4.递归右子树
         */
        /*
        如果要删除的结点是非叶子结点,现在我们不希望将该非叶子结点为根结点的子树删除
        规则如下:
        1.如果该非叶子结点A只有一个子结点B,则用B来代替A
        2.如果该非叶子结点A有左子结点B和右子结点C,让B代替A
        */
        if (this.left != null && this.left.no == no) {
            if (this.left.left != null && this.left.right == null) {
                this.left = this.left.left;
                this.left.left = null;
                return;
            }
            if (this.left.left == null && this.left.right != null) {
                this.left = this.right.right;
                this.right.right = null;
                return;
            }
            if (this.left.left != null && this.right.right != null) {
                this.left = this.left.left;
                this.left.left = null;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            if (this.right.left != null && this.right.right == null) {
                this.right = this.right.left;
                this.right.left = null;
                return;
            }
            if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
                this.right.right = null;
                return;
            }
            if (this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
                this.right.left = null;
                return;
            }
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历
    /*
      1.先输出根结点
      2.如果当前结点的左子结点不为null,就一直前序递归
      3.如果当前结点的右子结点不为null,就一直前序递归
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    /*
      1.如果当前结点的左子结点不为null,就一直中序递归
      2.输出根结点
      3.如果当前结点的右子结点不为null,就一直中序递归
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //后序遍历
    /*
      1.如果当前结点的左子结点不为null,就一直后序递归
      2.如果当前结点的右子结点不为null,就一直后序递归
      3.输出根结点
     */
    public void afterOrder() {
        if (this.left != null) {
            this.left.afterOrder();
        }
        if (this.right != null) {
            this.right.afterOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    /*
      1.先比较当前结点是否等于要查找的结点
      2.如果不是则判断当前结点的左子结点是否为null,如果不为null则前序递归,如果找到了则返回
      3.如果没有找到,则判断当前结点的右子结点是否为null,前序递归
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序查找");
        //比较当前结点和待查结点
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {//左子结点不为null
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//找到
            return resNode;
        }
        if (this.right != null) {//右子结点不为null
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    /*
      1.先判断当前结点的左子结点是否为null,如果不为null则中序递归,如果找到了则返回
      2.比较当前结点是否等于要查找的结点
      3.如果没有找到,则判断当前结点的右子结点是否为null,中序递归
     */
    public HeroNode midOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.midOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.midOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    /*
      1.先判断当前结点的左子结点是否为null,如果不为null则后序递归,如果找到了则返回
      2.如果没有找到,则判断当前结点的右子结点是否为null,后序递归
      3.比较当前结点是否等于要查找的结点
     */
    public HeroNode afterOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.afterOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.afterOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
