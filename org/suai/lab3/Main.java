package org.suai.lab3;

import org.suai.lab3.exceptions.*;
import org.suai.lab3.matrix.*;

public class Main {
    public static void main(String[] args) {
        Matrix simpleMatrixA = new Matrix(3, 3);
        simpleMatrixA.setElement(0, 1, 6);
        SquareMatrix squareMatrixB = new SquareMatrix(3);

        System.out.println("Matrix A: \n" + simpleMatrixA);
        System.out.println("Matrix B: \n" + squareMatrixB);
        System.out.println("Matrix A + B: \n" + simpleMatrixA.sum(squareMatrixB));

        MirrorMatrixHor mirrorMatrixC = new MirrorMatrixHor(5, 3);
        mirrorMatrixC.fillMatrix(1);
        mirrorMatrixC.setElement(1, 2, 2);
        mirrorMatrixC.setElement(0, 0, 4);
        System.out.println("Matrix C: \n" + mirrorMatrixC);


        System.out.println("Matrix C * A: \n" + mirrorMatrixC.product(simpleMatrixA));

        System.out.println("C equals A?: " + mirrorMatrixC.equals(simpleMatrixA) + '\n');

        try {
            System.out.println("Trying to get C[0][-1]");
            mirrorMatrixC.getElement(0, -1);
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }
    }

}
