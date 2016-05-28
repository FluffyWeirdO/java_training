package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testPoint() {
        Point p1 = new Point(-1, 3);
        Point p2 = new Point(7, 5);

        Assert.assertTrue(p1.distance(p2) > 0 && p1.distance(p2) < 100);
        Assert.assertTrue(p1.distance(p2) > 6);
        Assert.assertEquals(p1.distance(p2), 8.246211251235321);
        Assert.assertNotEquals(p2.distance(p1), 9);
        Assert.assertNotEquals(p1, p2);
        Assert.assertEquals(p1.distance(p2), p2.distance(p1));
    }
}
