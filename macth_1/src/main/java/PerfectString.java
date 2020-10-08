public class PerfectString {
    /**
     * @param s: string need to be transformed
     * @param k: minimum char can be transformed in one operation
     * @return: minimum times of transforming all char into '1'
     */
    public int perfectString(String s, int k) {
        // Write your code here.
        if(k == 1) {
            return countZero(s);
        }
        char[] chars = s.toCharArray();
        return perfectString(chars, 0, k);
    }

    private int countZero(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

    private int perfectString(char[] chars, int start, int k) {
        for (int i = start; i < chars.length; i++) {
            if (i - start < k) {
                if (chars[i] == '0') {
                    int count = 0;
                    int kn = 0;
                    for (int j = i; j < chars.length; j++) {
                        if(chars[j] == '0') {
                            kn++;
                            if(kn == k) {
                                kn = 0;
                                count++;
                            }
                        } else {
                            return (kn != 0 ? 1 : 0) + count + perfectString(chars, skipOne(chars, j), k);
                        }
                    }
                    return (kn != 0 ? 1 : 0) + count;
                }
            } else {
                return perfectString(chars, skipOne(chars, start), k);
            }
        }
        return 0;
    }

    private int skipOne(char[] chars, int start) {
        int i = start;
        for (; i < chars.length && chars[i] == '1'; i++) {
        }
        return i;
    }
}
