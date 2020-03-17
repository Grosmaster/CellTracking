package com.ssau.tracking.controller;

import com.ssau.tracking.algorithms.TrackingAlgorithm;
import com.ssau.tracking.entity.Cell;
import com.ssau.tracking.entity.Video;
import com.ssau.tracking.utils.SortedByLenght;
import com.ssau.tracking.utils.TrakingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

@RestController()
public class TrackingController {

    public static int id;

    @Autowired
    TrackingAlgorithm trackingAlgorithm;

    @GetMapping
    public String ping() {
        return "ping";
    }

    @PostMapping
    public Video cellTracking(@RequestBody Video video) {
        System.out.println("ping");
        handlerFirstCadr(video);
        for(int i = 1; i < video.getCells().length; i++){
            video.getCells()[i] = handler(video.getCells()[i-1], video.getCells()[i]);
        }
        cleanPixelArray(video);
        return video;
    }

    private void handlerFirstCadr(Video video) {
        id = 1;
        for(Cell cell : video.getCells()[0]){
            cell.setValue(id);
            id++;
        }
    }

    private Cell[] handler(Cell[] left, Cell[] right){
        return trackingAlgorithm.run(left, right);
    }

    private void cleanPixelArray(Video video){
        for (Cell[] cells : video.getCells()) {
            for (Cell cell : cells) {
                cell.setPixelArray(null);
            }
        }
    }

}
