package org.suai.lab3.matrix;

public class MatrixGenerator {

    public static UsualMatrix generateUsualMatrix(int rows, int cols) {
        UsualMatrix m = new UsualMatrix(rows, cols);
        fill(m, rows, cols);
        return m;
    }

    public static SparseMatrix generateSparseMatrix(int rows, int cols) {
        SparseMatrix m = new SparseMatrix(rows, cols);
        fill(m, rows, cols);
        return m;
    }

    public static SquareMatrix generateSquareMatrix(int size) {
        SquareMatrix m = new SquareMatrix(size);
        fill(m, size, size);
        return m;
    }

    private static void fill(Matrix m, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val = (int)(Math.random() * 9 + 1);
                m.setElement(i, j, val);
            }
        }
    }
}
