package com.example.treeinrow.model;

import com.example.treeinrow.grid.Grid;
import com.example.treeinrow.items.MoveResult;
import com.example.treeinrow.items.Pair;
import com.example.treeinrow.statusPanel.StatusPanel;

public class Game {

    private StatusPanel statusPanel;
    private Grid grid;
    private MoveResult moveResult;
    private Pair firstChoicePair = null;

    public MoveResult getMoveResult() {
        return moveResult;
    }

    public void setMoveResult(MoveResult moveResult) {
        this.moveResult = moveResult;
    }

    public Game() {
        this.statusPanel = new StatusPanel();
        this.grid = new Grid();
    }
    public boolean checkTwoPairs(int x1, int y1) {

        return ((x1 == firstChoicePair.getRow() && (y1 == firstChoicePair.getColumn() + 1 || y1 == firstChoicePair.getColumn() - 1)) || (y1 == firstChoicePair.getColumn() && (x1 == firstChoicePair.getRow() + 1 || x1 == firstChoicePair.getRow() - 1)));
    }

    public void swap(int x1, int y1) {

        int n = grid.getGrid()[firstChoicePair.getRow()][firstChoicePair.getColumn()];
        grid.getGrid()[firstChoicePair.getRow()][firstChoicePair.getColumn()] = grid.getGrid()[x1][y1];
        grid.getGrid()[x1][y1] = n;
        if (grid.isStreak(x1, y1) || grid.isStreak(firstChoicePair.getRow(), firstChoicePair.getColumn())) {
            if (grid.isStreak(x1, y1)) {
                    statusPanel.changeMove();
                    grid.removeElements(x1, y1, statusPanel);
                    if (statusPanel.getMoves() == 0) {
                        moveResult = MoveResult.WIN;
                    }
            }
            if (grid.isStreak(firstChoicePair.getRow(), firstChoicePair.getColumn())) {
                    statusPanel.changeMove();
                    grid.removeElements(firstChoicePair.getRow(), firstChoicePair.getColumn(), statusPanel);
                    if (statusPanel.getMoves() == 0) {
                        moveResult = MoveResult.WIN;
                    }
            }
        } else  {
            n = grid.getGrid()[firstChoicePair.getRow()][firstChoicePair.getColumn()];
            grid.getGrid()[firstChoicePair.getRow()][firstChoicePair.getColumn()] = grid.getGrid()[x1][y1];
            grid.getGrid()[x1][y1] = n;
        }
        firstChoicePair = null;
    }

    public boolean checkPair(int x1, int y1) {
        return (x1 >= 0 && x1 <= 6) && (y1 >= 0 && y1 <= 6);
    }

    public Pair getPair() {
        return firstChoicePair;
    }


    public Grid getGrid() {
        return grid;
    }

    public int getMoves() {

        return statusPanel.getMoves();
    }

    public int getPoints() {

        return statusPanel.getScore();
    }


    public void changePair() {

        this.firstChoicePair = null;
    }

    public void setPair(int x1, int y1) {
       this.firstChoicePair = new Pair(x1,y1);
    }
}
