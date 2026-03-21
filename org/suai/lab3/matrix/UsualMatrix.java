package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.*;
import java.util.Arrays;


public class UsualMatrix implements Matrix {
    protected int rows;
    protected int cols;
    protected final int[][] data;
    
    public UsualMatrix(int r, int c) throws MatrixException {
        if (r <= 0 || c <= 0) {
            throw new MatrixException("Index out of bounds: rows and cols must be > 0!\n");
        }

        rows = r;
        cols = c;
        data = new int[rows][cols];
    }

    public UsualMatrix(final Matrix other) {
        rows = other.getRows();
        cols = other.getCols();
        data = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = other.getElement(i, j);
            }
        }
    }

    @Override
    public int getRows() { return rows; }
    @Override
    public int getCols() { return cols; }


    @Override
    public int getElement(int row, int column) {
        checkBounds(row, column);
        return data[row][column];
    }


    @Override
    public void setElement(int row, int column, int value) throws MatrixException {
        checkBounds(row, column);
        data[row][column] = value;
    }

    @Override
    public UsualMatrix copy() {
        return new UsualMatrix(this);
    }


    @Override
    public void checkBounds(int r, int c) throws BadMatrixSizesException {
        if (r < 0 || c < 0) {
            throw new BadMatrixSizesException("Index out of bounds: row and col must be positive!\n");
        }

        else if (r >= getRows() || c >= getCols()) {
            throw new BadMatrixSizesException("Index out of bounds: position [" + r + "][" + c + "]" +
                                      "does not exist in " + getRows() + "x" + getCols() +" matrix.");
        }
    }

    public void fillMatrix(int val) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                setElement(i, j, val);
            }
        }
    }

    @Override
    public Matrix sum(final Matrix other) throws MatrixException {
        if (getRows() != other.getRows() || getCols() != other.getCols()) {
            throw new MatrixException("Invalid matrix to sum: matrix's rows and cols must be the same\n");
        }

        Matrix result = new UsualMatrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                int sum = getElement(i, j) + other.getElement(i, j);
                result.setElement(i, j, sum);
            }
        }

        return result;
    }


    @Override
    public Matrix product(final Matrix other) throws MatrixException {
        if (getCols() != other.getRows()) {
            throw new MatrixException(
                "Incompatible matrix sizes: left matrix columns (" 
                + getCols() +
                ") must match right matrix rows (" + other.getRows() + ")."
            );
        }

        Matrix result = new UsualMatrix(getRows(), other.getCols());
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                int sumVal = 0;
                for (int k = 0; k < getCols(); k++) {
                    sumVal += getElement(i, k) * other.getElement(k, j);
                }
                result.setElement(i, j, sumVal);
            }
        }
        return result;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof UsualMatrix other)) return false;

        return getRows() == other.getRows() && getCols() == other.getCols() && Arrays.deepEquals(data, other.data);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getSimpleName()).append(System.lineSeparator());

        for (int i = 0; i < getRows(); i++) {
            sb.append("[ ");
            for (int j = 0; j < getCols(); j++) {
                sb.append(getElement(i, j)).append(" ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}