package ProgrammingAssignment;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final Point[] points;
    private LineSegment[] segments;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
        for (Point point : this.points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < this.points.length; i++) {
            Point p = this.points[i];
            for (int j = i + 1; j < this.points.length; j++) {
                Point q = this.points[j];
                if (p == null || q == null || p.compareTo(q) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        Arrays.sort(this.points);


    }

    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }

    // the line segments
    public LineSegment[] segments() {
        if(this.segments == null) {
            ArrayList<LineSegment> segments = new ArrayList<>();
            for (int i = 0; i < points.length; i++) {
                Point p = points[i];
                for (int j = i + 1; j < points.length; j++) {
                    Point q = points[j];
                    for (int k = j + 1; k < points.length; k++) {
                        Point r = points[k];
                        for (int l = k + 1; l < points.length; l++) {
                            Point s = points[l];
                            double pq = p.slopeTo(q);
                            double pr = p.slopeTo(r);
                            double ps = p.slopeTo(s);
                            if (Double.compare(pq, pr) == 0 && Double.compare(pq, ps) == 0) {
                                segments.add(new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
            this.segments = segments.toArray(LineSegment[]::new);
        }
        LineSegment[] toReturn = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++) {
            toReturn[i] = segments[i];
        }
        return toReturn;
    }
}

