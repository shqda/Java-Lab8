package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.BadMatrixSizesException;
import org.suai.lab3.exceptions.MatrixException;

import java.util.*;

public class SparseMatrix implements Matrix {
    int rows;
    int cols;
    LinkedList<MatrixElement> data = new LinkedList<>();

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public void checkBounds(int r, int c) throws BadMatrixSizesException {
        if (r < 0 || c < 0 || r >= getRows() || c >= getCols()) {
            throw new BadMatrixSizesException("index out of bound");
        }
    }

    @Override
    public int getElement(int row, int column) throws BadMatrixSizesException {
        checkBounds(row, column);

        int val = 0;
        for (MatrixElement el : data) {
            if (el.getPos().getX() == row && el.getPos().getY() == column) {
                val = el.getValue();
            }
        }
        return val;
    }

    @Override
    public void setElement(int row, int column, int value) throws BadMatrixSizesException {
        checkBounds(row, column);

        for (MatrixElement el : data) {
            if (el.getPos().getX() == row && el.getPos().getY() == column) {
                if (value == 0) {
                    data.remove(el);
                    return;
                }
                el.setValue(value);
                return;
            }
        }
        if (value != 0) data.add(new MatrixElement(row, column, value));
    }

    @Override
    public Matrix sum(Matrix other) throws MatrixException {
        return null;
    }

    @Override
    public Matrix product(Matrix other) throws MatrixException {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SparseMatrix other)) return false;

        if (rows != other.rows || cols != other.cols || data.size() != other.data.size()) {
            return false;
        }

        Map<MatrixElement.Position, Integer> map = new HashMap<>();

        for (var el : data) {
            map.put(el.getPos(), el.getValue());
        }

        for (var el : other.data) {
            Integer val = map.get(el.getPos());
            if (val == null || val != el.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (var el : data) {
            sb.append(el.toString()).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private static class MatrixElement {
        public Position pos;
        public int value;

        MatrixElement(int x, int y, int value) {
            pos = new Position(x, y);
            this.value = value;
        }

        public Position getPos() {
            return pos;
        }

        public void setPos(Position pos) {
            this.pos = pos;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return pos.toString() + " = " + value;
        }

        private static class Position {
            int x;
            int y;

            public Position(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            @Override
            public int hashCode() {
                int prime1 = 73856093;
                int prime2 = 19349663;
                return (x * prime1) ^ (y * prime2);
            }

            @Override
            public String toString() {
                return "(" + x + ", " + y + ')';
            }
        }
    }
}
