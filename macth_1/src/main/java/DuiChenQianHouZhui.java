/**
 * 04 对称前后缀
 *
 * https://tianchi.aliyun.com/oj/14491652514320995/73733636160164531
 */
public class DuiChenQianHouZhui {
    /**
     * @param s: a string.
     * @return return the values of all the intervals.
     */
    public long suffixQuery(String s) {
        // write your code here
        long result = 0;

        for (int i = 0; i < s.length(); i++) {
            // 以i为中心
            {
                boolean isHuiWen = true;
                int count = 0;
                for (int l = i, r = i; l >= 0 && r < s.length(); l--, r++) {
                    if (s.charAt(l) == s.charAt(r)) {

                        System.out.println(l + "," + r + ":" + s.substring(l, r+1));

                        count++;
                        if (isHuiWen) {
                            result += count * 2 - 1;
                        }
                        else {
                            result += count;
                        }
                    }
                    else {
                        isHuiWen = false;
                        count = 0;
                    }
                }
            }

            // 以i、i+1的中间空位为中心
            {
                boolean isHuiWen = true;
                int count = 0;
                for (int l = i, r = i + 1; l >= 0 && r < s.length(); l--, r++) {
                    if (s.charAt(l) == s.charAt(r)) {

                        System.out.println(l + "," + r + ":" + s.substring(l, r+1));

                        count++;
                        if (isHuiWen) {
                            result += count * 2;
                        }
                        else {
                            result += count;
                        }
                    }
                    else {
                        isHuiWen = false;
                        count = 0;
                    }
                }
            }
        }

        return result;
    }
}
