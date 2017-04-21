/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueensolver;

import java.util.ArrayList;

/**
 *
 * @author rabi
 */
public abstract class NQueenSolver {
    public static int countConflicts(int positions[]) {
        int conflicts = 0;
        for (int currentColumn = 0; currentColumn < positions.length; currentColumn++) {
            for (int previousColumn = 0; previousColumn < currentColumn; previousColumn++) {
                int dx = currentColumn - previousColumn;
                int dy = Math.abs(positions[currentColumn] - positions[previousColumn]);
                if (dy == 0 || dy == dx) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }
    
    public abstract int[] getSolution(int n);
}

