package org.suai.lab3.matrix;

import org.suai.lab3.exceptions.BadMatrixSizesException;
import org.suai.lab3.exceptions.MatrixException;

import java.util.*;

public class SparseMatrix implements Matrix {
    protected int rows;
    protected int cols;
    private final LinkedList<MatrixElement> data = new LinkedList<>();

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public SparseMatrix(final Matrix other) {
        if (other.getRows() != other.getCols()) throw new MatrixException("rows and cols must be the same");

        rows = other.getRows();
        cols = other.getCols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setElement(i, j, other.getElement(i, j));
            }
        }
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
    public Matrix sum(final Matrix other) throws MatrixException {
        if (getRows() != other.getRows() || getCols() != other.getCols()) {
            throw new MatrixException("Invalid matrix to sum: matrix's rows and cols must be the same\n");
        }
        Matrix result;

        if (other instanceof SparseMatrix sm) {
            result = new SparseMatrix(getRows(), getCols());

            Map<MatrixElement.Position, Integer> map = new HashMap<>();
            for (var el : this.data) map.put(el.getPos(), el.getValue());
            for (var el : sm.data) map.merge(el.getPos(), el.getValue(), Integer::sum);

            map.forEach((pos, val) -> {
                if (val != 0) result.setElement(pos.getX(), pos.getY(), val);
            });
            return result;
        }
        else {
            result = new UsualMatrix(getRows(), getCols());

            for (int i = 0; i < other.getRows(); i++) {
                for (int j = 0; j < other.getCols(); j++) {
                    result.setElement(i, j, getElement(i,j) + other.getElement(i, j));
                }
            }
        }
        return result;
    }

    @Override
    public Matrix product(final Matrix other) throws MatrixException {
        if (getCols() != other.getRows()) {
            throw new MatrixException(
                    "Incompatible matrix sizes: left matrix columns (" + getCols() +
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
    public SparseMatrix copy() {
        return new SparseMatrix(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof SparseMatrix other)) return false;

        if (getRows() != other.getRows() || getCols() != other.getCols() || data.size() != other.data.size()) {
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

        sb.append(getClass().getSimpleName()).append(System.lineSeparator());

        for (int i = 0; i < rows; i++) {
            sb.append("[ ");
            for (int j = 0; j < cols; j++) {
                sb.append(getElement(i, j));
                if (j < cols - 1) sb.append(" ");
            }
            sb.append(" ]").append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static class MatrixElement {
        private Position pos;
        private int value;

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
            private int x;
            private int y;

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
            public String toString() {
                return "(" + x + ", " + y + ')';
            }

            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Position position)) return false;
                return x == position.x && y == position.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(getX(), getY());
            }
        }
    }
}
