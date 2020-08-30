import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JinZiTa {

    /**
     * @param n: The number of pyramid levels n
     * @param k: Possible coordinates k
     * @return: Find the sum of the number of plans
     */
    public int pyramid(int n, List<Integer> k) {
        // write your code here
        long result = 0;
        Map<String, Long> tmp = new HashMap<>();
        for (Integer start : k) {
            result += doPyramid(start, start, n, tmp);
        }
        return (int) (result % 1000000007);
    }

    private long doPyramid(Integer startX, Integer startY, int end, Map<String, Long> tmp) {
        String key = startX + "-" + startY;

        if (startX == end && startY == end) {
            tmp.put(key, 1L);
            return 1L;
        }

        Long saved = tmp.get(key);
        if (saved != null) {
            return saved;
        }

        if (startX <= end && startY <= end && startY <= startX) {
            final long a = doPyramid(startX + 1, startY, end, tmp);
            final long b = doPyramid(startX, startY + 1, end, tmp);
            final long c = doPyramid(startX + 1, startY + 1, end, tmp);

            long l = a + b + c;
            l = l % 1000000007;
            tmp.put(key, l);
            return l;
        } else {
            return 0;
        }
    }


}
