package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.MatrixException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMatrixProduct {

    private final ExecutorService pool;


    public ParallelMatrixProduct(int threads) {
        pool = Executors.newFixedThreadPool(threads);
    }

    public UsualMatrix product(UsualMatrix m1, UsualMatrix m2) {
        validateDimensions(m1, m2);
        UsualMatrix result = new UsualMatrix(m1.getRows(), m2.getCols());

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < m1.getRows(); i++) {
            int row = i;
            futures.add(pool.submit(() -> computeRow(m1, m2, result, row)));
        }

        for (var f : futures) {
            try {
                f.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        close();

        return result;
    }

    private void close() {
        pool.shutdown();
    }

    private static void validateDimensions(UsualMatrix m1, UsualMatrix m2) {
        if (m1.getCols() != m2.getRows()) {
            throw new MatrixException(
                    "Incompatible matrix sizes: left matrix columns ("
                    + m1.getCols() +
                    ") must match right matrix rows (" + m2.getRows() + ")."
            );
        }
    }

    private void computeRow(final UsualMatrix m1, final UsualMatrix m2, final UsualMatrix result, final int row) {
        for (int i = 0; i < m2.getCols(); i++) {

            int sum = 0;

            for (int j = 0; j < m1.getCols(); j++) {
                sum += m1.getElement(row, j) * m2.getElement(j, i);
            }
            result.setElement(row, i, sum);
        }
    }
}
