package learning1;

import com.sun.source.tree.Tree;

public class Search_Tree {

    private TreeNode root;

    // 初始化空树
    public Search_Tree() {
        root = null;
    }

    // 插入操作
    public void inset(int num) {
        // 如果是一颗空树
        if (root == null) {
            TreeNode root = new TreeNode(num);
        }

        // 如果不是一颗空树
        TreeNode pre = root;
        TreeNode temp = null;
        // 当其不等于空树时
        while (pre != null) {
            // 如果刚好相等则不做任何措施
            if (pre.val == num)
                return;
            // 保存pre成为空结点的父节点
            temp = pre;
            // 目标节点在左边
            if (pre.val > num) {
                pre = pre.left;
            }
            // 目标节点在右边
            if (pre.val < num) {
                pre = pre.right;
            }
        }
        // 将对应节点插入
        TreeNode node = new TreeNode(num);
        if (temp.val > num) {
            temp.left = node;
        } else {
            temp.right = node;
        }
    }

    // 删除操作
    public void delete(int num) {
        // 如果为空树
        if (root == null) {
            return;
        }

        // 不为空树，那就开始查找
        // 要注意这里pre存储的是cur的父节点
        // 所以一开始找到的时候，直接return，此时pre就是null，很合理
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            // 如果已经找到了，把它存储到pre中
            if (cur.val == num) {
                return;
            }
            // 如果还没有找到
            pre = cur;
            if (cur.val < num) {
                cur = cur.right;
            }
            if (cur.val > num) {
                cur = cur.left;
            }
        }

        // 如果当前节点只有 0/1 个子节点
        if (cur.left == null || cur.right == null) {
            TreeNode child = cur.left != null ? cur.left : cur.right;
            // 如果不为根节点
            if (cur != root) {
                /*if (pre.left == cur) {
                    pre.left = child;
                } else if (pre.right == cur) {
                    pre.right = child;
                }*/
                // 这样写是否也可以呢？
                if (child == null) return;
                else cur = child;
            }
            if (cur == root) {
                root = child;
            }
        }
        // 如果有两个子节点
        else {
            TreeNode treeNode = cur;
            // 需要吃到中序遍历的下一个节点
            while (treeNode.left != null) {
                treeNode = treeNode.left;
            }
            delete(treeNode.val);
            cur.val = treeNode.val;
        }
    }
}
