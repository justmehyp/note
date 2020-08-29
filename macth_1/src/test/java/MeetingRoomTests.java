//import org.junit.Assert;
//import org.junit.Test;
//
//public class MeetingRoomTests {
//
//    @Test
//    public void test1() {
//        int[][] meeting = {{10,20}, {20,30}};
//        int[] value = {5,3};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(8, mostValue);
//    }
//
//    @Test
//    public void test2() {
//        int[][] meeting = {{10,20}, {20,30}};
//        int[] value = {2,4};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(6, mostValue);
//    }
//
//    @Test
//    public void test3() {
//        int[][] meeting = {{10,20}, {19,30}};
//        int[] value = {2,4};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(4, mostValue);
//    }
//
//    @Test
//    public void test4() {
//        int[][] meeting = {{10,20}, {19,30}};
//        int[] value = {5,3};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(5, mostValue);
//    }
//
//    @Test
//    public void test5() {
//        int[][] meeting = {{10,20}, {19,30}, {20, 21}};
//        int[] value = {5,3, 6};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(11, mostValue);
//    }
//
//    @Test
//    public void test6() {
//        int[][] meeting = {{10,20}, {20,30}, {20, 21}};
//        int[] value = {5,3, 6};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(11, mostValue);
//    }
//
//    @Test
//    public void test7() {
//        int[][] meeting = {{10,20}, {20,30}, {30, 40}};
//        int[] value = {5,3, 6};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(14, mostValue);
//    }
//
//    @Test
//    public void test8() {
//        int[][] meeting = {{10,20}, {20,30}, {29, 40}};
//        int[] value = {5,3, 6};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(11, mostValue);
//    }
//
//    @Test
//    public void test9() {
//        int[][] meeting = {{10,21}, {20,30}, {30, 40}};
//        int[] value = {5,7, 6};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(13, mostValue);
//    }
//
//    @Test
//    public void test10() {
//        int[][] meeting = {{10,40},{20,50},{30,45},{40,60}};
//        int[] value = {3,6,2,4};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(7, mostValue);
//    }
//
//    @Test
//    public void test11() {
//        int[][] meeting = {{10,40},{20,50},{30,40},{40,60}};
//        int[] value = {3,6,5,4};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(9, mostValue);
//    }
//
//    // 乱序
//    @Test
//    public void test12() {
//        int[][] meeting = {{10,40},{20,50},{40,60},{30,40}};
//        int[] value = {3,6,7,4};
//        // 排序后： {{10,40},{20,50},{30,40},{40,60}};
//        // 排序后： {3,6,4,7}
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(11, mostValue);
//    }
//
//    @Test
//    public void test13() {
//        int[][] meeting = {{10,40},{20,50},{30,41},{40,60}, {61, 42}};
//        int[] value = {3,6,5,4, 9};
//
//        int mostValue = MeetingRoom.maxValue(meeting, value);
//        Assert.assertEquals(16, mostValue);
//    }
//}
