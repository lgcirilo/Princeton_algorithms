import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private ArrayList<LineSegment> uniqueSegments = new ArrayList<LineSegment>();
    private ArrayList<Point> collinear = new ArrayList<Point>();

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
                arr = points.clone();
                Arrays.sort(arr, arr[i].slopeOrder());

                for (int j = 1; j < arr.length - 1; j++) {
                    if (collinear.size() == 0) {
                        collinear.add(arr[0]);
                    }
                    if (arr[0].slopeTo(arr[j]) == arr[0].slopeTo(arr[j + 1])) {
                        collinear.add(arr[j]);
                        if (j == arr.length - 2) {
                            collinear.add(arr[j + 1]);
                            if (collinear.size() >= 4) {
                                Collections.sort(collinear);
                                segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                                collinear = new ArrayList<Point>();
                            } else {
                                collinear = new ArrayList<Point>();
                            }
                        }
                    } else {
                        collinear.add(arr[j]);
                        if (collinear.size() >= 4) {
                            Collections.sort(collinear);
                            segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                            collinear = new ArrayList<Point>();
                        } else {
                            collinear = new ArrayList<Point>();
                        }
                    }
                }
            }

            // select unique Line segments
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
                }
            }

        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return uniqueSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[numberOfSegments()];
        result = uniqueSegments.toArray(result);
        return result;
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

        FastCollinearPoints fcp = new FastCollinearPoints(points);
        System.out.println(fcp.numberOfSegments());
    }
}
