/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueensolver;

import java.util.Arrays;

/**
 *
 * @author rabi
 */
public class HillClimbingNQueenSolver extends NQueenSolver {

    private void randomize(int positions[]) {
        for (int c = 0; c < positions.length; c++) {
            // Math.random() returns a value in the range [0, 1)
            int d = (int) (Math.random() * positions.length);
            int temp = positions[c];
            positions[c] = positions[d];
            positions[d] = temp;
        }
    }

    @Override
    public int[] getSolution(int n) {
        int positions[] = new int[n];
        int localBest[] = new int[n];
        int globalBest[] = new int[n];

        for (int i = 0; i < n; i++) {
            positions[i] = i;
            globalBest[i] = i;
        }

        randomize(positions);
        System.out.printf("Initial Solution: %s\nConflicts: %d\n", Arrays.toString(positions), countConflicts(positions));

        for (int tries = 0; tries < 100; tries++) {
            System.arraycopy(positions, 0, localBest, 0, n);
            int minConflicts = countConflicts(localBest);
            if (minConflicts == 0)
                break;
            int tempPositions[] = new int[n];

            for (int c = 0; c < n; c++) {
                for (int r = 0; r < n; r++) {
                    System.arraycopy(positions, 0, tempPositions, 0, n);
                    tempPositions[c] = r;
                    int tempConflicts = countConflicts(tempPositions);
                    if (tempConflicts < minConflicts) {
                        minConflicts = tempConflicts;
                        System.arraycopy(tempPositions, 0, localBest, 0, n);
                    }
                }
            }
            if (countConflicts(localBest) < countConflicts(positions)) {
                System.arraycopy(localBest, 0, positions, 0, n);
                if (countConflicts(globalBest) > countConflicts(localBest)) {
                    System.arraycopy(localBest, 0, globalBest, 0, n);
                }
            } else {
                randomize(positions);
            }
        }
        System.out.printf("Updated Solution: %s\nConflicts: %d\n", Arrays.toString(globalBest), countConflicts(globalBest));
        for (int i = 0; i < n; i++) {
            globalBest[i]++;
        }
        return globalBest;
    }
}
