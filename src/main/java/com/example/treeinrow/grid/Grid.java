package com.example.treeinrow.grid;

import com.example.treeinrow.statusPanel.StatusPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Grid {
    
    private int[][] grid = new int[7][7];

    public Grid() {

        setNumberOnGrid();
    }
    private void setNumberOnGrid() {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = -1;
            }
        }

        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                do {
                    Random random = new Random();
                    grid[i][j] = random.nextInt(7) % 7;
                } while (isStreak(i, j));
            }
        }
    }

    public boolean isStreak(int row, int col) {

        return rowStreak(row,col) > 2 || colStreak(row,col) > 2;
    }

    private int rowStreak(int row, int col) {

        int current = grid[row][col];
        int streak = 1;
        int tmp = col;
        while (checkGem(current,row,tmp-1)) {
            tmp--;
            streak++;
        }
        tmp=col;
        while (checkGem(current,row,tmp+1)) {
            tmp++;
            streak++;

        }
        return streak;
    }

    private boolean checkGem(int current, int row, int col) {

        if (row < 0 || row > 6 || col < 0 || col > 6) {
            return false;
        }
        return current==grid[row][col];
    }

    private int colStreak(int row, int col) {

        int current = grid[row][col];
        int streak = 1;
        int tmp = row;
        while (checkGem(current,tmp-1,col)) {
            tmp--;
            streak++;
        }
        tmp=row;
        while (checkGem(current,tmp+1,col)) {
            tmp++;
            streak++;
        }
        return streak;
    }

    public void removeElements(int row, int col, StatusPanel sp) {

        List<Integer> elementToRemove = new ArrayList<>();
        List<Integer> elementToRemove2 = new ArrayList<>();
        int current = grid[row][col];
        elementToRemove.add(row);
        elementToRemove2.add(col);
        int tmp;

        if (rowStreak(row,col) > 2) {
            tmp=col;
            while (checkGem(current,row,tmp-1)) {
                tmp--;
                elementToRemove.add(row);
                elementToRemove2.add(tmp);
            }
            tmp=col;
            while (checkGem(current,row,tmp+1)) {
                tmp++;
                elementToRemove.add(row);
                elementToRemove2.add(tmp);
            }
        }
        if (colStreak(row,col) > 2) {
            tmp=row;
            while (checkGem(current,tmp-1,col)) {
                tmp--;
                elementToRemove.add(tmp);
                elementToRemove2.add(col);
            }
            tmp=row;
            while (checkGem(current,tmp+1,col)) {
                tmp++;
                elementToRemove.add(tmp);
                elementToRemove2.add(col);
            }
        }

        for (int i = 0; i < elementToRemove.size(); ++i) {
            grid[elementToRemove.get(i)][elementToRemove2.get(i)] = -1;
        }
        sp.addScore(elementToRemove.size()*10);

        adjustGems(sp);
    }

    private void adjustGems(StatusPanel statusPanel) {

        for (int i = 0; i < 7; ++i) {
            int nx = 0;
            int ny = 0;
            for (int j = 6; j >= 0; --j) {
                if (grid[i][j] == -1) {
                    nx += 1;
                    if (nx == 1) ny = j;
                }
                if (grid[i][j] > -1 && nx > 0) {
                    grid[i][ny] = grid[i][j];
                    if (isStreak(i,ny)) {
                        removeElements(i,ny,statusPanel);
                    }
                    ny -= 1;
                    grid[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                Random random = new Random();
                if (grid[i][j] == -1) {
                    grid[i][j] = random.nextInt(7) % 7;
                    if (isStreak(i,j)) {
                        removeElements(i,j,statusPanel);
                    }
                }
            }
        }
    }

    public int[][] getGrid() {

        return grid;
    }
}
