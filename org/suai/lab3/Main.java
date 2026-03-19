package org.suai.lab3;

import org.suai.lab3.exceptions.*;
import org.suai.lab3.matrix.*;

public class Main {
    public static void main(String[] args) {
        SparseMatrix sm = new SparseMatrix(10, 10);
        sm.setElement(9, 1, 5);
        sm.setElement(1, 1, 0);
        sm.setElement(1, 1, 0);
        sm.setElement(1, 1, 0);
        sm.setElement(1, 1, 0);
        sm.setElement(9, 1, 0);
        System.out.println(sm);
    }
}
