import org.junit.Test;

public class DaLouJianChuanSuoTest {

    @Test
    public void test1() {
        int[] heights = {1,5,4,3,3,5};
        int k = 3;
        int x = 10;
        int y = 6;
        System.out.println(new DaLouJianChuanSuo().shuttleInBuildings(heights, k, x, y));
    }
}
