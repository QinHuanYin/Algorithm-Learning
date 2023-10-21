package learning1;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjList {
    List<Integer> vertices = new ArrayList<>();
    List<List<Integer>> adjMat = new ArrayList<>();

    // 传两个参数构建，一个用于构建参数列表的arr，一个用于构建边的集合edges
    public GraphAdjList(int[] arr, int[][] edges) {
        // 把传来的数组arr中的值传进给vertices
        for (int i : arr) {
            vertices.add(i);
        }

        // 构建邻接矩阵，添加顶点
        for (int i : arr) {
            addVertices(i);
        }

        // 构建邻接矩阵，添加边
        // 这里我有一点不是很明白，就是为什么它只给添加了e[0]和e[1]两个值
        // 作为难道2和之后的都不要了吗
        // 唯一一种我能想到的解释就是其是一个n行两列的数组
        for (int[] e : edges) {
            addEdges(e[0], e[1]);
        }
    }


    // 返回列表的数量
    public int size (List list) {
        return list.size();
    }

    int n = size(vertices);

    // 添加顶点，首先添加的元素全都是0
    public void addVertices(int val) {
        // 先添加行
        List<Integer> newList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            newList.add(0);
        }

        // 再添加列，注意这里的逻辑，是在每一行的最后添加0
        for (List<Integer> list : adjMat) {
            list.add(0);
        }
    }

    // 添加边，其核心逻辑就是让两边的值为1
    public void addEdges(int i, int j) {
        // 首先对i、j的索引边界进行判断
        // 如果其超出了边界就抛出错误
        if (i < 0 || j < 0 || j >= n || j >= n || i == j) {
            throw new IndexOutOfBoundsException("超出边界");
        }
        adjMat.get(i).set(j, 1);
        adjMat.get(j).set(i, 1);
    }

    // 删除顶点，这里是根据vertices的索引进行删除的
    public void deleteVertices (int index) {
        // 如果超出了范围就报错
        if (index >= n || index < 0) {
            throw new IndexOutOfBoundsException("该索引不存在");
        }

        // 顶点表中进行删除
        vertices.remove(index);

        // 先是在adjMat中删除这一行
        adjMat.remove(index);

        // 然后在所有行中，依次删除该列的值
        for (List<Integer> list : adjMat) {
            list.remove(index);
        }
    }

    // 删除边
    public void deleteEdges (int i, int j) {
        // 首先对i、j的索引边界进行判断
        // 如果其超出了边界就抛出错误
        if (i < 0 || j < 0 || j >= n || j >= n || i == j) {
            throw new IndexOutOfBoundsException("超出边界");
        }
        adjMat.get(i).set(j, 0);
        adjMat.get(j).set(i, 0);
    }
}
