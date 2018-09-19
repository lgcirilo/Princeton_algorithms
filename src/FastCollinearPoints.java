import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private ArrayList<Point> inSomeSegment = new ArrayList<Point>();
    private ArrayList<Double> slopes = new ArrayList<Double>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        Point[] arr;
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (checkNulls(points)) {
            throw new java.lang.IllegalArgumentException();
        }
        arr = points.clone();
        Arrays.sort(arr);
        if (checkRepeated(arr)) {
            throw new java.lang.IllegalArgumentException();
        }
        if (points.length >= 4) {

            for (int i = 0; i < points.length; i++) {
                // find collinear segments for each point in array arr.
                Arrays.sort(arr, points[i].slopeOrder());
                ArrayList<Point> collinear = new ArrayList<Point>();
                double currentSlope = Double.NEGATIVE_INFINITY;
                double slope;
                boolean segmentExists = false;
                for (int j = 1; j < arr.length; j++) {
                    if (collinear.size() == 0) {
                        collinear.add(arr[0]);
                    }
                    slope = arr[0].slopeTo(arr[j]);
                    if (currentSlope == Double.NEGATIVE_INFINITY) {
                        currentSlope = slope;
                    }
                    if (slope == currentSlope) {
                        collinear.add(arr[j]);
                    }
                    if (slope != currentSlope || j == arr.length - 1) {
//                    else {
                        if (collinear.size() >= 4) {
                            // this for loop determines whether we already found the current segment
                            for (int s = 0; s < slopes.size(); s++) {
                                if (slopes.get(s) == currentSlope) {
                                    if (inSomeSegment.get(s).slopeTo(collinear.get(0)) == currentSlope) {
                                        segmentExists = true;
                                        break;
                                    }
                                }
                            }
                            if (segmentExists == false) {
                                slopes.add(currentSlope);
                                inSomeSegment.add(arr[0]);
                                Collections.sort(collinear);
                                segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                            }
                        }
                        currentSlope = arr[0].slopeTo(arr[j]);
//                        collinear = new ArrayList<Point>();
                        collinear.clear();
                        collinear.add(arr[0]);
                        collinear.add(arr[j]);
                        segmentExists = false;
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
        return segments.toArray(result);
    }

    private boolean checkNulls(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRepeated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i+1]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int arrSize = in.readInt();   // reads array size
        int arrIndex = 0;             // keeps track of current array index
        Point[] points = new Point[arrSize]; // creates FastCollinearPoints input array

        // repeatedly read in point coordinates, creates points and adds them to array
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            points[arrIndex] = new Point(x,y);
            arrIndex++;
        }

//        int rows = 1;
//        int columns = 4096;
//        Point[] points = new Point[rows*columns];
//        int index = 0;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                points[index] = new Point(i,j);
//                index++;
//            }
//        }

        FastCollinearPoints fcp = new FastCollinearPoints(points);
        System.out.println(fcp.numberOfSegments());

    }
}