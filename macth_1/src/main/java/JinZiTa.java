import java.util.*;

/**
 * https://blog.csdn.net/PleasantlY1/article/details/84074637 D和这个应该是一样的
 *
 * 大施罗德数： Fn = Fn-1 + ∑(0<=k<=n-1) Fk * Fk-1-k  前4项为 1 2 6 22
 * 由于这个公式只能用递归，性能不行
 *
 * 超级卡特兰数：
 *
 * (n+1)Fn = (6n-3)Fn-1 - (n-2)Fn-2
 *
 * 题目要求结果取模k = 1e9+7,是一个质数1000000007
 * (n+1)Fn%k = (6n-3)Fn-1%k - (n-2)Fn-2%k
 *
 * Fn%k = ( ((6n-3)Fn-1%k - (n-2)Fn-2%k) * 逆元(n+1,k) )%k
 *
 * a关于%k的逆元为a^(k-2)%k
 */
public class JinZiTa {

    /**
     * @param n: The number of pyramid levels n
     * @param k: Possible coordinates k
     * @return Find the sum of the number of plans
     */
    public int pyramid(int n, List<Integer> k) {
        // write your code here

        int min = k.stream().min(Integer::compareTo).get();

        Map<String, Long> buff = new HashMap<>();
        for (int p = n; p >= min; p--) {
            long a = doPyramid(p, p, n, buff);
            buff.put(p + "-" + p, a);
        }

        long result = 0;
        for (Integer i : k) {
            result = (result + buff.get(i + "-" + i)) % 1000000007;
        }

        return (int) result;
    }

    private long doPyramid(int x, int y, int end, Map<String, Long> buff) {
        String key = x + "-" + y;

        Long saved = buff.get(key);
        if (saved != null) {
            return saved;
        }

        if (x >= y) {
            if (x < end) {
                final long a = doPyramid(x + 1, y, end, buff);
                final long b = doPyramid(x, y + 1, end, buff);
                final long c = doPyramid(x + 1, y + 1, end, buff);

                long result = (((a + b) % 1000000007) + c) % 1000000007;
                buff.put(key, result);
                return result;
            }
            else {
                return 1;
            }
        }
        else {
            return 0;
        }
    }


}
