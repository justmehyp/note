import org.junit.Test;

public class WuZiHuiWenTest {
    @Test
    public void test1() {
        System.out.println(new WuZiHuiWen().Fivecharacterpalindrome("abcba"));
    }

    @Test
    public void test2() {
        System.out.println(new WuZiHuiWen().Fivecharacterpalindrome("abcbabcccb")); // abcba cbabc
    }

    @Test
    public void test3() {
        System.out.println(new WuZiHuiWen().Fivecharacterpalindrome("ababa")); // abcba cbabc
    }
}
