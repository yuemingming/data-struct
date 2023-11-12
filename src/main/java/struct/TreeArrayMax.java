package struct;

/**
 * 树状数组维护前缀最大值
 * 最大值没有办法用树状数组维护，因为树状数组只能维护区间和，而区间最大值无法通过区间和推出
 */
public class TreeArrayMax {
    long[] tree;

    public TreeArrayMax(int n) {
        tree = new long[n + 1];
    }

    void set(int k, int v) {
        while (k < tree.length) {
            tree[k] = Math.max(tree[k], v);
            k += lowBit(k);
        }
    }


    /**
     * 获取当前数二进制最低位的1对应的数</br>
     * 例如 10 的二进制为 1010，返回 2(10)
     *
     * @param x 当前数
     * @return 当前数二进制最低位的1对应的数
     */
    private static int lowBit(int x) {
        return x & (-x);
    }

    long get(int k) {
        long ans = 0;
        while (k > 0) {
            ans = Math.max(ans, tree[k]);
            k -= lowBit(k);
        }
        return ans;
    }
}
