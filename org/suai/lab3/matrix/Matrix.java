package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.BadMatrixSizesException;
import org.suai.lab3.exceptions.MatrixException;

public interface Matrix {
    int getRows();

    int getCols();

    void checkBounds(int r, int c) throws BadMatrixSizesException;

    int getElement(int row, int column);

    void setElement(int row, int column, int value) throws BadMatrixSizesException;

    Matrix sum(Matrix other) throws MatrixException;

    Matrix product(Matrix other) throws MatrixException;
}
