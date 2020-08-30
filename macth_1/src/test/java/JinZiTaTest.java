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
}
