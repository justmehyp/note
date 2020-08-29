import java.util.HashMap;
import java.util.Map;

/**
 * 02 正三角形拼接
 *
 * 描述
 * 给出  根木棍，每次切割可以将  根木棍切成  段。 请计算出最少切割几次，可以从所有木棍中选出  根，组成一个 正三角形 。
 *
 * 一开始的木棍根数为 ，。 所有木棍的长度为一个整型数组 ，。 切割必须要将木棍分成  根整数长度的木棍，而且总长度要和原木棍相等。
 *
 * 说明
 * 可以从长为  的木棍中，切出  根长为  的木棍，那么木棍的长度应该为 ，可以拼出边长为  的正三角形。
 *
 * 示例
 * 输入：
 * [2,3,7,5]
 * 输出：
 * 2
 */
public class ZhengSanJiao {
    /**
     * @param lengths: the lengths of sticks at the beginning.
     * @return: return the minimum number of cuts.
     */
    public int makeEquilateralTriangle(int[] lengths) {
        // write your code here.
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int length : lengths) {
            map.merge(length, 1, Integer::sum);
            max = Math.max(max, length);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 3) {
                return 0;
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(2) && !entry.getKey().equals(max)) {
                return 1;
            }
            if (map.get(entry.getKey() * 2) != null) {
                return 1;
            }
        }

        return 2;
    }
}
