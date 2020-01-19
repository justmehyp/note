package algo.sort;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = generateNums(1000000);

        long start;
        long end;

        System.out.println(Arrays.toString(nums));

        int[] tmp = Arrays.copyOf(nums, nums.length);
        start = System.currentTimeMillis();
        Arrays.sort(tmp);
        end = System.currentTimeMillis();
        print("java", tmp, start, end);

        tmp = Arrays.copyOf(nums, nums.length);
        start = System.currentTimeMillis();
        sort(tmp);
        end = System.currentTimeMillis();
        print("sort", tmp, start, end);

        tmp = Arrays.copyOf(nums, nums.length);
        start = System.currentTimeMillis();
        sortV2(tmp);
        end = System.currentTimeMillis();
        print("sortV2", tmp, start, end);

        tmp = Arrays.copyOf(nums, nums.length);
        start = System.currentTimeMillis();
        sortV3(tmp);
        end = System.currentTimeMillis();
        print("sortV3", tmp, start, end);
    }

    private static void print(String lab, int[] nums, long start, long end) {
//        System.out.println(lab + ", used: " + (end - start) + ": " + Arrays.toString(nums));
        System.out.println(lab + ", used: " + (end - start) );
    }

    private static void sortV3(int[] nums) {
        int sortedBorder = nums.length - 1;
        int lastExchageIndex = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < sortedBorder; j++) {
                if (nums[j] > nums[j + 1]) {
                    sorted = false;
                    lastExchageIndex = j;

                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
            if (sorted) {
                break;
            }
            sortedBorder = lastExchageIndex;
        }
    }

    private static void sortV2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    sorted = false;
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    private static void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    private static int[] generateNums(int count) {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = new Random().nextInt(count);
        }
        return result;
    }
}
