package ru.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dtitov on 26.10.16.
 */
public class DistanceTests {

  @Test
  public void checkDistanceAssertEqualsTest(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(2,5);
    Assert.assertEquals(Math.round(p1.distance(p2)), 3);
  }

  @Test
  public void checkDistanceAssertFalseTest(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(2,8);
    Assert.assertFalse(Math.round(p1.distance(p2))!= 6);
  }

  @Test
  public void checkDistanceAssertTrueTest(){
    Point p1 = new Point(1,7);
    Point p2 = new Point(2,2);
    Assert.assertTrue(p1.distance(p2) != 2);
  }

  @Test
  public void checkDistanceSamePointTest(){
    Point p1 = new Point(7,7);
    Point p2 = new Point(7,7);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }
}
