package graph;

/**
 * 倍增算法求最近公共祖先
 */
public class TreeLcaWithMultiply {
    private static final int MAX = 40005;
    int[][] fa = new int[MAX][31];
    int[] depth = new int[MAX];

    public void dfs(int root, int father, int[][] graph) {
        // 从根节点开始，深度为 0
        fa[root][0] = father;
        depth[root] = depth[father] + 1;
        // 从 1 开始，因为 0 已经被赋值了
        // 构造各个节点各个指数级的父结点
        for (int i = 1; i < 31; i++) {
            fa[root][i] = fa[fa[root][i - 1]][i - 1];
        }
        // 递归构造各个子节点
        for (int i = 0; i < graph[root].length; i++) {
            if (i != father && graph[root][i] != 0) {
                dfs(i, root, graph);
            }
        }
    }

    /**
     * 获取两个节点的最近公共祖先
     *
     * @param x 节点 x
     * @param y 节点 y
     * @return 最近公共祖先
     */

    public int lca(int x, int y) {
        // 如果 x 的深度小于 y 的深度，交换 x 和 y
        if (depth[x] < depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        // 将 x 的深度调整到和 y 的深度相同
        int tmp = depth[y] - depth[x], ans = 0;
        for (int j = 0; tmp > 0; tmp >>= 1, j++) {
            if ((tmp & 1) == 1) {
                y = fa[y][j];
            }
        }
        // 如果 x 和 y 相等，说明 y 是 x 的祖先
        if (x == y) {
            ans = x;
        } else {
            // 从最大的指数级开始，如果 x 和 y 的 2^j 级的父节点不相同，说明 x 和 y 的最近公共祖先不在这个父节点上
            // 将 x 和 y 的父节点调整到 2^j-1 级
            for (int j = 30; j >= 0; j--) {
                if (fa[x][j] != fa[y][j]) {
                    x = fa[x][j];
                    y = fa[y][j];
                }
            }
            // 最后 x 和 y 的父节点就是最近公共祖先
            ans = fa[x][0];
        }
        return ans;
    }
}
