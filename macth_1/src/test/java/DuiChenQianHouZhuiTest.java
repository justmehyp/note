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
        System.out.println(new DuiChenQianHouZhui().suffixQuery("bacbdab"));
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
}
