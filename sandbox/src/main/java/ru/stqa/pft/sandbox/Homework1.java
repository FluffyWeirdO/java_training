package ru.stqa.pft.sandbox;

public class Homework1 {
    public static void main(String[] args) {

        Point p1 = new Point(1, 2);
        Point p2 = new Point(6, 4);
//      Function call
        System.out.println("Distance between p1" + "(" + p1.x + ", " + p1.y + ") and p2" + "(" + p2.x + ", " + p2.y + ") = " + distance(p1, p2));
//      Method call
        System.out.println("Distance between p1" + "(" + p1.x + ", " + p1.y + ") and p2" + "(" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
