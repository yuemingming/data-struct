package struct;

public class SegmentTree {

    private final int[] tree;

    private final int[] mark;

    public SegmentTree(int[] nums) {
        tree = new int[nums.length * 4];
        mark = new int[nums.length * 4];
        buildTree(0, nums.length - 1, 0, nums);
    }

    private void buildTree(int l, int r, int p, int[] nums) {
        if (l == r) {
            tree[p] = nums[l];
            return;
        }
        int mid = l + ((r - l) >> 1);
        buildTree(l, mid, p * 2 + 1, nums);
        buildTree(mid + 1, r, p * 2 + 2, nums);
        tree[p] = tree[p * 2 + 1] + tree[p * 2 + 2];
    }

    /**
     * 查询指定取件
     *
     * @param l 查询左界
     * @param r 查询右界
     * @param s 当前节点左界
     * @param t 当前节点右界
     * @param p 当前节点
     * @return 查询结果
     */
    public int getSum(int l, int r, int s, int t, int p) {
        if (l <= s && t <= r) {
            return tree[p];
        }
        int mid = s + ((t - s) >> 1);
        if (mark[p] > 0) {
            markDownPush(s, t, mid, p);
        }
        int sum = 0;
        if (l <= mid) {
            sum += getSum(l, r, s, mid, p * 2 + 1);
        }
        if (r > mid) {
            sum += getSum(l, r, mid + 1, t, p * 2 + 2);
        }
        return sum;
    }

    private void markDownPush(int s, int t, int mid, int p) {
        tree[p * 2 + 1] += (mid - s + 1) * mark[p];
        tree[p * 2 + 2] += (t - mid) * mark[p];
        mark[p * 2 + 1] += mark[p];
        mark[p * 2 + 2] += mark[p];
        mark[p] = 0;
    }

    /**
     * 更新指定区间节点
     *
     * @param l 更新左界
     * @param r 更新右界
     * @param c 更新值
     * @param s 当前节点左界
     * @param t 当前节点右界
     * @param p 当前节点
     */
    public void update(int l, int r, int c, int s, int t, int p) {
        if (l <= s && t <= r) {
            tree[p] += (t - s + 1) * c;
            mark[p] += c;
            return;
        }
        int mid = s + ((t - s) >> 1);
        if (mark[p] != 0 && s != t) {
            markDownPush(s, t, mid, p);
        }
        if (l <= mid) {
            update(l, r, c, s, mid, p * 2 + 1);
        }
        if (r > mid) {
            update(l, r, c, mid + 1, t, p * 2 + 2);
        }
        tree[p] = tree[p * 2 + 1] + tree[p * 2 + 2];
    }


    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{10, 11, 12, 13, 14});
        int sum = segmentTree.getSum(0, 4, 0, 4, 0);
//        System.out.println(sum);
        segmentTree.update(0, 4, 1, 0, 4, 0);
        sum = segmentTree.getSum(0, 4, 0, 4, 0);
//        System.out.println(sum);
    }

}
