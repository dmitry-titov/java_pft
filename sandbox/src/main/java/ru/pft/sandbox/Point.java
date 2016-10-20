package ru.pft.sandbox;

/**
 * Created by dtitov on 20.10.16.
 */
public class Point {

  double x;
  double y;

  public Point() {
  }

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p){
    return Math.sqrt(differencePow(p.x, this.x) + (differencePow(p.y, this.y)));
  }

  public double differencePow(double a, double b){
    return Math.pow(a - b, 2);
  }
}
