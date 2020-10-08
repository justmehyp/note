package com.hyp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long begin = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> result = forkJoinPool.submit(new CountTask(1, 6));
        System.out.println(result.get());

        System.out.println("elapsed: " + (System.currentTimeMillis() - begin));
    }

    static class CountTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 2;

        private final long start;
        private final long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
//                System.out.println("compute, start: " + start + ", end:" + end);

                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
//                System.out.println("fork-join, start: " + start + ", end:" + end);

                long mid = (end + start) >> 1;
                CountTask left = new CountTask(start, mid);
                CountTask right = new CountTask(mid + 1, end);
                left.fork();
                right.fork();
                Long leftResult = left.join();
                Long rightResult = right.join();
                return leftResult + rightResult;
            }
        }
    }
}
