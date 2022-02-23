package com.epam.rd.autotasks.figures;

import static java.lang.Math.abs;

class Circle extends Figure {

    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;

        if (center == null || radius <= 0){
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        boolean theSame = false;
        double delta = 0.0001;

        if (figure instanceof Circle){
            Point otherCenter = ((Circle)figure).center;
            double otherRadius = ((Circle)figure).radius;
            if (abs(center.getX() - otherCenter.getX()) - delta <= 0 &&
                    abs(center.getY() - otherCenter.getY()) - delta <= 0 &&
                    abs(radius - otherRadius) - delta <= 0){
                theSame = true;
            }
        }

        return theSame;
    }
}
