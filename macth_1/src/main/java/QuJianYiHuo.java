import java.util.List;

public class QuJianYiHuo {

    private int[] num;
    private Node[] tree;

    /**
     * @param num: array of num
     * @param ask: Interval pairs
     * @return: return the sum of xor
     */
    public int Intervalxor(int[] num, List<List<Integer>> ask) {
        // write your code here
        build(1, 1, num.length, num);

        int result = 0;
        for (List<Integer> aAsk : ask) {
            int l1 = aAsk.get(0); int r1 = aAsk.get(1);
            int l2 = aAsk.get(2); int r2 = aAsk.get(3);
            int trans = queryMax(1, l1, r1, 1, num.length) + queryMin(1, l2, r2, 1, num.length);
            result ^= trans;
        }
        return result;
    }

    private int queryMin(int rt, int ql, int qr, int l, int r) {
        if (ql <= l && r <= qr) {
            return tree[rt].mi;
        }

        int mid = (l + r) >> 1;
        int min = Integer.MAX_VALUE;
        if (ql <= mid) {
            min = Math.min(min, queryMin(rt << 1, ql, qr, l, mid));
        }
        if (mid < qr) {
            min = Math.min(min, queryMin(rt << 1 | 1, ql, qr, mid + 1, r));
        }
        return min;
    }

    private int queryMax(int rt, int ql, int qr, int l, int r) {
        if (ql <= l && r <= qr) {
            return tree[rt].mx;
        }

        int mid = (l + r) >> 1;
        int max = Integer.MIN_VALUE;
        if (ql <= mid) {
            max = Math.max(max, queryMax(rt << 1, ql, qr, l, mid));
        }
        if (mid < qr) {
            max = Math.max(max, queryMax(rt << 1 | 1, ql, qr, mid + 1, r));
        }
        return max;
    }

    private void build(int rt, int l, int r, int[] num) {
        this.num = num;
        tree = new Node[num.length * 4];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }
        doBuild(rt, l, r);
    }

    private void doBuild(int rt, int l, int r) {
        if (l == r) {
            tree[rt].mx = tree[rt].mi = num[l - 1];
            return;
        }

        int mid = (l + r) / 2;
        doBuild(rt << 1, l, mid);
        doBuild(rt << 1 | 1, mid + 1, r);

        pushup(rt);
    }

    private void pushup(int rt) {
        tree[rt].mx = Math.max(tree[rt << 1].mx, tree[rt << 1 | 1].mx);
        tree[rt].mi = Math.min(tree[rt << 1].mi, tree[rt << 1 | 1].mi);
    }


    static class Node {
        int mx;
        int mi;
    }
}
