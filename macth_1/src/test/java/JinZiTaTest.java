import org.junit.Test;

import java.util.Arrays;

public class JinZiTaTest {
    @Test
    public void test1(){
        System.out.println(new JinZiTa().pyramid(3, Arrays.asList(1, 2, 3)));
    }

    @Test
    public void test2(){
        System.out.println(new JinZiTa().pyramid(3, Arrays.asList(1)));
    }

    @Test
    public void test3(){
        System.out.println(new JinZiTa().pyramid(3, Arrays.asList(2)));
    }

    @Test
    public void test4(){
        System.out.println(new JinZiTa().pyramid(3, Arrays.asList(3)));
    }

    @Test
    public void test5(){
        System.out.println(new JinZiTa().pyramid(4, Arrays.asList(1)));
    }

    @Test
    public void test6(){
        System.out.println(new JinZiTa().pyramid(2, Arrays.asList(1,2)));
        // 336020753
    }
}
