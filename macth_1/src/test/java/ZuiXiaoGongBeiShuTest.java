import org.junit.Assert;
import org.junit.Test;

public class ZuiXiaoGongBeiShuTest {
    @Test
    public void test1() {
        final long greatestcommonmultiple = new ZuiXiaoGongBeiShu().greatestcommonmultiple(3, 6);
        Assert.assertEquals(60, greatestcommonmultiple);
    }
}
