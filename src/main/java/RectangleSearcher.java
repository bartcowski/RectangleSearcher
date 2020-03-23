/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

public class RectangleSearcher {
    private PointsCollection originalPointsCollection;
    private FoundRectangle currentRectangle = null;
    private FoundRectangle bestRectangle = null;

    public RectangleSearcher() {
    }

    public RectangleSearcher(PointsCollection pc) {
        this.originalPointsCollection = pc;
    }

    public FoundRectangle getBestRectangle() {
        return bestRectangle;
    }

    //Main Algorithm
    public void searchForRectangles() {
        bestRectangle = findSmallestRectangle();
        ResultDisplay.showResults(bestRectangle, createRectangleFromCollection(originalPointsCollection),
                        createRectangleFromCollection(originalPointsCollection).getOriginalPoints() - bestRectangle.getOriginalPoints());
    }

    public void benchmarkSearchForRectangles() {
        bestRectangle = findSmallestRectangle();
    }

    private FoundRectangle findSmallestRectangle() {
        int countUnder = 0;         //for checking if whole rectangle is UNDER y=x line
        int countOver = 0;          //for checking if whole rectangle is OVER y=x line
        int countDiagonal = 0;      //for checking if rectangle is made by 2 points on y=x line (those points make rectangle's diagonal)

        //setting up rectangle that contains only original points
        currentRectangle = createRectangleFromCollection(originalPointsCollection);

        //3 situations where creating reflections won't make perimeter smaller
        for (Point p : originalPointsCollection.getPoints()) {
            if (p.getX() >= p.getY()) {
                ++countUnder;
            }
            if (p.getY() >= p.getX()) {
                ++countOver;
            }
            if ((p.getX() == p.getY() && p.getX() == currentRectangle.getMaxX().getX() && p.getY() == currentRectangle.getMaxY().getY())
                || (p.getX() == p.getY() && p.getX() == currentRectangle.getMinX().getX() && p.getY() == currentRectangle.getMinY().getY())) {
                ++countDiagonal;
            }
        }
        if (countUnder == originalPointsCollection.getPoints().size() || countOver == originalPointsCollection.getPoints().size() || countDiagonal == 2) {
            return currentRectangle;
        }

        //Other situations
        PointsCollection currentCollection = new PointsCollection(originalPointsCollection);    //for checking 4 sides with the same collection
        PointsCollection testCollection = null;                                                 //for testing if new one is better
        PointsCollection bestCollectionYet = null;                                              //for saving best one out of all 4 found
        FoundRectangle testRectangle = null;
        boolean foundBetter = true;                                                             //stops the loop when no smaller rectangle could've been found

        while (foundBetter) {
            testCollection = reflectTop(currentCollection);
            testRectangle = createRectangleFromCollection(testCollection);
            if (testRectangle.calculatePerimeter() < currentRectangle.calculatePerimeter()
                || (testRectangle.calculatePerimeter() == currentRectangle.calculatePerimeter()
                    && testRectangle.getOriginalPoints() > currentRectangle.getOriginalPoints())) {
                bestCollectionYet = testCollection;
            }

            testCollection = reflectRight(currentCollection);
            testRectangle = createRectangleFromCollection(testCollection);
            if (testRectangle.calculatePerimeter() < currentRectangle.calculatePerimeter()
                    || (testRectangle.calculatePerimeter() == currentRectangle.calculatePerimeter()
                    && testRectangle.getOriginalPoints() > currentRectangle.getOriginalPoints())) {
                if (bestCollectionYet == null || testRectangle.calculatePerimeter() < createRectangleFromCollection(bestCollectionYet).calculatePerimeter()) {
                    bestCollectionYet = testCollection;
                }
            }

            testCollection = reflectBot(currentCollection);
            testRectangle = createRectangleFromCollection(testCollection);
            if (testRectangle.calculatePerimeter() < currentRectangle.calculatePerimeter()
                    || (testRectangle.calculatePerimeter() == currentRectangle.calculatePerimeter()
                    && testRectangle.getOriginalPoints() > currentRectangle.getOriginalPoints())) {
                if (bestCollectionYet == null || testRectangle.calculatePerimeter() < createRectangleFromCollection(bestCollectionYet).calculatePerimeter()) {
                    bestCollectionYet = testCollection;
                }
            }

            testCollection = reflectLeft(currentCollection);
            testRectangle = createRectangleFromCollection(testCollection);
            if (testRectangle.calculatePerimeter() < currentRectangle.calculatePerimeter()
                    || (testRectangle.calculatePerimeter() == currentRectangle.calculatePerimeter()
                    && testRectangle.getOriginalPoints() > currentRectangle.getOriginalPoints())) {
                if (bestCollectionYet == null || testRectangle.calculatePerimeter() < createRectangleFromCollection(bestCollectionYet).calculatePerimeter()) {
                    bestCollectionYet = testCollection;
                }
            }

            //after checking all possibilities
            if (bestCollectionYet == null) {
                foundBetter = false;
            } else {
                currentCollection = bestCollectionYet;
                bestCollectionYet = null;
                currentRectangle = createRectangleFromCollection(currentCollection);
            }
        }
        return createRectangleFromCollection(currentCollection);
    }

