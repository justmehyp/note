import java.util.*;

/**
 * https://blog.csdn.net/PleasantlY1/article/details/84074637 D和这个应该是一样的
 *
 * 大施罗德数： Fn = Fn-1 + ∑(0<=k<=n-1) Fk * Fk-1-k  前4项为 1 2 6 22
 * 由于这个公式只能用递归，性能不行
 *
 * 卡特兰数：前几项为 1 1 3 11，除了第1项，其他项乘以2可得到大施罗德数
 *
 * (n+1)Fn = (6n-3)Fn-1 - (n-2)Fn-2
 *
 * 题目要求结果取模k = 1e9+7,是一个质数1000000007
 * (n+1)Fn%k = (6n-3)Fn-1%k - (n-2)Fn-2%k
 *
 * Fn%k = ( ((6n-3)Fn-1%k - (n-2)Fn-2%k) * 逆元(n+1) )%k
 *
 * a关于%k的逆元为a^(k-2)%k
 */
public class JinZiTa {
    private static final int MOD = 1000000007;

    /**
     * @param n: The number of pyramid levels n
     * @param k: Possible coordinates k
     * @return Find the sum of the number of plans
     */
    public int pyramid(int n, List<Integer> k) {
        // write your code here

        int[] c = new int[n+1];
        c[1] = 1;
        for (int i = 2; i <= n; i++) {
            c[i] = (int) (((6 * i - 3L) * c[i - 1] % MOD - (i - 2L) * c[i - 2] % MOD + MOD) % MOD * inverse(i + 1) % MOD);
        }

        int[] s = new int[n+1];
        s[1] = 1;
        for (int i = 2; i <= n; i++) {
            s[i] = (int) (c[i-1] * 2L % MOD);
        }

        long result = 0;
        for (Integer i : k) {
            int si = s[n - i + 1];
            result = (result + si) % MOD;
        }
        return (int) result;
    }

    // a关于%k的逆元为a^(k-2)%k
    private long inverse(long a) {
        long sum = 1;
        for (int y = MOD - 2; y != 0; y /= 2) {
            if ((y & 1) == 1) {
                sum = (sum * a) % MOD;
            }
            a = (a * a) % MOD;
        }

        return sum % MOD;
    }

}
