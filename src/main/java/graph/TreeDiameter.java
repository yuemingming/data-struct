package graph;

public class TreeDiameter {
    /**
     * 获取树的直径,进行一次 DFS
     *
     * @param graph    图
     * @param start    起点
     * @param father   父节点
     * @param distance 距离
     * @param current  当前最远点
     * @return 最远点
     */
    public int dfs(int[][] graph, int start, int father, int[] distance, int current) {
        for (int i = 0; i < graph.length; i++) {
            if (i == father) {
                continue;
            }
            distance[i] = distance[start] + 1;
            if (distance[i] > distance[current]) {
                current = i;
            }
            current = dfs(graph, i, start, distance, current);
        }
        return current;
    }

    /**
     * 获取直径
     *
     * @param graph 图
     * @return 直径
     */
    public int DiameterWithDfs(int[][] graph) {
        int[] distance = new int[graph.length];
        int current = dfs(graph, 0, -1, distance, 0);
        distance = new int[graph.length];
        current = dfs(graph, current, -1, distance, current);
        return distance[current];
    }

    public int dfsDiameter(int[][] graph, int start, int father, int[] distance1, int[] distance2) {
        distance1[start] = 0;
        distance2[start] = 0;
        int max = 0;
        for (int i = 0; i < graph.length; i++) {
            if (i == father) {
                continue;
            }
            max = Math.max(max, dfsDiameter(graph, i, start, distance1, distance2));
            if (distance1[i] + 1 > distance1[start]) {
                distance2[start] = distance1[start];
                distance1[start] = distance1[i] + 1;
            } else if (distance1[i] + 1 > distance2[start]) {
                distance2[start] = distance1[i] + 1;
            }
        }
        return Math.max(max, distance1[start] + distance2[start]);
    }
}