    private FoundRectangle createRectangleFromCollection(PointsCollection pointsCollection) {
        FoundRectangle rectangle = new FoundRectangle();

        pointsCollection.sortPointsByX();
        rectangle.setMaxX(establishMaxBoundaries(pointsCollection));
        rectangle.setMinX(establishMinBoundaries(pointsCollection));
        pointsCollection.sortPointsByY();
        rectangle.setMaxY(establishMaxBoundaries(pointsCollection));
        rectangle.setMinY(establishMinBoundaries(pointsCollection));

        for (Point p : pointsCollection.getPoints()) {
            if (!p.isSkipped() && !p.isReflection()) {
                rectangle.setOriginalPoints(rectangle.getOriginalPoints() + 1);
            }
        }

        return rectangle;
    }

    //establishes rectangle's top side if points sorted by Y coordinate or right side if sorted by X coordinate
    private Point establishMaxBoundaries(PointsCollection pc) {
        for (int i = pc.getPoints().size() - 1; i >= 0; --i) {
            if (!pc.getPoints().get(i).isSkipped()) {
                return pc.getPoints().get(i);
            }
        }
        return null;
    }

    //establishes rectangle's bottom side if points sorted by Y coordinate or left side if sorted by X coordinate
    private Point establishMinBoundaries(PointsCollection pc) {
        for (int i = 0; i < pc.getPoints().size(); ++i) {
            if (!pc.getPoints().get(i).isSkipped()) {
                return pc.getPoints().get(i);
            }
        }
        return null;
    }

    private PointsCollection reflectTop(PointsCollection pointsCollection) {
        pointsCollection.sortPointsByY();
        PointsCollection newCollection = new PointsCollection(pointsCollection);

        int i = pointsCollection.getPoints().size() - 1;
        while (pointsCollection.getPoints().get(i).getY() == currentRectangle.getMaxY().getY()) {
            newCollection.addReflection(pointsCollection.getPoints().get(i));
            newCollection.getPoints().get(i).setSkipped(true);
            --i;
        }
        return newCollection;
    }

    private PointsCollection reflectRight(PointsCollection pointsCollection) {
        pointsCollection.sortPointsByX();
        PointsCollection newCollection = new PointsCollection(pointsCollection);

        int i = pointsCollection.getPoints().size() - 1;
        while (pointsCollection.getPoints().get(i).getX() == currentRectangle.getMaxX().getX()) {
            newCollection.addReflection(pointsCollection.getPoints().get(i));
            newCollection.getPoints().get(i).setSkipped(true);
            --i;
        }
        return newCollection;
    }

    private PointsCollection reflectBot(PointsCollection pointsCollection) {
        pointsCollection.sortPointsByY();
        PointsCollection newCollection = new PointsCollection(pointsCollection);
        int i = 0;
        while (pointsCollection.getPoints().get(i).getY() == currentRectangle.getMinY().getY()) {
            newCollection.addReflection(pointsCollection.getPoints().get(i));
            newCollection.getPoints().get(i).setSkipped(true);
            ++i;
        }
        return newCollection;
    }

    private PointsCollection reflectLeft(PointsCollection pointsCollection) {
        pointsCollection.sortPointsByX();
        PointsCollection newCollection = new PointsCollection(pointsCollection);

        int i = 0;
        while (pointsCollection.getPoints().get(i).getX() == currentRectangle.getMinX().getX()) {
            newCollection.addReflection(pointsCollection.getPoints().get(i));
            newCollection.getPoints().get(i).setSkipped(true);
            ++i;
        }
        return newCollection;
    }
}
