package com.ssau.tracking.algorithms;

import com.ssau.tracking.entity.Cell;

public interface TrackingAlgorithm {
    Cell[] run(Cell[] left, Cell[] right, int id);
}
