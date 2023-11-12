package struct;

/**
 * 树状数组维护区间和
 * <link><a href="https://oi-wiki.org/ds/fenwick/">树状数组对应解释</a></link>
 */
public class TreeArraySum {

    private final long[] tree;

    public TreeArraySum(int n) {
        tree = new long[n + 1];
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

    /**
     * 给数组 X 位加上 k
     *
     * @param x 数组下标
     * @param k 增加的值
     */
    public void add(int x, int k) {
        while (x < tree.length) {
            tree[x] += k;
            x += lowBit(x);
        }
    }

    /**
     * 获取数组前 x 位的和
     *
     * @param x 数组下标
     * @return 前 x 位的和
     */
    public long getSum(int x) {
        long sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= lowBit(x);
        }
        return sum;
    }

    /**
     * 获取区间和
     *
     * @param l 左下标
     * @param r 右下标
     * @return 区间和
     */
    public long getSum(int l, int r) {
        return getSum(r) - getSum(l - 1);
    }
}
