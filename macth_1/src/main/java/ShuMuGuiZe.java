/**
 * 01 树木规划
 * https://tianchi.aliyun.com/oj/14491652514320995/73733636160164534
 */
public class ShuMuGuiZe {
    /**
     * @param trees: the positions of trees.
     * @param d: the minimum beautiful interval.
     * @return: the minimum number of trees to remove to make trees beautiful.
     */
    public int treePlanning(int[] trees, int d) {
        // write your code here.
        int count = 0;
        int len = trees.length;
        for (int i = 0; i < len - 1; ) {
            if (trees[i] + d > trees[i+1]) {
                count++;
                len--;
                if(len == i+1) {
                    break;
                }
                System.arraycopy(trees, i+2, trees, i + 1, len - i - 1);
                continue;
            }
            i++;
        }

        return count;
    }
}
