import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JinZiTaTest {
    @Test
    public void test1(){
        final int pyramid = new JinZiTa().pyramid(3, Arrays.asList(1, 2, 3));
        Assert.assertEquals(9, pyramid);
    }

    @Test
    public void test2(){
        final int pyramid = new JinZiTa().pyramid(3, Arrays.asList(1));
        Assert.assertEquals(6, pyramid);
    }

    @Test
    public void test3(){
        final int pyramid = new JinZiTa().pyramid(3, Arrays.asList(2));
        Assert.assertEquals(2, pyramid);
    }

    @Test
    public void test4(){
        final int pyramid = new JinZiTa().pyramid(3, Arrays.asList(3));
        Assert.assertEquals(1, pyramid);
    }

    @Test
    public void test5(){
        final int pyramid = new JinZiTa().pyramid(4, Arrays.asList(1));
        Assert.assertEquals(22, pyramid);
    }

    @Test
    public void test6(){
        final int pyramid = new JinZiTa().pyramid(2, Arrays.asList(1, 2));
        Assert.assertEquals(3, pyramid);
    }

    @Test
    public void test7(){
        final int pyramid = new JinZiTa().pyramid(5, Arrays.asList(1));
        Assert.assertEquals(90, pyramid);
    }

    @Test
    public void test8(){
        final int pyramid = new JinZiTa().pyramid(6, Arrays.asList(1));
        Assert.assertEquals(394, pyramid);
    }

    @Test
    public void test9(){
        final int pyramid = new JinZiTa().pyramid(7, Arrays.asList(1));
        Assert.assertEquals(1806, pyramid);
    }

    @Test
    public void test10(){
        final int pyramid = new JinZiTa().pyramid(8, Arrays.asList(1));
        Assert.assertEquals(8558, pyramid);
    }

    @Test
    public void test11(){
        final int pyramid = new JinZiTa().pyramid(13, Arrays.asList(1));
        Assert.assertEquals(13648869*2, pyramid);
    }

    @Test
    public void test12(){
        final int pyramid = new JinZiTa().pyramid(13, Arrays.asList(2));
        Assert.assertEquals(2646723*2, pyramid);
    }

    @Test
    public void test13(){
        final int pyramid = new JinZiTa().pyramid(12, Arrays.asList(1));
        Assert.assertEquals(2646723*2, pyramid);
    }

    @Test
    public void test14(){
        final int pyramid = new JinZiTa().pyramid(13, Arrays.asList(1, 2));
        Assert.assertEquals((13648869*2 + 2646723*2L) % 1000000007, pyramid);
    }
}
