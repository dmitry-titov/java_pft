package ru.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dtitov on 26.10.16.
 */
public class DistanceTests {

  @Test
  public void checkDistanceAssertEquals(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(2,5);
    Assert.assertEquals(Math.round(p1.distance(p2)), 3);
  }

  @Test
  public void checkDistanceAssertFalse(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(2,8);
    Assert.assertFalse(Math.round(p1.distance(p2))!= 6);
  }

  @Test
  public void checkDistanceAssertTrue(){
    Point p1 = new Point(1,7);
    Point p2 = new Point(2,2);
    Assert.assertTrue(p1.distance(p2) != 2);
  }
}
