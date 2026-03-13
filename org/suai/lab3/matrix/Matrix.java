package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.*;
import java.util.Arrays;


public class Matrix {
    protected final int rows;
    protected final int cols;
    protected final int[][] data;
    
    public Matrix(int r, int c) throws MatrixException {
        if (r <= 0 || c <= 0) {
            throw new MatrixException("Index out of bounds: rows and cols must be > 0!\n");
        }

        rows = r;
        cols = c;
        data = new int[getRows()][getCols()];
    }

    public void fillMatrix(int val) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                setElement(i, j, val);
            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    

    protected void checkBounds(int r, int c) throws BadMatrixSizesException {
        if (r < 0 || c < 0) {
            throw new BadMatrixSizesException("Index out of bounds: row and col must be > 0!\n");
        }

        else if (r >= getRows() || c >= getCols()) {
            throw new BadMatrixSizesException("Index out of bounds: position [" + r + "][" + c + "]" +
                                      "does not exist in " + getRows() + "x" + getCols() +" matrix.");
        }
    }


    public int getElement(int row, int column) {
        checkBounds(row, column);
        return data[row][column];
    }   
    
    
    public void setElement(int row, int column, int value) throws MatrixException {
        checkBounds(row, column);
        data[row][column] = value;
    }


    public Matrix sum(Matrix other) throws MatrixException {
        if (getRows() != other.getRows() || getCols() != other.getCols()) {
            throw new MatrixException("Invalid matrix to sum: matrix's rows and cols must be the same\n");
        }

        Matrix newM = new Matrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                int sum = getElement(i, j) + other.getElement(i, j);
                newM.setElement(i, j, sum);
            }
        }

        return newM;
    }


    public Matrix product(Matrix other) throws MatrixException {
        if (getCols() != other.getRows()) {
            throw new MatrixException(
                "Incompatible matrix sizes: left matrix columns (" 
                + getCols() +
                ") must match right matrix rows (" + other.getRows() + ")."
            );
        }

        Matrix newM = new Matrix(getRows(), other.getCols());
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                int sumVal = 0;
                for (int k = 0; k < getCols(); k++) {
                    sumVal += getElement(i, k) * other.getElement(k, j);
                }
                newM.setElement(i, j, sumVal);
            }
        }
        return newM;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        return getRows() == other.getRows() && getCols() == other.getCols() && Arrays.deepEquals(data, other.data);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

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