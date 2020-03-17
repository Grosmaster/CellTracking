package com.ssau.tracking.entity;

public class Pixel {
    int x;
    int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pixel(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
    }

    public Pixel() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        if (x != pixel.x) return false;
        return y == pixel.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "x=" + x +
                ", y=" + y +
                '}' + '\n';
    }
}
