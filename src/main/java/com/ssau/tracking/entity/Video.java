package com.ssau.tracking.entity;

public class Video {
    Cell[][] cells;

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[] getCells(int index) {
        return this.cells[index];
    }

    public void setCells(int index, Cell[] cells) {
        this.cells[index] = cells;
    }
}
