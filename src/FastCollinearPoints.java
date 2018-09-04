import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private ArrayList<LineSegment> uniqueSegments = new ArrayList<LineSegment>();
    private ArrayList<Point> collinear;
    private int count;
    private int max;
    private double currentSlope;
    private double collinearSlope;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        Point p;
        double[] slopesToP = new double[points.length];

        for ( int i = 0; i < points.length; i++) {
            max = 1;
            count = 1;
            collinear = new ArrayList<Point>();
            p = points[i];
            Arrays.sort(points, p.slopeOrder());
            for ( int j = 0; j < slopesToP.length; j++) {
                slopesToP[j] = p.slopeTo(points[j]);
            }
            collinear.add(points[0]);
            currentSlope = slopesToP[1];
            for (int k = 1; k < slopesToP.length - 1; k++) {
                if (slopesToP[k] == slopesToP[k+1]) {
                    count++;
                    if (count == 3) {
                        collinearSlope = currentSlope;
                    }
                    if (max < count) {
                        max = count;
                    }
                } else {
                    count = 1;
                    currentSlope = slopesToP[k+1];
                }
            }
            if (max >= 3) {
                for (int l = 1; l < slopesToP.length; l++) {
                    if (slopesToP[l] == collinearSlope) {
                        collinear.add(points[l]);
                    }
                }
                Collections.sort(collinear);
//                LineSegment currentSegment = new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1));
                segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
            }
        }
        for ( int i = 0; i < segments.size(); i ++) {
                boolean exists = false;
                String str1 = segments.get(i).toString();
                if (uniqueSegments.size() == 0) {
                    uniqueSegments.add(segments.get(i));
                    exists = true;
                } else {
                    for (int j = 0; j < uniqueSegments.size(); j++) {
                        String str2 = uniqueSegments.get(j).toString();
                        if (str2.equals(str1)) {
                            exists = true;
                        }
                    }
                }
            if (exists == false) {
                uniqueSegments.add(segments.get(i));
                exists = true;
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

//        // creates input array using input6.txt values provided in the course
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



        System.out.println("\n\n\n\n\n");
        for (LineSegment ls: fcp6.segments()) {
            System.out.println(ls.toString());
        }

        System.out.println("\n\n\n\n\n\n");
        for (LineSegment ls: fcp8.segments()) {
            System.out.println(ls.toString());
        }
        System.out.println("iuhgik");
    }
}
