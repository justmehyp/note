import org.junit.Test;

public class RunNianTest {

    @Test
    public void test1() {
        int[][] a = {
                {2, 28, 3, 2, 2}, {2, 28, 3, 1, 2}, {12, 31, 1, 1, 1},
                {2, 16, 1, 23, 341},
                {2, 16, 1, 22, 341},
        };

        System.out.println(new RunNian().guessYear(a));
    }
}
