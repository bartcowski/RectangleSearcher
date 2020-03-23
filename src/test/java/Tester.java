import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    @Test
    void shouldCalculateRectanglePerimeter() {
        FoundRectangle rect = new FoundRectangle(new Point(5, -3), new Point(-2, 14),
                                                 new Point(0, 20), new Point(3, -7));
        assertEquals(68, rect.calculatePerimeter());
    }

    @Test
    void shouldAddPointsToCollection() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(1, 1);
        pc.addPoint(-1, 2);
        pc.addPoint(3, 0);

        assertEquals(3, pc.getPoints().size());
    }

    @Test
    void shouldFindPointWithBiggestXCoordinate() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(1, 1);
        pc.addPoint(-1, 2);
        pc.addPoint(3, 7);
        pc.addPoint(9, -3);
        pc.addPoint(-12, 4);

        assertEquals(9, pc.findMaxXPoint().getX());
    }

    @Test
    void shouldFindPointWithSmallestXCoordinate() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(1, 1);
        pc.addPoint(-1, 2);
        pc.addPoint(3, 7);
        pc.addPoint(9, -3);
        pc.addPoint(-12, 4);

        assertEquals(-12, pc.findMinXPoint().getX());
    }

    @Test
    void shouldFindPointWithBiggestYCoordinate() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(1, 1);
        pc.addPoint(-1, 2);
        pc.addPoint(3, 7);
        pc.addPoint(9, -3);
        pc.addPoint(-12, 4);

        assertEquals(7, pc.findMaxYPoint().getY());
    }

    @Test
    void shouldFindPointWithSmallestYCoordinate() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(1, 1);
        pc.addPoint(-1, 2);
        pc.addPoint(3, 7);
        pc.addPoint(9, -3);
        pc.addPoint(-12, 4);

        assertEquals(-3, pc.findMinYPoint().getY());
    }

    @RepeatedTest(10)
    void shouldGenerateRandomNumberInGivenRange() {
        RandomGenerator rg = new RandomGenerator();
        int test = rg.getRandomInt(-123, 321);
        assertEquals(true, test >= -123 && test <= 321);
    }

    @Test
    void shouldGenerateCertainNumber() {
        RandomGenerator rg = new RandomGenerator();
        assertEquals(-100, rg.getRandomInt(-100, -100));
    }

    @Test
    void shouldSortPointsByXCoordinate() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(10, 7);
        pc.addPoint(-2, 9);
        pc.addPoint(4, -10);
        pc.addPoint(0, 0);
        pc.addPoint(-3, 1);
        pc.sortPointsByX();

        assertAll(
                () -> assertEquals(-3, pc.getPoints().get(0).getX()),
                () -> assertEquals(-2, pc.getPoints().get(1).getX()),
                () -> assertEquals(0, pc.getPoints().get(2).getX()),
                () -> assertEquals(4, pc.getPoints().get(3).getX()),
                () -> assertEquals(10, pc.getPoints().get(4).getX())
        );
    }

    @Test
    void shouldSortPointsByYCoordinate() {
        PointsCollection pc = new PointsCollection();
        pc.addPoint(10, 7);
        pc.addPoint(-2, 9);
        pc.addPoint(4, -10);
        pc.addPoint(0, 0);
        pc.addPoint(-3, 1);
        pc.sortPointsByY();

        assertAll(
                () -> assertEquals(-10, pc.getPoints().get(0).getY()),
                () -> assertEquals(0, pc.getPoints().get(1).getY()),
                () -> assertEquals(1, pc.getPoints().get(2).getY()),
                () -> assertEquals(7, pc.getPoints().get(3).getY()),
                () -> assertEquals(9, pc.getPoints().get(4).getY())
        );
    }

    @Test
    void shouldShowThatPointsAreEqual() {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(1, 3);

        assertTrue(p1.equals(p2));
    }

    @Test
    void shouldShowThatPointsAreNotEqual() {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(-10, -10);

        assertFalse(p2.equals(p1));
    }

    @Test
    void shouldCreateDeepCopyOfPointsCollection() {
        PointsCollection pc1 = new PointsCollection();
        pc1.addPoint(5, 10);
        pc1.addPoint(0, 0);
        PointsCollection pc2 = new PointsCollection(pc1);
        pc2.getPoints().get(1).setX(-100);
        pc2.addPoint(1, 1);

        assertAll(
                () -> assertEquals(-100, pc2.getPoints().get(1).getX()),
                () -> assertEquals(0, pc1.getPoints().get(1).getX()),
                () -> assertEquals(2, pc1.getPoints().size()),
                () -> assertEquals(3, pc2.getPoints().size())
        );
    }
}
