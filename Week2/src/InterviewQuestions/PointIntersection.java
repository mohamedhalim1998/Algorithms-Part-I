package InterviewQuestions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class PointIntersection {
    private class Point {
        double x, y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 &&
                    Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int countIntersections(Point[] a, Point[] b) {
        HashSet<Point> aPoints = new HashSet<>(Arrays.asList(a));
        int count = 0;
        for (Point p : b) {
            if (aPoints.contains(p)) {
                count++;
            }
        }
        return count;
    }

    public int countIntersections2(Point[] a, Point[] b) {
        int count = 0;
        for (Point p1 : b) {
            for (Point p2 : a) {
                if (p2.equals(p1)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
