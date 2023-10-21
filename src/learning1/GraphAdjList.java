package learning1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAdjList {
    Map<Vertex, List<Vertex>> adjList = new HashMap<>();

    // 添加所有的顶点和边
    // 实际上这里的传值还是顶点
    public GraphAdjList (Vertex[][] vertex) {
        for (Vertex[] v : vertex) {
            addVertex(v[0]);
            addVertex(v[1]);
            addEdges(v[0], v[1]);
        }
    }

    // 获取实际长度
    public int size() {
        return adjList.size();
    }

    // 添加顶点
    public void addVertex(Vertex num) {
        // 如果已经有了这个顶点了
        if (adjList.containsKey(num)) {
            return;
        }
        adjList.put(num, new ArrayList<>());
    }

    // 添加边
    public void addEdges(Vertex i, Vertex j) {
        // 首先要判断这两条边是否存在
        if (!adjList.containsKey(i) || !adjList.containsKey(j) || i == j) {
            throw new Error("边不存在或者边相等");
        }
        adjList.get(i).add(j);
        adjList.get(j).add(i);
    }

    // 删除边
    public void deleteEdges(Vertex i, Vertex j) {
        // 首先要判断这两条边是否存在
        if (!adjList.containsKey(i) || !adjList.containsKey(j) || i == j) {
            throw new Error("边不存在或者边相等");
        }
        adjList.get(i).remove(j);
        adjList.get(j).remove(i);
    }

    // 删除顶点
    public void deleteVertex(Vertex num) {
        // 如果没有这个顶点
        if (!adjList.containsKey(num)) {
            throw new Error("该顶点不存在");
        }
        adjList.remove(num);
        // 同时还要把其他定点上与之相连的边删掉
        for (int i = 0; i < size(); i++) {
            adjList.get(i).remove(num);
        }
    }

    public void printf() {
        List<Vertex> tempList = new ArrayList<>();
        for (Map.Entry<Vertex, List<Vertex>> temp: adjList.entrySet()) {
            for (Vertex tempVertex : temp.getValue()) {
                tempList.add(tempVertex);
            }
            System.out.println(temp.getKey() + ":" + tempList + ",");
        }
    }
}
