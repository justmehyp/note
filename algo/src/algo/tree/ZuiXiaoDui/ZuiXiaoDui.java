package algo.tree.ZuiXiaoDui;

import java.util.Arrays;

public class ZuiXiaoDui {

    public static void main(String[] args) {
        //          1
        //      2      3
        //   4    5  6   7
        // 8  0

        //         0
        //      1     3
        //    2   5 6   7
        //  8  4
        int[] heap1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        upAjust(heap1);
        System.out.println("upAjust: " + Arrays.toString(heap1)); // [0, 1, 3, 2, 5, 6, 7, 8, 4]

        //          8
        //      2      3
        //   4    5  6   7

        //         2
        //     4       3
        //  8    5   6   7
        int[] heap2 = new int[]{8, 2, 3, 4, 5, 6, 7};
        downAjust(heap2);
        System.out.println("downAjust: " + Arrays.toString(heap2)); // [2, 4, 3, 8, 5, 6, 7]

        //       8
        //    5     4
        //  9  7  1  3
        // 2

        //       8
        //    5     4
        //  2  7  1  3
        // 9

        //       8
        //    5     1
        //  2  7  4  3
        // 9

        //       8
        //    2     1
        //  5  7  4  3
        // 9

        //       1
        //    2     3
        //  5  7  4  8
        // 9
        int[] heap3 = new int[]{8, 5, 4, 9, 7, 1, 3, 2};
        buildZuiXiaoDui(heap3);
        System.out.println("buildZuiXiaoDui: " + Arrays.toString(heap3)); // [1, 2, 3, 5, 7, 4, 8, 9]
    }

    // 从最后一个非叶子节点的父节点开始下沉
    private static void buildZuiXiaoDui(int[] heap) {
        for (int i = (heap.length - 2) / 2; i >= 0; i--) {
            downAjust(heap, i);
        }
    }

    private static void downAjust(int[] heap) {
        if (heap == null || heap.length == 0) {
            return;
        }
        downAjust(heap, 0);
    }

    private static void downAjust(int[] heap, int startParentIndex) {
        if (heap == null || heap.length == 0 || startParentIndex < 0 || startParentIndex > heap.length - 1) {
            return;
        }

        int parentIndex = startParentIndex;
        int childIndex = (parentIndex * 2) + 1;
        int first = heap[parentIndex];

        while (childIndex <= heap.length - 1) {
            if (childIndex + 1 <= heap.length - 1 && heap[childIndex + 1] < heap[childIndex]) {
                childIndex++;
            }

            if (first > heap[childIndex]) {
                heap[parentIndex] = heap[childIndex];
                parentIndex = childIndex;
                childIndex = (parentIndex * 2) + 1;
            }
            else {
                break;
            }
        }

        heap[parentIndex] = first;
    }

    private static void upAjust(int[] heap) {
        if (heap == null || heap.length == 0) {
            return;
        }

        int childIndex = heap.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        int last = heap[childIndex];

        while (childIndex > 0 && last < heap[parentIndex]) {
            heap[childIndex] = heap[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }

        heap[childIndex] = last;
    }
}
