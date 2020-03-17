package com.ssau.tracking.algorithms.impl;

import com.ssau.tracking.algorithms.TrackingAlgorithm;
import com.ssau.tracking.controller.TrackingController;
import com.ssau.tracking.entity.Cell;
import com.ssau.tracking.entity.Pixel;
import com.ssau.tracking.utils.SortedByArea;
import com.ssau.tracking.utils.SortedByLenght;
import com.ssau.tracking.utils.TrakingUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
//0.439650
public class AreaCell implements TrackingAlgorithm {

    @Override
    public Cell[] run(Cell[] leftC, Cell[] rightC) {
        SuperCell[] left = createSuperCell(leftC);
        SuperCell[] right = createSuperCell(rightC);

        for(SuperCell cellR : right) {
            Arrays.sort(left, new SortedByArea(cellR));
            for (SuperCell cellL : left) {
                if(cellL.link){
                    cellL.link = false;
                    cellR.link = false;
                    cellR.setValue(cellL.getValue());
                    break;
                }
            }
            if(cellR.link){
                cellR.setValue(TrackingController.id);
                TrackingController.id++;
            }
        }

        return right;
    }

    private SuperCell[] createSuperCell(Cell[] cells){
        SuperCell[] superCells = new SuperCell[cells.length];
        for (int i = 0; i < cells.length; i++) {
            superCells[i] = new SuperCell(cells[i]);
        }
        return superCells;
    }

    class SuperCell extends Cell implements Comparable {
        private boolean link;

        public SuperCell(Cell cell) {
            this.link = true;
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
