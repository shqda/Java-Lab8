package org.suai.lab3.matrix;


public class SquareMatrix extends UsualMatrix {

    public SquareMatrix(int size) {
        super(size, size);

        for (int i = 0; i < size; i++) {
            data[i][i] = 1;
        }
    }

    @Override
    public Matrix sum(final Matrix other) {
        return super.sum(other);
    }
}