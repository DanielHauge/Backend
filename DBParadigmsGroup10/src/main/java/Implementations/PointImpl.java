package Implementations;

import Interfaces.Point;

public class PointImpl implements Point {

    private double x;
    private double y;

    public PointImpl(double x, double y){
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
