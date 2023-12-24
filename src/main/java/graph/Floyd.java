package graph;

/**
 * Floyd 算法
 * 获取每两个点之间的最短距离
 */
public class Floyd {

    /**
     * 传入的 graph 需要满足，如果两点相连，则对应的值位对应边的权重。如果两个点相等，则权重为 0.
     * 否则权重为正无穷
     *
     * @param graph 图
     * @return 任意两点之间的最短距离
     */
    public static int[][] minDistanceWithN3(int[][] graph) {
        int[][][] minDistance = new int[graph.length + 1][graph.length + 1][graph.length + 1];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, minDistance[0][i + 1], 1, graph.length);
        }
        for (int k = 1; k <= graph.length; k++) {
            for (int i = 1; i <= graph.length; i++) {
                for (int j = 1; j <= graph.length; j++) {
                    minDistance[k][i][j] = Math.min(minDistance[k - 1][i][j],
                            minDistance[k - 1][i][k] + minDistance[k - 1][k][j]);
                }
            }
        }
        int[][] minDistanceResult = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(minDistance[graph.length][i + 1], 1, minDistanceResult[i], 0, graph.length);
        }
        return minDistanceResult;
    }

    /**
     * 传入的 graph 需要满足，如果两点相连，则对应的值位对应边的权重。如果两个点相等，则权重为 0.
     * 否则权重为正无穷
     *
     * @param graph 图
     * @return 任意两点之间的最短距离
     */
    public static int[][] minDistanceWithN3Optimize(int[][] graph) {
        int[][] minDistance = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, minDistance[i], 0, graph.length);
        }
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    minDistance[i][j] = Math.min(minDistance[i][j],
                            minDistance[i][k] + minDistance[k][j]);
                }
            }
        }
        return minDistance;
    }
}
