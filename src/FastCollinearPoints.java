import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();
    private ArrayList<Point> collinear;
    private int count;
    private int maxOcurrences;
    private double currentSlope;
    private double collinearSlope;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        LineSegment segment;
        double[] slopesToP = new double[points.length];

        for ( int i = 0; i < points.length; i++) {
            maxOcurrences = 1;
            count = 1;
            collinear = new ArrayList<Point>();
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            for ( int j = 0; j < slopesToP.length; j++) {
                slopesToP[j] = p.slopeTo(points[j]);
            }
//            Arrays.sort(slopesToP);
            collinear.add(points[0]);
            currentSlope = slopesToP[1];
            for (int k = 1; k < slopesToP.length - 1; k++) {
                if (slopesToP[k] == slopesToP[k+1]) {
                    count++;
                    if (count == 3) {
                        collinearSlope = currentSlope;
                    }
                    if (maxOcurrences < count) {
                        maxOcurrences = count;
                    }
                } else {
                    count = 1;
                    currentSlope = slopesToP[k+1];
                }
            }
            if (maxOcurrences >= 3) {
                for (int l = 1; l < slopesToP.length; l++) {
                    if (slopesToP[l] == collinearSlope) {
                        collinear.add(points[l]);
                    }
                }
            }
            Collections.sort(collinear);

            segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)) );
//            System.out.println("collinear slope: " + collinearSlope);
//            System.out.println("-----------------");
//            for (Point point: collinear) {
//                System.out.println(point.toString());
//            }
//            System.out.println();
//            System.out.println();
////            System.out.println("max occurence count: " + maxOcurrences);
        }


//        if (slopesToP.length > 1) {
//            Double temp = slopesToP[1];
//            int count = 1;
//            for (int i = 1; i < slopesToP.length - 1; i++) {
//                if (slopesToP[i] == temp) {
//                    count++;
//                } else {
//                    temp = slopesToP[i];
//                }
//            }
//        }
//        System.out.println("fdfdg");

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

    public static void main(String[] args) {
        // creates input array using input6.txt values provided in the course
        Point[] input6 = { new Point(19000,10000),
                           new Point(18000,10000),
                           new Point(32000,10000),
                           new Point(21000,10000),
                           new Point(1234,5678),
                           new Point(14000,10000),
        };

        FastCollinearPoints fcp6 = new FastCollinearPoints(input6);

        // creates input array using input6.txt values provided in the course
        Point[] input8 = { new Point(10000,0),
                new Point(0,10000),
                new Point(3000,7000),
                new Point(7000,3000),
                new Point(20000,21000),
                new Point(3000,4000),
                new Point(14000,15000),
                new Point(6000,7000)
        };

        FastCollinearPoints fcp8 = new FastCollinearPoints(input8);

        for (LineSegment ls: fcp6.segments()) {
            System.out.println(ls.toString());
        }
        for (LineSegment ls: fcp8.segments()) {
            System.out.println(ls.toString());
        }
    }
}
