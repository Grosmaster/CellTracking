package com.ssau.tracking.algorithms.impl;

import com.ssau.tracking.algorithms.TrackingAlgorithm;
import com.ssau.tracking.controller.TrackingController;
import com.ssau.tracking.entity.Cell;
import com.ssau.tracking.entity.Pixel;
import com.ssau.tracking.utils.SortedByLenght;
import com.ssau.tracking.utils.TrakingUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;

//0.495097
@Service
//@Primary
public class ThresholdValue implements TrackingAlgorithm {

    @Override
    public Cell[] run(Cell[] leftC, Cell[] rightC, int id) {
        SuperCell[] left = createSuperCell(leftC);
        SuperCell[] right = createSuperCell(rightC);

        for(SuperCell cellR : right) {
            Arrays.sort(left, new SortedByLenght(cellR));
            for (SuperCell cellL : left) {
                if(cellL.link){
                    if(!cellL.parent && isOldCell(cellL, cellR)){
                        cellL.link = false;
                        cellR.link = false;
                        cellR.setValue(cellL.getValue());
                        break;

                    }
                    else if (isChild(cellL, cellR)) {
                        cellL.parent = true;
                        cellR.link = false;
                        cellR.setParent(cellL.getValue());
                        cellR.setValue(id);
                        id++;
                        break;
                    }
                }
            }
            if(cellR.link){
                cellR.setValue(id);
                id++;
            }
        }

        return right;
    }


    private boolean isOldCell(Cell cellA, Cell cellB){
        double res = 1;
        if (cellA.getPixelArray().length < cellB.getPixelArray().length)
            res = cellA.getPixelArray().length / cellB.getPixelArray().length;
        else
            res = cellB.getPixelArray().length / cellA.getPixelArray().length;
        return TrakingUtils.lenght(cellA, cellB) < 40 && res > 0.8; // TRA measure: 0.484241
    }

    private SuperCell[] createSuperCell(Cell[] cells){
        SuperCell[] superCells = new SuperCell[cells.length];
        for (int i = 0; i < cells.length; i++) {
            superCells[i] = new SuperCell(cells[i]);
        }
        return superCells;
    }


    private boolean isChild(Cell cellA, Cell cellB){
        return isChild(cellA.getPixelArray(), new Pixel(cellB));
    }

    private boolean isChild(Pixel[] arr, Pixel b){
        for (Pixel pixel : arr) {
            if(pixel.equals(b)) return true;
        }
        return false;
    }

    class SuperCell extends Cell implements Comparable {
        private boolean link;
        private boolean parent;

        public SuperCell(Cell cell) {
            this.link = true;
            this.parent = false;
            this.setPixelArray(cell.getPixelArray());
            this.setValue(cell.getValue());
            this.setParent(cell.getParent());
            this.setCadr(cell.getCadr());
            this.setStart(cell.getStart());
            this.setEnd(cell.getEnd());
            this.setX(cell.getX());
            this.setY(cell.getY());
        }

        @Override
        public int compareTo(Object o) {
            Cell cell = (Cell) o;
            return Integer.compare(this.getPixelArray().length, cell.getPixelArray().length);
        }
    }

}
