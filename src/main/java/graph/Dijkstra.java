package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Dijkstra 算法
 * 获取一个点到所有点的最短距离
 * Johnson 全源最短路径算法，可以计算任意两点之间的最短距离。
 */
public class Dijkstra {

    /**
     * 获取 start 到所有节点的最短距离
     *
     * @param graph 图
     * @param start 起点
     * @return 最短距离
     * {@link https://oi-wiki.org/graph/shortest-path}
     */
    public static int[] dijkstra(int[][] graph, int start) {
        int[] distance = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE / 2;
        }
        distance[start] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            for (int i = 0; i < graph.length; i++) {
                if (graph[cur[0]][i] != Integer.MAX_VALUE / 2 && distance[i] > distance[cur[0]] + graph[cur[0]][i]) {
                    distance[i] = distance[cur[0]] + graph[cur[0]][i];
                    queue.add(new int[]{i, distance[i]});
                }
            }
        }
        return distance;
    }
}
