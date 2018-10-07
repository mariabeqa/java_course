package maria.belyaeva.qa;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(3.0,-4.0);
        Point p2 = new Point(-6.0,5.0);
        Double d = p1.distance(p2);

        Assert.assertEquals(p1.x,3.0);
        Assert.assertEquals(p1.y,-4.0);
        Assert.assertEquals(p2.x,-6.0);
        Assert.assertEquals(p2.y,10.0);
        Assert.assertEquals(Math.round(d),13);

    }
}
