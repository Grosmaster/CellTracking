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

import java.util.Arrays;

@Service
//@Primary
//0.457899
public class IncludeCell implements TrackingAlgorithm {

    @Override
    public Cell[] run(Cell[] left, Cell[] right, int id) {
        for(Cell cellR : right) {
            Arrays.sort(left, new SortedByArea(cellR));
            if(isOldCell(left[0], cellR)){
                cellR.setValue(left[0].getValue());
            }
            else {
                cellR.setValue(id);
                id++;
            }
        }
        return right;
    }

    private boolean isOldCell(Cell cellA, Cell cellB){
        Pixel b = new Pixel(cellB);
        for (Pixel pixel : cellA.getPixelArray()) {
            if(pixel.equals(b)) return true;
        }
        return false; // TRA measure: 0.454241
    }

}
