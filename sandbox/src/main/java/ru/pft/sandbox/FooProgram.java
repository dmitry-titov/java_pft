package ru.pft.sandbox;

public class FooProgram {
  public static void main(String[] args) {

    Point p1 = new Point();
    p1.x = 6.0;
    p1.y = 1.1;

    Point p2 = new Point();
    p2.x = -4.0;
    p2.y = 9.1;

    Point p3 = new Point(6.0, 1.1);
    Point p4 = new Point(-4.0, 9.1);

    System.out.println("Расстояние между точками (" + p1.x + "," + p1.y + ")" +
        " и (" + p2.x + "," + p2.y + ") через функции составляет " + distance(p1, p2));
    System.out.println("Расстояние между точками (" + p3.x + "," + p3.y + ")" +
        " и (" + p4.x + "," + p4.y + ") через методы класса Point составляет " + p3.distance(p4));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(differencePow(p2.x, p1.x) + (differencePow(p2.y, p1.y)));
  }

  public static double differencePow(double a, double b) {
    return Math.pow(a - b, 2);
  }
}