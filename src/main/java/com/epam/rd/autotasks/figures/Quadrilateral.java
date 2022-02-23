package com.epam.rd.autotasks.figures;

import static java.lang.Math.abs;

class Quadrilateral extends Figure {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        if (!pointNotNull() || !checkIsDegenerative() || !checkIsConvex()){
            throw new IllegalArgumentException();
        }
    }

    private boolean pointNotNull(){
        return a != null && b != null && c != null && d != null; // true if all points is not null
    }

    public boolean checkIsDegenerative() {
        boolean isDegenerative = true;

        double ab = side(a, b);
        double bc = side(b, c);
        double cd = side(c, d);
        double da = side(d, a);
        double ac = side(a, c);
        double bd = side(b, d);

        boolean check_t1 = checkIsTriangleExist(ab, bc, ac);
        boolean check_t2 = checkIsTriangleExist(cd, da, ac);
        boolean check_t3 = checkIsTriangleExist(ab, da, bd);
        boolean check_t4 = checkIsTriangleExist(cd, bc, bd);

        if (!check_t1 || !check_t2 || !check_t3 || !check_t4){
            isDegenerative = false;
        }

        return isDegenerative;
    }

    private boolean checkIsTriangleExist(double s1, double s2, double s3) {
        boolean check = false;

        double epsilon = 0.0001;
        boolean check1 = Math.abs(s1 + s2 - s3) > epsilon;
        boolean check2 = Math.abs(s2 + s3 - s1) > epsilon;
        boolean check3 = Math.abs(s1 + s3 - s2) > epsilon;

        if (s1 > 0 && s2 > 0 && s3 > 0 && check1 && check2 && check3){
            check = true;        // triangle is exist
        }
        return check;
    }

    private boolean checkIsConvex(){
        int countO = 0;

        Point crossPoint = findCrossPoint(a, b, c, d);

        if (a.getX() >= c.getX()){
            if (crossPoint.getX() <= a.getX() && crossPoint.getX() >= c.getX()){
                countO++;
            }
        }
        else{
            if (crossPoint.getX() <= c.getX() && crossPoint.getX() >= a.getX()){
                countO++;
            }
        }

        if (b.getX() >= d.getX()){
            if (crossPoint.getX() <= b.getX() && crossPoint.getX() >= d.getX()){
                countO++;
            }
        }
        else{
            if (crossPoint.getX() <= d.getX() && crossPoint.getX() >= b.getX()){
                countO++;
            }
        }

        return countO == 2;
    }

    private Point findCrossPoint(Point p1, Point p2, Point p3, Point p4){    // точки вводятся против часовой стрелки по четырехугольнику
                                                                             // (т.е. 1 и 3 точки - одна диагональб 2 и 4 - вторая)

        double k_ac;
        double b_ac;
        double k_bd;
        double b_bd;

        // уравнение для диагонали ac: y = k_ac * x + b_ac
        if (p3.getX() == p1.getX()){
            k_ac = p3.getY() - p1.getY();
            b_ac = 0;
        }
        else{
            k_ac = (p3.getY() - p1.getY()) / (p3.getX() - p1.getX());
            b_ac = p1.getY() - k_ac * p1.getX();
        }

        // уравнение для диагонали bd: y = k_bd * x + b_bd
        if (p2.getX() == p4.getX()){
            k_bd = p4.getY() - p2.getY();
            b_bd = 0;
        }
        else{
            k_bd = (p4.getY() - p2.getY()) / (p4.getX() - p2.getX());
            b_bd = p2.getY() - k_bd * p2.getX();
        }

        // координаты точки пересечения
        double o_x = (b_bd - b_ac) / (k_ac - k_bd);
        double o_y = k_ac * o_x + b_ac;

        return new Point(o_x, o_y);
    }

    @Override
    public Point centroid() {

        // for diagonal ac:
        Point bac_centroid = centroidTriangle(b, a, c);    // second point
        Point dac_centroid = centroidTriangle(d, a, c);    // forth point

        // for diagonal bd:
        Point cbd_centroid = centroidTriangle(c, b, d);    // third point
        Point abd_centroid = centroidTriangle(a, b, d);    // first point

        return findCrossPoint(abd_centroid, bac_centroid, cbd_centroid, dac_centroid);
    }

    private Point centroidTriangle(Point p1, Point p2, Point p3){

        double centroid_x, centroid_y;

        Point mid = middle(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        Point a = new Point(p1.getX(), p1.getY());

        double x_shift = abs((a.getX() - mid.getX())) / 3;
        double y_shift = abs((a.getY() - mid.getY())) / 3;

        if (p1.getX() > mid.getX()){
            centroid_x = mid.getX() + x_shift;
        }
        else{
            centroid_x = mid.getX() - x_shift;
        }

        if (p1.getY() > mid.getY()){
            centroid_y = mid.getY() + y_shift;
        }
        else{
            centroid_y = mid.getY() - y_shift;
        }

        return new Point(centroid_x, centroid_y);
    }

    private Point middle(double p1x, double p1y, double p2x, double p2y){
        double x = (p2x + p1x) / 2;
        double y = (p2y + p1y) / 2;
        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        boolean theSame = false;
        int countSamePoints = 0;
        double delta = 0.0001;

        if (figure instanceof Quadrilateral){
            Point other_a = ((Quadrilateral) figure).a;
            Point other_b = ((Quadrilateral) figure).b;
            Point other_c = ((Quadrilateral) figure).c;
            Point other_d = ((Quadrilateral) figure).d;

            Point[] abc = {a, b, c, d};
            Point[] other = {other_a, other_b, other_c, other_d};
            Point[] repeat = new Point[4];
            int indexRepeat = 0;
            for (Point value : abc) {
                for (Point point : other) {
                    if (!itsRepeat(point, repeat, countSamePoints)) {
                        if (abs(value.getX() - point.getX()) - delta <= 0 &&
                                abs(value.getY() - point.getY()) - delta <= 0) {
                            countSamePoints++;
                            repeat[indexRepeat] = point;
                            indexRepeat++;
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
