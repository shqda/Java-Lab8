package org.suai.lab3;

import org.suai.lab3.matrix.*;

//TODO: final

public class Main {
    public static void main(String[] args) {
        System.out.println("Matrix usual1:");
        Matrix usual1 = MatrixGenerator.generateUsualMatrix(1000, 1000);

        System.out.println("Matrix usual2:");
        Matrix usual2 = MatrixGenerator.generateSparseMatrix(1000, 1000);

        System.out.println("Matrix usualSum = usual1 + usual2:");
        Matrix usualSum = usual1.sum(usual2);

        System.out.println("Matrix usualProd = usual1 * usual2:");
        Matrix usualProd = usual1.product(usual2);
        System.out.println();

        System.out.println("Matrix sparse1 = usual1:");
        Matrix sparse1 = usual1.copy();

        System.out.println("Matrix sparse2 = usual2:");
        Matrix sparse2 = usual2.copy();

        System.out.println("Matrix sparseSum = sparse1 + sparse2:");
        Matrix sparseSum = sparse1.sum(sparse2);

        System.out.println("Matrix sparseProd = sparse1 * sparse2:");
        Matrix sparseProd = sparse1.product(sparse2);
        System.out.println();

        System.out.println("Matrix combineSum = usual1 + sparse2:");
        Matrix combineSum = usual1.sum(sparse2);

        System.out.println("Matrix combineProd = usual1 + sparse2:");
        Matrix combineProd = usual1.product(sparse2);
        System.out.println();

        System.out.println("usualSum == sparseSum == combineSum: " + // тк они Matrix то все ок
                (usualSum.equals(sparseSum) == usualSum.equals(combineSum)));

        System.out.println("usualProd == sparseProd == combineProd: " +
            (usualProd.equals(sparseProd) == usualProd.equals(combineProd)));
    }
}
