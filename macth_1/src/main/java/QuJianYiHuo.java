import java.util.Arrays;
import java.util.List;

public class QuJianYiHuo {

    /**
     * @param num: array of num
     * @param ask: Interval pairs
     * @return: return the sum of xor
     */
    public int Intervalxor(int[] num, List<List<Integer>> ask) {
        // write your code here
        Arrays.sort(num);
        int[] sums = new int[ask.size()];
        for (int i = 0; i < ask.size(); i++) {
            List<Integer> item = ask.get(i);
            int left = num[item.get(1) - 1];
            int right = num[item.get(2) - 1];
            sums[i] = left + right;
        }

        int result = 0;
        for (int i = 0; i < sums.length - 1; i++) {
            result += sums[i] ^ sums[i+1];
        }
        return result;
    }
}
