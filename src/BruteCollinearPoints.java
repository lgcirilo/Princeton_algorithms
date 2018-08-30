import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        LineSegment segment;
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                            points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                            segment = new LineSegment(points[i], points[l]);
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
        BruteCollinearPoints bcp8 = new BruteCollinearPoints(input8);
        for (LineSegment ls: bcp8.segments()) {
            System.out.println(ls.toString());
        }
        System.out.println();
        BruteCollinearPoints bcp6 = new BruteCollinearPoints(input5);
        for (LineSegment ls: bcp6.segments()) {
            System.out.println(ls.toString());
        }
    }
}