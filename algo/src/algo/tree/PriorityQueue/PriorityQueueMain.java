package algo.tree.PriorityQueue;

/**
 * 优先队列
 *
 * @see java.util.PriorityQueue
 * @see java.util.concurrent.PriorityBlockingQueue
 */
public class PriorityQueueMain {

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();

        System.out.println(priorityQueue.peek()); // []
        System.out.println(priorityQueue.pop()); // []

        priorityQueue.offer(5);
        System.out.println(priorityQueue.peek()); // [5]

        priorityQueue.offer(4);
        System.out.println(priorityQueue.peek()); // [4, 5]

        priorityQueue.offer(6);
        System.out.println(priorityQueue.pop()); // 4 [5, 6]

        priorityQueue.offer(1);
        System.out.println(priorityQueue.pop()); // 1 [5, 6]

        priorityQueue.offer(2);
        System.out.println(priorityQueue.peek()); // [2, 5, 6]

        priorityQueue.offer(3);
        System.out.println(priorityQueue.peek()); // [2, 3, 5, 6]

        System.out.println(priorityQueue.pop()); // 2 [3, 5, 6]

        System.out.println(priorityQueue.pop()); // 3 [5, 6]

        System.out.println(priorityQueue.pop()); // 5 [6]

        System.out.println(priorityQueue.peek()); // [6]

        System.out.println(priorityQueue.pop()); // 6

        System.out.println(priorityQueue.peek()); // []
        System.out.println(priorityQueue.pop()); // []
    }
}

class PriorityQueue {
    private int[] heap = new int[8];
    private int size;

    public void offer(int item) {
        if (size > heap.length) {
            resize();
        }
        heap[size++] = item;
        upAdjust();
    }

    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1 ) / 2;

        int child = heap[childIndex];
        while (childIndex > 0 && heap[parentIndex] > child) {
            heap[childIndex] = heap[parentIndex];

            childIndex = parentIndex;
            parentIndex = (childIndex - 1 ) / 2;
        }
        heap[childIndex] = child;
    }

    private void resize() {
        size *= 2;
        int[] tmp = new int[size];
        System.arraycopy(heap, 0, tmp, 0, heap.length);
        heap = tmp;
    }

    public Integer pop() {
        if (size == 0) {
            return null;
        }
        int result = heap[0];
        heap[0] = heap[--size];
        downAdjust();
        return result;
    }

    private void downAdjust() {
        int parentIndex = 0;
        int childIndex = parentIndex * 2 + 1;

        int parent = heap[parentIndex];
        while (childIndex < size) {
            if (childIndex + 1 < size && heap[childIndex] > heap[childIndex + 1]) {
                childIndex = childIndex + 1;
            }

            if (parent > heap[childIndex]) {
                heap[parentIndex] = heap[childIndex];

                parentIndex = childIndex;
                childIndex = parentIndex * 2 + 1;
            }
            else {
                break;
            }
        }
        heap[parentIndex] = parent;
    }

    public Integer peek() {
        return size == 0 ? null : heap[0];
    }
}