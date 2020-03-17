package com.ssau.tracking.utils;

import com.ssau.tracking.entity.Cell;

import java.util.Comparator;

public class SortedByArea implements Comparator {
    private int area;

    public SortedByArea(Cell cell) {
        this.area = cell.getPixelArray().length;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Cell cellA = (Cell) o1;
        Cell cellB = (Cell) o2;
        int areaA = cellA.getPixelArray().length;
        int areaB = cellB.getPixelArray().length;

        return Integer.compare( Math.abs(areaA - area), Math.abs(areaB - area));
    }
}
