package com.epam.rd.autotasks.figures;

import static java.lang.Math.abs;

class Triangle extends Figure{

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;

        if (!pointNotNull() || !checkIs()){
            System.out.println("wrong argument");
            throw new IllegalArgumentException();
        }
    }

    private boolean checkIs() {
        boolean check = false;
        double ab = side(a, b);
        double bc = side(b, c);
        double ca = side(c, a);

        double epsilon = 0.0001;
        boolean check1 = Math.abs(ab + bc - ca) > epsilon;
        boolean check2 = Math.abs(bc + ca - ab) > epsilon;
        boolean check3 = Math.abs(ab + ca - bc) > epsilon;

        if (ab > 0 && bc > 0 && ca > 0 && check1 && check2 && check3){
            check = true;        // triangle is exist
        }
        return check;
    }

    private boolean pointNotNull(){
        return a != null && b != null && c != null; // true if all points is not null
    }

    @Override
    public Point centroid() {
        double centerx;
        double centery;

        Point middle_ab = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);

        if (c.getX() > middle_ab.getX()){
            centerx = c.getX() - (c.getX() - middle_ab.getX()) * 2 / 3;
        }
        else{
            centerx = c.getX() + abs(c.getX() - middle_ab.getX()) * 2 / 3;
        }

        if (c.getY() > middle_ab.getY()){
            centery = c.getY() - (c.getY() - middle_ab.getY()) * 2 / 3;
        }
        else{
            centery = c.getY() + abs(c.getY() - middle_ab.getY()) * 2 / 3;
        }

        return new Point(centerx, centery);
    }

    @Override
    public boolean isTheSame(Figure figure) {

        boolean theSame = false;
        int countSamePoints = 0;

        if (figure instanceof Triangle){
            Point other_a = ((Triangle) figure).a;
            Point other_b = ((Triangle) figure).b;
            Point other_c = ((Triangle) figure).c;

            Point[] abc = {a, b, c};
            Point[] other = {other_a, other_b, other_c};
            Point[] repeat = new Point[3];
            for (int i = 0; i < abc.length; i++) {
                for (Point point : other) {
                    if (!itsRepeat(point, repeat, countSamePoints)) {
                        if (abc[i].getX() == point.getX() &&
                                abc[i].getY() == point.getY()) {
                            countSamePoints++;
                            repeat[i] = point;
                            break;
                        }
                    }
                }
            }

            if (countSamePoints == abc.length){
                theSame = true;
            }

        }

        return theSame;
    }

    private boolean itsRepeat(Point p, Point[] points, int len){
        boolean check = false;
        for (int i = 0; i < len; i++) {
            if (p.getX() == points[i].getX() && p.getY() == points[i].getY()) {
                check = true;
                break;
            }
        }
        return check;
    }
}


