package learning1;

import java.util.*;
import java.util.LinkedList;

public class GraphDFS {
    /** 和BFS一样
     * 要准备三个东西
     * 第一个是res，用于记录已经访问的顶点
     * 第二个是que，用于实现队列中的先进先出的关系
     * 第三个是哈希表visited，用于标记已经周边已经标记的顶点
     */

    List<Vertex> res = new ArrayList<>();
    Set<Vertex> visited = new HashSet<>();

    /** 但其逻辑和BFS很不一样
     * 其是通过找不到未标记的邻接点了
     * 所以才进行返回
     */
    public List<Vertex> graphDFS(GraphAdjList list, Vertex vertex) {
        dfs(list, res, visited, vertex);
        return res;
    }

    public void dfs(GraphAdjList list, List<Vertex> res, Set<Vertex> visited, Vertex vertex) {
        res.add(vertex);
        visited.add(vertex);
        for (Vertex vertexTemp : list.adjList.get(vertex)) {
            if (visited.contains(vertex)) {
                continue;
            }
            dfs(list, res, visited, vertexTemp);
        }
    }
}
