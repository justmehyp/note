import org.junit.Assert;
import org.junit.Test;

public class FromStartToEndTest {
    @Test
    public void test1() {
        Assert.assertTrue(new FromStartToEnd().judge("abcd", "abcd"));
    }

    @Test
    public void test2() {
        Assert.assertFalse(new FromStartToEnd().judge("abce", "abcd"));
    }

    @Test
    public void test3() {
        Assert.assertTrue(new FromStartToEnd().judge("dabc", "abcd"));
    }

    @Test
    public void test4() {
        Assert.assertFalse(new FromStartToEnd().judge("aabc", "cbaa"));
    }
}
