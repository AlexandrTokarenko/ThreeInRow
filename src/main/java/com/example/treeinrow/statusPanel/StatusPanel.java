package com.example.treeinrow.statusPanel;

public class StatusPanel {

    private static int numberOfMoves;
    private int numberOfPoints;

    public StatusPanel() {
        this.numberOfPoints = 0;
    }

    public static void setNumberOfMoves(int n) {

        numberOfMoves = n;
    }

    public int getMoves() {
        return numberOfMoves;
    }

    public void changeMove() {

        if (numberOfMoves != -1) {
            numberOfMoves--;
        }
    }

    public void addScore(int n) {

        numberOfPoints += n;
    }

    public int getScore() {

        return numberOfPoints;
    }
}
