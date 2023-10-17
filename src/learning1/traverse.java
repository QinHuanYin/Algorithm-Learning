package learning1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class traverse {
    // 三种遍历方式的实现

    // 前序遍历
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

    // 后序遍历
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        postOrder(root.left);
        postOrder(root.right);
        list.add(root.val);
    }

    /** 前序遍历的栈递归表示方式
        其核心思想还是先进后出
        所以在进栈的时候都是先进右边
     */
    public void preOrder2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 压入根节点
        stack.push(root);
        while (!stack.empty()) {
            TreeNode treeNode = stack.pop();
            System.out.println(treeNode.val);
            list.add(treeNode.val);
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }
}
