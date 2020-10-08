import org.junit.Assert;
import org.junit.Test;

public class PerfectStringTest {

    @Test
    public void test1() {
        String s = "10101";
        int k = 2;
        Assert.assertEquals(2, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test2() {
        String s = "00000";
        int k = 3;
        Assert.assertEquals(2, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test3() {
        String s = "1111111111111100";
        int k = 3;
        Assert.assertEquals(1, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test4() {
        String s = "11111110010";
        int k = 3;
        Assert.assertEquals(2, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test5() {
        String s = "1111111001001011111111";
        int k = 3;
        Assert.assertEquals(3, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test6() {
        String s = "1111";
        int k = 3;
        Assert.assertEquals(0, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test7() {
        String s = "000";
        int k = 2;
        Assert.assertEquals(2, new PerfectString().perfectString(s, k));
    }

    @Test
    public void test8() {
        String s = "000";
        int k = 1;
        Assert.assertEquals(3, new PerfectString().perfectString(s, k));
    }
}
