package org.suai.lab3;

import org.suai.lab3.matrix.*;

public class Main {

    public static void main(String[] args) {
        UsualMatrix m1 = MatrixGenerator.generateUsualMatrix(1000, 1000);
        UsualMatrix m2 = MatrixGenerator.generateUsualMatrix(1000, 1000);

        long start = System.nanoTime();
        Matrix singleThread = m1.product(m2);
        long end = System.nanoTime();

        System.out.println("Time single: " + (end - start) / 1_000_000.0 + " ms");

        ParallelMatrixProduct producter = new ParallelMatrixProduct(10);

        start = System.nanoTime();
        Matrix multiThread = producter.product(m1, m2);
        end = System.nanoTime();

        System.out.println("Time multi: " + (end - start) / 1_000_000.0 + " ms");
    }
}
