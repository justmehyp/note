import java.util.HashSet;
import java.util.Set;

public class WuZiHuiWen {
    /**
     * @param s: The given string
     * @return: return the number of Five-character palindrome
     */
    public int Fivecharacterpalindrome(String s) {
        // write your code here
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            // 以i为中心
            int count = 0;
            Set<Character> visited = new HashSet<>();
            for (int l = i, r = i; l >= 0 && r < s.length(); l--, r++) {
                if (s.charAt(l) == s.charAt(r) && !visited.contains(s.charAt(l))) {
//                    System.out.println(l + "," + r + ":" + s.substring(l, r+1));
                    visited.add(s.charAt(l));
                    count++;
                    if (count * 2 - 1 == 5) {
                        result++;
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }

        return result;
    }
}
