package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;
import static java.lang.Math.pow;

abstract class Figure{

    public abstract Point centroid();
    public abstract boolean isTheSame(Figure figure);

    public double side(Point m, Point n){
        double first = abs(m.getX() - n.getX());
        double second = abs(m.getY() - n.getY());
        return sqrt(pow(first, 2) + pow(second, 2));
    }
}
