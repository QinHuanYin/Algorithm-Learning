package learning1;

import java.util.*;
import java.util.LinkedList;

public class GraphBFS {
    /** 要准备三个东西
     * 第一个是res，用于记录已经访问的顶点
     * 第二个是que，用于实现队列中的先进先出的关系
     * 第三个是哈希表visited，用于标记已经周边已经标记的顶点
     */

    List<Vertex> res = new ArrayList<>();
    Set<Vertex> visited = new HashSet<>();
    Queue<Vertex> que = new LinkedList<>();

    /** 其大体思路就是
     * 传进来一个邻接表，然后res保存第一个顶点
     * que中记录其邻接点
     * visited记录标记的顶点
     * 每当一个顶点访问完毕后
     * que就会弹出队首，然后传进res
     * 从而开启下一轮的访问
     * 通过que的先进先出的关系来间接实现bfs
     */

    public List<Vertex> graphBFS(GraphAdjList graphAdjList, Vertex vertex) {
        // 首先将第一个要进来的顶点添加进que中
        que.add(vertex);
        // 当队列不为空时，这个时候就是真正的广度优先遍历
        while(!que.isEmpty()) {
            // 此时的顶点为队列弹出来的队首
            Vertex vet = que.poll();

            res.add(vet);
            // 开始循环遍历邻接表中的邻接点
            // 这里要写graphAdjList.adjList.values()
            // 尤其是其中的adjList，因为adjList是它的属性，所有的方法都是根据该属性才有的
            // 所以要通过.来访问values
            for (Vertex vertexTemp : graphAdjList.adjList.get(vertex)){
                // 如果该顶点已经在哈希表中被标记了，直接跳过
                if (visited.contains(vertexTemp)) {
                    continue;
                }
                que.offer(vertexTemp);
                visited.add(vertexTemp);
            }
        }
        return res;
    }

}
