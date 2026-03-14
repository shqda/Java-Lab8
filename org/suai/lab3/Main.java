package org.suai.lab3;

import org.suai.lab3.exceptions.*;
import org.suai.lab3.matrix.*;

public class Main {
    public static void main(String[] args) {
        Matrix simpleMatrixA = new Matrix(2, 3);
        simpleMatrixA.setElement(0, 1, 3);
        simpleMatrixA.setElement(1, 1, 5);
        SquareMatrix squareMatrixB = new SquareMatrix(2);

        System.out.println("Matrix A: \n" + simpleMatrixA);
        System.out.println("Matrix B: \n" + squareMatrixB);

        try {
            System.out.println("Matrix A + B:");
            System.out.println(simpleMatrixA.sum(squareMatrixB));
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }

        MirrorMatrixHor mirrorMatrixC = new MirrorMatrixHor(5, 2);
//        mirrorMatrixC.fillMatrix(1);
        mirrorMatrixC.setElement(1, 1, 2);
        mirrorMatrixC.setElement(0, 0, 4);
        System.out.println("Matrix C: \n" + mirrorMatrixC);

        try {
            System.out.println("Matrix C * A:");
            System.out.println(mirrorMatrixC.product(simpleMatrixA));
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("\nC equals A?: " + mirrorMatrixC.equals(simpleMatrixA) + '\n');
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Trying to get A[1][1]: " + simpleMatrixA.getElement(1, 1));
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }
    }
}
