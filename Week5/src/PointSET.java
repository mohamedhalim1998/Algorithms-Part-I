import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class PointSET {
    private final SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException();
        }
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException();
        }
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null){
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> inside = new ArrayList<>();
        for (Point2D point : points) {
            if (point.x() >= rect.xmin() && point.x() <= rect.xmax()
                    && point.y() >= rect.ymin() && point.y() <= rect.ymax()) {
                inside.add(point);
            }
        }
        return inside;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        double min = Double.MAX_VALUE;
        Point2D nearestPoint = null;
        for (Point2D point : points) {
            double d = p.distanceTo(point);
            if (d < min) {
                min = d;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

}