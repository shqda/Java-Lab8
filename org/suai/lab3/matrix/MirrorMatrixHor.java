package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.BadMatrixSizesException;


public class MirrorMatrixHor extends Matrix {

    public MirrorMatrixHor(int rows, int cols)  {
        super((rows + 1) / 2, cols);
    }

    @Override
    public int getRows() {
        if (rows % 2 == 0) return rows * 2;
        return rows * 2 - 1;
    }

    @Override
    public int getElement(int row, int column) throws BadMatrixSizesException {
        checkBounds(row, column);
        return data[mapRow(row)][column];
    }

    @Override
    public void setElement(int row, int column, int value) throws BadMatrixSizesException {
        checkBounds(row, column);
        data[mapRow(row)][column] = value;
        data[row][column] = value;
    }

    public String convertToString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getRows() / 2; i++) {
            sb.append("[ ");
            for (int j = 0; j < getCols(); j++) {
                sb.append(data[i][j]).append(" ");
            }
            sb.append("]\n");
        }
        for (int i = getRows() / 2; i < getRows(); i++) {
            sb.append("[ ");
            for (int j = 0; j < getCols(); j++) {
                sb.append(data[mapRow(i)][j]).append(" ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    private int mapRow(int row) {
        return getRows() - 1 - row;
    }

}
