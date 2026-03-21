package org.suai.lab3.matrix;


import org.suai.lab3.exceptions.MatrixException;

public class SquareMatrix extends UsualMatrix {

    public SquareMatrix(int size) {
        super(size, size);

        for (int i = 0; i < size; i++) {
            data[i][i] = 1;
        }
    }

    public SquareMatrix(final Matrix other) throws MatrixException{
        if (other.getRows() != other.getCols()) throw new MatrixException("rows and cols must be the same");

        super(other.getRows(), other.getCols());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = other.getElement(i, j);
            }
        }
    }

    @Override
    public SquareMatrix copy() {
        return new SquareMatrix(this);
    }
}