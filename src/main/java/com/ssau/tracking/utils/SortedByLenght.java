package com.ssau.tracking.utils;

import com.ssau.tracking.entity.Cell;

import java.util.Comparator;

public class SortedByLenght implements Comparator {

    private int x;
    private int y;

    public SortedByLenght(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SortedByLenght(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
    }

    @Override
    public int compare(Object o1, Object o2) {
        Cell cellA = (Cell) o1;
        Cell cellB = (Cell) o2;
        double lenghtA = TrakingUtils.lenght(x, y, cellA.getX(), cellA.getY());
        double lenghtB =  TrakingUtils.lenght(x, y, cellB.getX(), cellB.getY()) ;
        return (int) (lenghtA - lenghtB);
    }


}
