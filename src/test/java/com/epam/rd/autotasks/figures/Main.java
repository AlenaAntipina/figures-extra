package com.epam.rd.autotasks.figures;
import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {

        Figure t1 = new Triangle(new Point(0, 0), new Point(4, 0), new Point(0, 3));
        Figure t2 = new Triangle(new Point(1, 3), new Point(3, 9), new Point(0, 6));
        System.out.println("t1 and t2 the same? " + t1.isTheSame(t2));
        Figure t3 = new Triangle(new Point(2, 5), new Point(-5, 4), new Point(3, 0));     //   (0, 3)
        System.out.println("t3: " + t3.centroid().toString());

        Figure c1 = new Circle(new Point(0, 0), 1);
        Figure c2 = new Circle(new Point(0, 0), 1);
        System.out.println("c1 and c2 the same? " + c1.isTheSame(c2));       // true
        Figure c3 = new Circle(new Point(1 - (1d / 3 * 3), 3 - (30d / 10)), 1);
        Figure c4 = new Circle(new Point(0, 0), 1);
        System.out.println("c3 and c4 the same? " + c3.isTheSame(c4));       // false
        Figure c5 = new Circle(new Point(sqrt(2) * sqrt(2), 4 - sqrt(2) * sqrt(2)), sqrt(3) * sqrt(3));
        Figure c6 = new Circle(new Point(2, 2), 3);
        System.out.println("c5 and c6 the same? " + c5.isTheSame(c6));       // true
        Figure c7 = new Circle(new Point(0, 0), 1);
        System.out.println("c7: " + c7.centroid().toString());

        Figure q1 = new Quadrilateral(new Point(1, 0), new Point(2, 1), new Point(1, 2), new Point(0, 1));
        Figure q2 = new Quadrilateral(new Point(1, 0), new Point(2, 1), new Point(1, 2), new Point(0, 1));
        Figure q7 = new Quadrilateral(new Point(1, 0), new Point(3, 1), new Point(1, 2), new Point(0, 1));
        System.out.println("q1 and q2 the same? " + q1.isTheSame(q2));
        System.out.println("q1 and q7 the same? " + q1.isTheSame(q7));

        Figure q3 = new Quadrilateral(new Point(1, 0), new Point(2, 1), new Point(1, 2), new Point(0, 1));
        System.out.println("q3: " + q3.centroid().toString());



    }
}