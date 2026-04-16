package org.suai.lab3;

import org.suai.lab3.matrix.*;
import org.suai.lab3.sort.Sort;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        UsualMatrix m1 = MatrixGenerator.generateUsualMatrix(1000, 1000);
        UsualMatrix m2 = MatrixGenerator.generateUsualMatrix(1000, 1000);

        long start = System.nanoTime();
        Matrix singleThread = m1.product(m2);
        long end = System.nanoTime();
        System.out.println("Time single thread matrix prod: " + (end - start) / 1_000_000.0 + " ms");

        int threadCnt = 2;
        ParallelMatrixProduct producter = new ParallelMatrixProduct(threadCnt);

        start = System.nanoTime();
        Matrix multiThread = producter.product(m1, m2);
        end = System.nanoTime();
        System.out.println("Time " + threadCnt + " threads matrix prod: " + (end - start) / 1_000_000.0 + " ms");
        System.out.println();

        Random random = new Random();

        Integer[] data = new Integer[1000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(1000);
        }

        start = System.nanoTime();
        Sort.quicksort(data, 1);
        end = System.nanoTime();
        System.out.println("Time 1 thread sort: " + (end - start) / 1_000_000.0 + " ms");

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(1000);
        }

        int threadCnt = 4;
        start = System.nanoTime();
        Sort.quicksort(data, threadCnt);
        end = System.nanoTime();
        System.out.println("Time " + threadCnt2 + " threads sort: " + (end - start) / 1_000_000.0 + " ms");
    }
}
