/**
 * 04 对称前后缀
 *
 * https://tianchi.aliyun.com/oj/14491652514320995/73733636160164531
 */
public class DuiChenQianHouZhui {
    /**
     * @param s: a string.
     * @return: return the values of all the intervals.
     */
    public long suffixQuery(String s) {
        // write your code here
        if (s == null) {
            return 0;
        }
        final char[] chars = s.toCharArray();
        final int len = chars.length;
        long result = len;
        for (int i = 2; i <= len; i++) { // 子字符串字符个数
            for (int j = 0; j <= len - i; j++) {
                result += doSuffixQuery(chars, j, i);
            }
        }
        return result;
    }

    private long doSuffixQuery(char[] chars, int start, int count) {
        long result = 0;
        for (int end = start + count - 1; start < end; start++, end--) {
            if (chars[start] != chars[end]) {
                break;
            } else {
                result++;
            }
        }
        return result;
    }
}
