package struct;

/**
 * 树状数组维护区间修改区间和
 * <link><a href="https://oi-wiki.org/ds/fenwick/">树状数组对应解释</a></link>
 */
public class TreeArrayWithRangeEditSum {
    long tree1[];
    long tree2[];

    public TreeArrayWithRangeEditSum(int n) {
        tree1 = new long[n + 1];
        tree2 = new long[n + 1];
    }

    void add(int k, int v) {
        int v1 = k * v;
        while (k <= tree1.length) {
            tree1[k] += v;
            tree2[k] += v1;
            k += lowBit(k);
        }
    }

    long getSum(long[] tree, int k) {
        int sum = 0;
        while (k > 0) {
            sum += tree[k];
            k -= lowBit(k);
        }
        return sum;
    }

    void add(int l, int r, int v) {
        add(l, v);
        add(r + 1, -v);
    }

    long getSum(int l, int r) {
        return getSum(tree1, r) * (r + 1) - l * getSum(tree1, l - 1)
                - (getSum(tree2, r) - getSum(tree2, l - 1));
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
}
