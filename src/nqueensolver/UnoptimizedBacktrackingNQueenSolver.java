/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueensolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author rabi
 */
public class UnoptimizedBacktrackingNQueenSolver extends NQueenSolver {
    private boolean solutionFound;
    
    @Override
    public int[] getSolution(int n) {
        int positions[] = new int[n];
        
        solutionFound = false;
        backtrack(positions, 0);
        
        for (int i = 0; i < positions.length; i++)
            positions[i]++;
        
        if (solutionFound)
            return positions;
        else return null;
    }
    
    public void backtrack(int positions[], int currentColumn) {
        if (currentColumn >= positions.length) {
            solutionFound = true;
            return;
        }
        
        if (solutionFound)
            return;
        
        for (int currentRow = 0; currentRow < positions.length && !solutionFound; currentRow++) {
            boolean isConflicting = false;
            positions[currentColumn] = currentRow;
            for (int previousColumn = 0; previousColumn < currentColumn; previousColumn++) {
                int dx = Math.abs(currentColumn - previousColumn);
                int dy = Math.abs(positions[currentColumn] - positions[previousColumn]);
                if (dy == 0 || dx == dy) {
                    isConflicting = true;
                    break;
                }
            }
            if (isConflicting)
                continue;
            backtrack(positions, currentColumn + 1);
        }
    }
}
