/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class PointsCollection {
    private ArrayList<Point> points = new ArrayList<Point>();

    public PointsCollection() {
    }

    public PointsCollection(PointsCollection pc) {
        for (int i = 0; i < pc.points.size(); ++i) {
            addPoint(pc.points.get(i).getX(), pc.points.get(i).getY());
            points.get(i).setSkipped(pc.points.get(i).isSkipped());
            points.get(i).setReflection(pc.points.get(i).isReflection());
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void createFromFile(String fileName) throws IOException {
        Scanner scanner = null;
            File file = new File(fileName);
            scanner = new Scanner(file);

        while(scanner.hasNextInt()) {
            addPoint(scanner.nextInt(), scanner.nextInt());
        }
    }

    public void createRandomly(int numOfPoints) {
        RandomGenerator generator = new RandomGenerator();

        for (int i = 0; i < numOfPoints; ++i) {
            addPoint(generator.getRandomInt(), generator.getRandomInt());
        }
    }

    public void createRandomly(int numOfPoints, int from, int to) {
        RandomGenerator generator = new RandomGenerator();

        for (int i = 0; i < numOfPoints; ++i) {
            addPoint(generator.getRandomInt(from, to), generator.getRandomInt(from, to));
        }
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void addPoint(int x, int y, boolean reflection) {
        points.add(new Point(x, y, reflection));
    }

    public Point findMaxXPoint() {
        Point bestFound = points.get(0);
        for (Point p : points) {
            if(p.getX() > bestFound.getX()) {
                bestFound = p;
            }
        }
        return bestFound;
    }

    public Point findMinXPoint() {
        Point bestFound = points.get(0);
        for (Point p : points) {
            if(p.getX() < bestFound.getX()) {
                bestFound = p;
            }
        }
        return bestFound;
    }

    public Point findMaxYPoint() {
        Point bestFound = points.get(0);
        for (Point p : points) {
            if(p.getY() > bestFound.getY()) {
                bestFound = p;
            }
        }
        return bestFound;
    }

    public Point findMinYPoint() {
        Point bestFound = points.get(0);
        for (Point p : points) {
            if(p.getY() < bestFound.getY()) {
                bestFound = p;
            }
        }
        return bestFound;
    }

    public void addReflection(Point originalPoint) {
        addPoint(originalPoint.getY(), originalPoint.getX(), true);
    }

    public void sortPointsByX() {
        points.sort(Comparator.comparing(Point::getX));
    }

    public void sortPointsByY() {
        points.sort(Comparator.comparing(Point::getY));
    }
}
