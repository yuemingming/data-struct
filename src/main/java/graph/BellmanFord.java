package graph;

import java.util.Arrays;

/**
 * 在包含负权边的图中求解一个点到所有点的最短距离
 */
public class BellmanFord {
    /**
     * 获取 start 到所有节点的最短距离
     *
     * @param graph 图
     * @param start 起点
     * @return 最短距离，如果有负环，则返回 null
     */
    public static int[] bellmanFord(int[][] graph, int start) {
        int[] distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE / 2);
        distance[start] = 0;
        boolean flag = false;
        for (int i = 0; i < graph.length; i++) {
            flag = false;
            for (int j = 0; j < graph.length; j++) {
                if (distance[j] == Integer.MAX_VALUE / 2) {
                    continue;
                }
                for (int k = 0; k < graph.length; k++) {
                    if (graph[j][k] != Integer.MAX_VALUE / 2 && distance[k] > distance[j] + graph[j][k]) {
                        distance[k] = distance[j] + graph[j][k];
                        flag = true;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        if (flag) {
            return null;
        }
        return distance;
    }
}
