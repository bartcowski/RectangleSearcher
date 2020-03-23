/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

public class Point {
    private int x;
    private int y;
    private boolean skipped = false;
    private boolean reflection = false;

    public Point() {

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, boolean reflection) {
        this.x = x;
        this.y = y;
        this.reflection = reflection;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public boolean isReflection() {
        return reflection;
    }

    public void setReflection(boolean reflection) {
        this.reflection = reflection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
}
