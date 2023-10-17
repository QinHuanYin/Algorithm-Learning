package learning1;

import java.util.ArrayList;
import java.util.List;

public class Array_Tree {
    private List<Integer> tree;

    public Array_Tree(List<Integer> nodes) {
        tree = new ArrayList<>(nodes);
    }

    // 根据索引算出对应值
    // 返回值类型要写Integer，因为要返回null
    public Integer val(int index) {
        if (index < 0 || index > tree.size()) {
            return null;
        }
        return tree.get(index);
    }

    // 拿到左子节点索引（因为这是一个完全二叉树，所以可以适用 2*i+1 的规则）
    public Integer left(int index) {
        if (index < 0 || index > tree.size()) {
            return null;
        }
        return index * 2 + 1;
    }

    // 拿到右子节点
    public Integer right(int index) {
        if (index < 0 || index > tree.size()) {
            return null;
        }
        return index * 2 + 2;
    }

    // 拿到父节点索引
    public Integer parent(int index) {
        if (index < 0 || index > tree.size()) {
            return null;
        }
        return (index - 1) / 2;
    }

    // 深度遍历dfs，传进来的index是索引的意思
    public void dfs(int index, String order, List res) {
        if (val(index) == null) {
            return;
        }
        // 如果是前序遍历
        if (order == "pre") {
            res.add(val(index));
        }
        dfs(left(index), order, res);

        // 如果是中序遍历
        if (order == "in") {
            res.add(val(index));
        }
        dfs(right(index), order, res);

        // 如果是后序遍历
        if (order == "post") {
            res.add(val(index));
        }
    }

    // 前序遍历
    public List<Integer> preOrder() {
        List<Integer> list = new ArrayList<>();
        dfs(0, "pre", list);
        return list;
    }

    // 中序遍历
    public List<Integer> inOrder() {
        List<Integer> list = new ArrayList<>();
        dfs(0, "in", list);
        return list;
    }

    // 后序遍历
    public List<Integer> postOrder() {
        List<Integer> list = new ArrayList<>();
        dfs(0, "post", list);
        return list;
    }

}
