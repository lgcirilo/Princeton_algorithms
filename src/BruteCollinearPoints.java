import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        LineSegment segment;
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Point[] p = Arrays.copyOf(points, points.length);
        if (checkNulls(p)) {
            throw new java.lang.IllegalArgumentException();
        }
        Arrays.sort(p);
        if (checkRepeated(p)) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                for (int k = j + 1; k < p.length; k++) {
                    for (int l = k + 1; l < p.length; l++) {
                        if (p[i].slopeTo(p[j]) == p[i].slopeTo(p[k]) &&
                            p[i].slopeTo(p[k]) == p[i].slopeTo(p[l])) {
                            segment = new LineSegment(p[i], p[l]);
                            segments.add(segment);
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[numberOfSegments()];
        result = segments.toArray(result);
        return result;
    }

    private boolean checkRepeated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) {
                    return true;
            }
        }
        return false;
    }

    private boolean checkNulls(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                return true;
            }
        }
        return false;
    }

    // unit tests. Do not include this code in coursera submissions
    public static void main(String[] args) {
        // creates input array using input8.txt values provided in the course
        Point[] input8 = { new Point(10000,0),
                           new Point(0,10000),
                           new Point(3000,7000),
                           new Point(7000,3000),
                           new Point(20000,21000),
                           new Point(3000,4000),
                           new Point(14000,15000),
                           new Point(6000,7000)
        };

        // creates input array using input6.txt values provided in the course as base. Point(18000,10000) has been
        // stripped off of the original input6.txt since it's stated that no input that has 5 or more collinear
        // points will be supplied to BruteCollinearPoints
        Point[] input5 = { new Point(19000,10000),
                           new Point(32000,10000),
                           new Point(21000,10000),
                           new Point(1234,5678),
                           new Point(14000,10000),
        };

//        Point[] nil = null;
//        BruteCollinearPoints bcpNil = new BruteCollinearPoints(nil);

//        Point[] duplicatePoints = {
//                new Point(31757, 27749),
//                new Point(750,   602),
//                new Point(26727,   549),
//                new Point(31757, 27749),
//                new Point(9044,  4648)
//        };
//        BruteCollinearPoints dup = new BruteCollinearPoints(duplicatePoints);




//        BruteCollinearPoints bcp8 = new BruteCollinearPoints(input8);
//        for (LineSegment ls: bcp8.segments()) {
//            System.out.println(ls.toString());
//        }
//        System.out.println();
//        BruteCollinearPoints bcp5 = new BruteCollinearPoints(input5);
//        for (LineSegment ls: bcp5.segments()) {
//            System.out.println(ls.toString());
//        }
    }
}