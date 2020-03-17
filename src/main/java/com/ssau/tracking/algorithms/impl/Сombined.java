package com.ssau.tracking.algorithms.impl;

import com.ssau.tracking.algorithms.TrackingAlgorithm;
import com.ssau.tracking.controller.TrackingController;
import com.ssau.tracking.entity.Cell;
import com.ssau.tracking.entity.Pixel;
import com.ssau.tracking.utils.SortedByArea;
import com.ssau.tracking.utils.SortedByLenght;
import com.ssau.tracking.utils.TrakingUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
//@Primary
//0.495603
public class Сombined implements TrackingAlgorithm {

    @Override
    public Cell[] run(Cell[] leftC, Cell[] rightC, int id) {
        SuperCell[] left = createSuperCell(leftC);
        SuperCell[] right = createSuperCell(rightC);
        List<SuperCell> temp;

        for(SuperCell cellL : left) {
            temp = new LinkedList<SuperCell>();

            for (SuperCell cellR : right) {
                if(isChild(cellL, cellR)){
                    temp.add(cellR);
                }
            }

            if(temp.size() == 1){
                temp.get(0).setValue(cellL.getValue());
                temp.get(0).link = false;

                cellL.link = false;
            } else if(temp.size() == 2){
                cellL.link = false;
                cellL.parent = true;

                temp.get(0).setParent(cellL.getValue());
                temp.get(0).link = false;
                temp.get(0).setValue(id);
                id++;

                temp.get(1).setParent(cellL.getValue());
                temp.get(1).link = false;
                temp.get(1).setValue(id);
                id++;
            }
        }

        List<SuperCell> beforeLeft = new LinkedList<>();
        for (SuperCell cellL : left) {
            if(cellL.link)
                beforeLeft.add(cellL);
        }


        for (SuperCell cellR : right) {
            if (cellR.link){
                if (beforeLeft.size() != 0){
                    beforeLeft.sort(new SortedByArea(cellR));

                    cellR.setValue(beforeLeft.get(0).getValue());
                    cellR.link = false;
                    beforeLeft.remove(0);
                } else {
                    cellR.setValue(id);
                    id++;
                }
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


    private boolean isChild(Cell cellA, Cell cellB){
        return isChild(cellA.getPixelArray(), new Pixel(cellB));
//        else {
//            double l = TrakingUtils.lenght(cellA, cellB);
//            return l < 40 && TrakingUtils.coefficientIdentity(cellA, cellB) < 0.6;
//        }
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
