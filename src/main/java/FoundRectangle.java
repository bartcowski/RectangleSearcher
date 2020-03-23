/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

public class FoundRectangle {
    //points that establish borders of rectangle
    private Point maxX = null;
    private Point minX = null;
    private Point maxY = null;
    private Point minY = null;
    private int originalPoints = 0;

    public FoundRectangle() {
    }

    public FoundRectangle(Point maxX, Point minX, Point maxY, Point minY) {
        this.maxX = maxX;
        this.minX = minX;
        this.maxY = maxY;
        this.minY = minY;
    }

    public Point getMaxX() {
        return maxX;
    }

    public void setMaxX(Point maxX) {
        this.maxX = maxX;
    }

    public Point getMinX() {
        return minX;
    }

    public void setMinX(Point minX) {
        this.minX = minX;
    }

    public Point getMaxY() {
        return maxY;
    }

    public void setMaxY(Point maxY) {
        this.maxY = maxY;
    }

    public Point getMinY() {
        return minY;
    }

    public void setMinY(Point minY) {
        this.minY = minY;
    }

    public int getOriginalPoints() {
        return originalPoints;
    }

    public void setOriginalPoints(int originalPoints) {
        this.originalPoints = originalPoints;
    }

    public int calculatePerimeter() {
        return 2*((maxX.getX() - minX.getX()) + (maxY.getY() - minY.getY()));
    }
}
