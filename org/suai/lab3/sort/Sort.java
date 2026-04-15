package org.suai.lab3.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sort {

    public static <T extends Comparable<T>> void quicksort(T[] array, int threadNum) {
        if (array == null || array.length <= 1) return;

        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        List<Future<?>> futures = new ArrayList<>();

        futures.add(pool.submit(() -> quicksort(array, 0, array.length - 1, futures, pool, threadNum > 1)));

        for (int i = 0; i < futures.size(); i++) {
            try {
                futures.get(i).get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        pool.shutdown();
    }

    private static <T extends Comparable<T>> void quicksort(
            T[] array, int left, int right, List<Future<?>> futures, ExecutorService pool, boolean parallel) {
        if (left >= right) return;

        int pivotIndex = partition(array, left, right);
        final int finalPivot = pivotIndex;

        if (parallel) {
            synchronized (futures) {
                futures.add(pool.submit(() -> quicksort(array, left, finalPivot - 1, futures, pool, true)));
                futures.add(pool.submit(() -> quicksort(array, finalPivot + 1, right, futures, pool, true)));
            }
        } else {
            quicksort(array, left, finalPivot - 1, futures, pool, false);
            quicksort(array, finalPivot + 1, right, futures, pool, false);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                T temp = array[i]; array[i] = array[j]; array[j] = temp;
            }
        }
        T temp = array[i + 1]; array[i + 1] = array[right]; array[right] = temp;
        return i + 1;
    }
}