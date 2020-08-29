import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * 03 大楼间穿梭
 * https://tianchi.aliyun.com/oj/14491652514320995/73733636160164532
 *
 * 到达某个点i，
 *   可以由左边某个能到达i的点过来，花费x
 *   可以由第i-1个点过来，或者第i-2个点过来，花费y
 *
 * 到达某个点i的花费记为dp[i],
 *   dp[i] = min(dp[i], dp[左边所有能到达i的点]+x)
 *   dp[i] = min(dp[i], dp[i-1]+y)
 *   dp[i] = min(dp[i], dp[i-2]+y)
 *
 */
public class DaLouJianChuanSuo {

    private Map<Integer, List<Integer>> arrivePoints;

    /**
     * @param heights: the heights of buildings.
     * @param k: the vision.
     * @param x: the energy to spend of the first action.
     * @param y: the energy to spend of the second action.
     * @return the minimal energy to spend.
     */
    public long shuttleInBuildings(int[] heights, int k, int x, int y) {
        // write your code here.
        init(heights, k, x, y);

        long[] dp = LongStream.generate(() -> Long.MAX_VALUE).limit(heights.length).toArray();
        dp[0] = 0; // 到达起点不需要花费

        for (int i = 1; i < heights.length; i++) {
            //dp[i] = min(dp[i], dp[左边所有能到达i的点]+x)
            final List<Integer> points = arrivePoints.get(i);
            if(points != null) {
                for (int point : points) {
                    dp[i] = Math.min(dp[i], dp[point] + x);
                }
            }

            //dp[i] = min(dp[i], dp[i-1]+y)
            dp[i] = Math.min(dp[i], dp[i - 1] + y);

            //dp[i] = min(dp[i], dp[i-2]+y)
            if (i >= 2) {
                dp[i] = Math.min(dp[i], dp[i - 2] + y);
            }
        }

        return dp[heights.length - 1];
    }

    private void init(int[] heights, int k, int x, int y) {
        // 单调栈
        // 找右边第一个比自己大的数字，那是能到达的最远的点，这个比自己大的数字会挡住我的去路
        LinkedList<Integer> stack = new LinkedList<>();
        int[] firstBiggerThanMe = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.getLast()] <= heights[i]) {
                Integer top = stack.removeLast();
                firstBiggerThanMe[top] = i;
            }
            stack.add(i);
        }

        //题目中 蜘蛛侠的视野为k，所以不是所有点都能去到它右边的第一个比自己大的点
        //同时，转换成 能到达某个点的所有点的集合
        this.arrivePoints = new HashMap<>();
        for (int i = 0; i < heights.length; i++) {
            if (firstBiggerThanMe[i] != 0 && firstBiggerThanMe[i] - i <= k) {
                List<Integer> points = arrivePoints.computeIfAbsent(firstBiggerThanMe[i], k1 -> new LinkedList<>());
                points.add(i);
            }
        }
    }
}
