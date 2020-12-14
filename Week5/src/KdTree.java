import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KdTree {
    private Node root;

    public KdTree() {

    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node x) {
        if (x == null) {
            return 0;
        }
        return x.count;
    }

    public void insert(Point2D point2D) {
        if (point2D == null) {
            throw new IllegalArgumentException();
        }
        if (contains(point2D)) {
            return;
        }
        root = insertHelper(root, point2D, true);
    }

    private Node insertHelper(Node x, Point2D point2D, boolean vertical) {
        if (x == null) {
            Node node = new Node(point2D, vertical);
            node.count = 1;
            return node;
        }
        if (vertical) {
            int compare = Double.compare(point2D.x(), x.point.x());
            if (compare >= 0) {
                x.right = insertHelper(x.right, point2D, false);
            } else {
                x.left = insertHelper(x.left, point2D, false);
            }
            x.count = 1 + sizeHelper(x.right) + sizeHelper(x.left);
        } else {
            int compare = Double.compare(point2D.y(), x.point.y());
            if (compare >= 0) {
                x.right = insertHelper(x.right, point2D, true);
            } else {
                x.left = insertHelper(x.left, point2D, true);
            }
            x.count = 1 + sizeHelper(x.right) + sizeHelper(x.left);
        }
        return x;
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        return containsHelper(root, point);
    }

    private boolean containsHelper(Node x, Point2D point) {
        if (x == null) {
            return false;
        }
        if (point.compareTo(x.point) == 0) {
            return true;
        }
        if (x.vertical) {
            int compare = Double.compare(point.x(), x.point.x());
            if (compare < 0) {
                return containsHelper(x.left, point);
            } else {
                return containsHelper(x.right, point);
            }
        } else {
            int compare = Double.compare(point.y(), x.point.y());
            if (compare < 0) {
                return containsHelper(x.left, point);
            } else {
                return containsHelper(x.right, point);
            }
        }
    }

    public void draw() {
        drawHelper(root, 0, 1, 0, 1, true);
    }

    private void drawHelper(Node x, double minx, double maxx, double miny, double maxy, boolean vertical) {
        if (x == null) {
            return;
        }
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.point(x.point.x(), x.point.y());
        StdDraw.setPenRadius();
        if (vertical) {
            StdDraw.setPenColor(255, 0, 0);
            StdDraw.line(x.point.x(), miny, x.point.x(), maxy);
            drawHelper(x.left, minx, x.point.x(), miny, maxy, false);
            drawHelper(x.right, x.point.x(), maxx, miny, maxy, false);
        } else {
            StdDraw.setPenColor(0, 0, 255);
            StdDraw.line(minx, x.point.y(), maxx, x.point.y());
            drawHelper(x.left, minx, maxx, miny, x.point.y(), true);
            drawHelper(x.right, minx, maxx, x.point.y(), maxy, true);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> inside = new ArrayList<>();
        rangeHelper(root, rect, inside);
        return inside;
    }

    private void rangeHelper(Node x, RectHV rect, ArrayList<Point2D> inside) {
        if (x == null) {
            return;
        }
//        if(x.vertical){
//            if(rect.intersects(new RectHV(x.point.x(),0, x.point.x())))
//        }
        if (x.point.x() >= rect.xmin() && x.point.x() <= rect.xmax()
                && x.point.y() >= rect.ymin() && x.point.y() <= rect.ymax()) {
            inside.add(x.point);
        }
        if (x.vertical) {
            if (x.point.x() >= rect.xmin() && x.point.x() <= rect.xmax()) {
                rangeHelper(x.left, rect, inside);
                rangeHelper(x.right, rect, inside);
            } else if (x.point.x() > rect.xmin() && x.point.x() >= rect.xmax()) {
                rangeHelper(x.left, rect, inside);
            } else if (x.point.x() <= rect.xmin() && x.point.x() < rect.xmax()) {
                rangeHelper(x.right, rect, inside);
            }
        } else {
            if (x.point.y() >= rect.ymin() && x.point.y() <= rect.ymax()) {
                rangeHelper(x.left, rect, inside);
                rangeHelper(x.right, rect, inside);
            } else if (x.point.y() > rect.ymin() && x.point.y() >= rect.ymax()) {
                rangeHelper(x.left, rect, inside);
            } else if (x.point.y() <= rect.ymin() && x.point.y() < rect.ymax()) {
                rangeHelper(x.right, rect, inside);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        DistancePoint res = nearestHelper(root, p);
        //System.out.println(res);
        return res.point;
    }

    private DistancePoint nearestHelper(Node x, Point2D p) {
        if (x == null) {
            return new DistancePoint(null, Double.MAX_VALUE);
        }

        double d = p.distanceSquaredTo(x.point);
        DistancePoint parentRes = new DistancePoint(x.point, d);

        DistancePoint leftRes = nearestHelper(x.left, p);
        DistancePoint rightRes = nearestHelper(x.right, p);
        return Collections.min(Arrays.asList(parentRes, leftRes, rightRes));
    }

    private static class DistancePoint implements Comparable<DistancePoint> {
        Point2D point;
        double distance;

        public DistancePoint(Point2D point, double distance) {
            this.point = point;
            this.distance = distance;
        }


        @Override
        public String toString() {
            return point.toString() + " : " + distance;
        }

        @Override
        public int compareTo(DistancePoint distancePoint) {
            return Double.compare(distance, distancePoint.distance);
        }
    }

    private static class Node {
        Node left, right;
        Point2D point;
        boolean vertical;
        int count;

        public Node(Point2D point, boolean vertical) {
            this.point = point;
            this.vertical = vertical;
        }
    }

}
