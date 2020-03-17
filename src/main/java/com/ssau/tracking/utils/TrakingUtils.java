package com.ssau.tracking.utils;

import com.ssau.tracking.entity.Cell;
import com.ssau.tracking.entity.Pixel;

public abstract class TrakingUtils {

    public static double lenght(int x1, int y1, int x2, int y2){
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }

    public static double lenght(Cell cellA, Cell cellB){
        return Math.sqrt((cellA.getX() - cellB.getX())*(cellA.getX() - cellB.getX()) + (cellA.getY() - cellB.getY())*(cellA.getY() - cellB.getY()));
    }


    public static double coefficientIdentity(Cell cellA, Cell cellB){
        double res = 1;
        if (cellA.getPixelArray().length < cellB.getPixelArray().length)
            res = cellA.getPixelArray().length / cellB.getPixelArray().length;
        else
            res = cellB.getPixelArray().length / cellA.getPixelArray().length;
        return res;
    }
}
