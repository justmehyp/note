///**
// * 会议室
// *
// * 描述
// * 给定一系列会议的开始时间、结束时间[[s1,e1]，[s2,e2]，…(si < ei)及他们的价值。你在同一时刻只能参加一个会议，请问你可以获得的最大价值是多少？
// *
// * 说明
// * (0,8),(8,10)在8这这一时刻不冲突
// *
// * 示例
// * 样例 1
// *
// *
// * 输入:
// * meeting = [[10,40],[20,50],[30,45],[40,60]]
// * value = [3,6,2,4]
// * 输出: 7
// * 说明: 你可以参加第0个会议和第3个会议，总价值为3 + 4 = 7.
// *
// * 样例 2
// *
// *
// * 输入:
// * meeting = [[10,20],[20,30]]
// * value = [2,4]
// * 输出: 6
// * 说明: 你可以参加第0个会议和第1个会议，总价值为 2 + 4 = 6.
// */
//public class MeetingRoom {
//
//    public static int maxValue(int[][] meeting, int[] value) {
//        if (meeting == null || value == null || meeting.length != value.length) {
//            return 0;
//        }
////        sort(meeting, value);
//        return doMaxValue(meeting, value);
//    }
//
//    private static void sort(int[][] meeting, int[] value) {
//        for (int i = 0; i < meeting.length - 1; i++) {
//            for (int j = i; j < meeting.length - i - 1; j++) {
//                if (meeting[j][0] > meeting[j + 1][0]) {
//                    int[] tmp = meeting[j];
//                    meeting[j] = meeting[j+1];
//                    meeting[j+1] = tmp;
//
//                    int tmpValue = value[j];
//                    value[j] = value[j+1];
//                    value[j+1] = tmpValue;
//                }
//            }
//        }
//    }
//
//    private static int doMaxValue(int[][] meeting, int[] value) {
//        if (value.length == 1) {
//            return value[0];
//        }
//        else {
//            int maxValue = 0;
//            for (int i = 1, len = value.length; i < len; i++) {
//                maxValue = Math.max(maxValue, doMaxValue(meeting, value, 0, i));
//            }
//            return maxValue;
//        }
//    }
//
//    private static int doMaxValue(int[][] meeting, int[] value, int start, int count) {
//        if (count == 1) {
//            int maxValue = 0;
//            for (int i = start; i < value.length; i++) {
//                maxValue = Math.max(maxValue, value[start]);
//            }
//            return maxValue;
//        }
//        else if (count == 2) {
//            int maxValue = 0;
//            for (int i = start; i < value.length - 1; i++) {
//                for (int j = i + 1; j < value.length; j++) {
//                    if (meeting[i][1] <= meeting[j][0]) {
//                        maxValue = Math.max(maxValue, value[i] + value[j]);
//                    }
//                }
//            }
//            return maxValue;
//        }
//        else {
//
//        }
//    }
//
//    private static int next(int[][] meeting, int[] value, int start) {
//        for (int i = start + 1, len = meeting.length; i < len; i++) {
//            if (meeting[start][1] <= meeting[i][0]) {
//                return i;
//            }
//        }
//        return -1;
//    }
//}
