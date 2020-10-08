public class FromStartToEnd {
    /**
     * @param s1: the string 1
     * @param s2: the string 2
     * @return: judge can s1 change to s2
     */
    public boolean judge(String s1, String s2) {
        // write your code here
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() == 1) {
            return false;
        }

        for (int i = 0,count = 1; i < s1.length(); i++) {
            int s2Index = s1.length() - i - 1;
            if (s1.substring(i, i + count).equals(s2.substring(s2Index)) && s1.substring(i + count).equals(s2.substring(0, s2.length() - count))) {
                return true;
            }
        }
        return false;
    }
}
