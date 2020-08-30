import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuJianYiHuoTest {

    @Test
    public void test1() {
        System.out.println(5^6);
        System.out.println(5^1);
    }

    @Test
    public void test2() {
        int[] nums = {1,2,3,4,5};
        final List<List<Integer>> objects = new ArrayList<>();
        objects.add(Arrays.asList(1, 2, 3, 4));
        objects.add(Arrays.asList(1,2,4,5));
        System.out.println(new QuJianYiHuo().Intervalxor(nums, objects));
    }

    @Test
    public void test3() {
        int[] nums = {1,2,3,4,5};
        final List<List<Integer>> objects = new ArrayList<>();
        objects.add(Arrays.asList(1, 2, 3, 4));
        objects.add(Arrays.asList(1,2,4,5));
        objects.add(Arrays.asList(1, 2, 3, 4));
        System.out.println(new QuJianYiHuo().Intervalxor(nums, objects));
    }
}
