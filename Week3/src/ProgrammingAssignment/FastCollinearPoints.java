package ProgrammingAssignment;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final Point[] points;
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }

    // the line segments
    public LineSegment[] segments() {
        if (this.segments == null) {
            ArrayList<LineSegment> segments = new ArrayList<>();
            Point[] original = new Point[points.length];
            for (int i = 0; i < points.length; i++) {
                original[i] = points[i];
            }
            Arrays.sort(original);
            for (int i = 0; i < points.length; i++) {
                Point p = original[i];
                Arrays.sort(points);
                Arrays.sort(points, p.slopeOrder());
                int count = 0;
                for (int j = 1; j < points.length; j++) {
                    double currentSlope = p.slopeTo(points[j]);
                    while (j < points.length && Double.compare(currentSlope, p.slopeTo(points[j])) == 0) {
                        count++;
                        j++;
                    }

                    if (count >= 3) {
                        if (p.compareTo(points[j - count]) < 0)
                            segments.add(new LineSegment(p, points[j - 1]));


                    }
                    count = 0;
                    j--;

                }

                this.segments = segments.toArray(LineSegment[]::new);
            }
        }
        LineSegment[] toReturn = new LineSegment[segments.length];
        for (int i = 0; i < segments.length; i++) {
            toReturn[i] = segments[i];
        }
        return toReturn;
    }
}