import org.junit.Test;

public class ZhengSanJiaoTest {
    @Test
    public void test1() {
        int[] arr = {2,2,4,5};
        System.out.println(new ZhengSanJiao().makeEquilateralTriangle(arr));
    }

    @Test
    public void test2() {
        int[] arr = {2,2,3,5};
        System.out.println(new ZhengSanJiao().makeEquilateralTriangle(arr));
    }

    @Test
    public void test3() {
        int[] arr = {3,3,3,3};
        System.out.println(new ZhengSanJiao().makeEquilateralTriangle(arr));
    }

    @Test
    public void test4() {
        int[] arr = {2,4,5};
        System.out.println(new ZhengSanJiao().makeEquilateralTriangle(arr));
    }

    @Test
    public void test5() {
        int[] arr = {2,3,7,5};
        System.out.println(new ZhengSanJiao().makeEquilateralTriangle(arr));
    }

    @Test
    public void test6() {
        int[] arr = {1,3,4,5,7,10};
        System.out.println(new ZhengSanJiao().makeEquilateralTriangle(arr));
    }
}
