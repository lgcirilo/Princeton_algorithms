import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StopwatchCPU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private ArrayList<ArrayList<Point>> inSomeSegment = new ArrayList<ArrayList<Point>>();
    private ArrayList<Double> slopes = new ArrayList<Double>();
//    public int countslpto = 0;

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
<<<<<<< HEAD
                Arrays.sort(arr, points[i].slopeOrder());
                ArrayList<Point> collinear = new ArrayList<Point>();
                double currentSlope = Double.NEGATIVE_INFINITY;
                double slope;
                boolean segmentExists = false;
                for (int j = 1; j < arr.length; j++) {
=======
                arr = points.clone();
                Arrays.sort(arr, arr[i].slopeOrder());

                for (int j = 1; j < arr.length - 1; j++) {
>>>>>>> 24bc7ed1921416d80835f9308c3aa4c4f299e016
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
                        int index = -1;
                        if (collinear.size() >= 4) {
                            // this for loop determines whether we already found the current segment
                            for (int s = 0; s < slopes.size(); s++) {
                                if (Double.valueOf(slopes.get(s)) == currentSlope) {
                                    index = s;
                                    break;
                                }
                            }
                            if (index != -1) {
                                for (int t = 0; t < inSomeSegment.get(index).size(); t++) {
//                                    countslpto++;
//                                        if (inSomeSegment.get(index).get(t).slopeTo(collinear.get(0)) == currentSlope ||
//                                                inSomeSegment.get(index).get(t).slopeTo(collinear.get(0)) == Double.NEGATIVE_INFINITY) {
                                    if (inSomeSegment.get(index).get(t).slopeTo(collinear.get(0)) == currentSlope) {
                                        segmentExists = true;
//                                            inSomeSegment.get(s).add(collinear.get(0));
                                        break;
                                    }
                                }
                            }
//                                    if (index == s) { break; }
//                                }
//                            }
                            if (index == -1) {
                                slopes.add(currentSlope);
                                inSomeSegment.add(new ArrayList<Point>());
                                inSomeSegment.get(inSomeSegment.size() - 1).add(collinear.get(0));
                                Collections.sort(collinear);
                                segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                            } else {
                                if (!segmentExists) {
                                    inSomeSegment.get(index).add(collinear.get(0));
                                    Collections.sort(collinear);
                                    segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));

                                }
                            }
//                            if (segmentExists == false) {
//                                Collections.sort(collinear);
//                                segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
//                            }
                        }
//                        currentSlope = arr[0].slopeTo(arr[j]);
                        currentSlope = slope;
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
//        In in = new In(args[0]);      // input file
//        int arrSize = in.readInt();   // reads array size
//        int arrIndex = 0;             // keeps track of current array index
//        Point[] points = new Point[arrSize]; // creates FastCollinearPoints input array
//
//        // repeatedly read in point coordinates, creates points and adds them to array
//        while (!in.isEmpty()) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[arrIndex] = new Point(x,y);
//            arrIndex++;
//        }

        int rows = 4;
        int columns = 1024;
        Point[] points = new Point[rows*columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                points[index] = new Point(i,j);
                index++;
            }
        }

        StopwatchCPU sw = new StopwatchCPU();
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        System.out.println("number of unique line segments: " + fcp.numberOfSegments());
//        System.out.println("elapsed time: " + sw.elapsedTime());
//        System.out.println("slopeTo calls: " + points[0].countSlope);
//        System.out.println("dssdfsdf:      " + fcp.countslpto);
//        System.out.println("compare calls: " + points[0].countCompare);
//        int ops = 2*points[0].countCompare + points[0].countSlope;
//        System.out.println("slopeTo() + 2*compare(): " + ops);
    }
}