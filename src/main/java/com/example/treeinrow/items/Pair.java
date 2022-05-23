package com.example.treeinrow.items;

public class Pair {

    private final int row;
    private final int column;

    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (getRow() != pair.getRow()) return false;
        return getColumn() == pair.getColumn();
    }
}

