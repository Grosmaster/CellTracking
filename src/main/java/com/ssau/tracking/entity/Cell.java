package com.ssau.tracking.entity;

import java.util.Arrays;

public class Cell {
    int cadr;
    int x;
    int y;
    int value;
    int start;
    int end;
    int parent;
    Pixel[] pixelArray;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public Pixel[] getPixelArray() {
        return pixelArray;
    }

    public void setPixelArray(Pixel[] pexelArray) {
        this.pixelArray = pexelArray;
    }

    public int getCadr() {
        return cadr;
    }

    public void setCadr(int cadr) {
        this.cadr = cadr;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cadr=" + cadr +
                ", x=" + x +
                ", y=" + y +
                ", value=" + value +
                ", start=" + start +
                ", end=" + end +
                ", parent=" + parent +
                ", pexelArray=" + Arrays.toString(pixelArray) +
                '}';
    }
}
