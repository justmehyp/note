import org.junit.Assert;
import org.junit.Test;

public class DuiChenQianHouZhuiTest {
    @Test
    public void test1() {
        System.out.println(new DuiChenQianHouZhui().suffixQuery("abca"));
    }

    @Test
    public void test2() {
        System.out.println(new DuiChenQianHouZhui().suffixQuery("aaaa"));
    }

    @Test
    public void test3() {
        System.out.println(new DuiChenQianHouZhui().suffixQuery("daac"));
    }

    @Test
    public void test4() {
        long count = new DuiChenQianHouZhui().suffixQuery("bacbdab");
        Assert.assertEquals(12, count);
    }

    @Test
    public void test5() {
        System.out.println(new DuiChenQianHouZhui().suffixQuery("abc"));
    }

    @Test
    public void test6() {
        System.out.println(new DuiChenQianHouZhui().suffixQuery(""));
    }

    @Test
    public void test7() {
        System.out.println(new DuiChenQianHouZhui().suffixQuery("abcdefghijklmnaa"));
    }

    @Test
    public void test8() {
        final long count = new DuiChenQianHouZhui().suffixQuery("abba");
        Assert.assertEquals(10, count);
    }

    @Test
    public void test9() {
        final long count = new DuiChenQianHouZhui().suffixQuery("1234351");
        System.out.println(count);
        Assert.assertEquals(11, count);
    }
}
