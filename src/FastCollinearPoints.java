import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

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
        Point[] arr;
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (checkNulls(points)) {
            throw new java.lang.IllegalArgumentException();
        }
        if (points.length >= 4) {
            arr = points.clone();
            double[] slopesToP = new double[points.length];

            for (int i = 0; i < points.length; i++) {
                max = 1;
                count = 1;
                collinear = new ArrayList<Point>();
                p = points[i];
                Arrays.sort(arr, p.slopeOrder());
                if (checkRepeated(arr)) {
                    throw new java.lang.IllegalArgumentException();
                }
                for (int j = 0; j < slopesToP.length; j++) {
                    slopesToP[j] = p.slopeTo(arr[j]);
                }
                collinear.add(arr[0]);
                currentSlope = slopesToP[1];
                for (int k = 1; k < slopesToP.length - 1; k++) {
                    if (slopesToP[k] == slopesToP[k + 1]) {
                        count++;
                        if (count == 3) {
                            collinearSlope = currentSlope;
                        }
                        if (max < count) {
                            max = count;
                        }
                    } else {
                        count = 1;
                        currentSlope = slopesToP[k + 1];
                    }
                }
                if (max >= 3) {
                    for (int l = 1; l < slopesToP.length; l++) {
                        if (slopesToP[l] == collinearSlope) {
                            collinear.add(arr[l]);
                        }
                    }
                    Collections.sort(collinear);
//                LineSegment currentSegment = new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1));
                    segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));

                }
            }
            for (int i = 0; i < segments.size(); i++) {
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
//        // creates input array using input6.txt values provided in the course
//        Point[] input6 = { new Point(19000,10000),
//                           new Point(18000,10000),
//                           new Point(32000,10000),
//                           new Point(21000,10000),
//                           new Point(1234,5678),
//                           new Point(14000,10000),
//        };
//
//        FastCollinearPoints fcp6 = new FastCollinearPoints(input6);

//        // creates input array using input6.txt values provided in the course
//        Point[] input8 = { new Point(10000,0),
//                new Point(0,10000),
//                new Point(3000,7000),
//                new Point(7000,3000),
//                new Point(20000,21000),
//                new Point(3000,4000),
//                new Point(14000,15000),
//                new Point(6000,7000),
//        };
//
//        FastCollinearPoints fcp8 = new FastCollinearPoints(input8);

//        creates input array using input48.xtx values provided in the course
//        Point[] input48 = {new Point(26000, 27000),
//                           new Point(24000, 23000),
//                           new Point(18000, 23000),
//                           new Point(22000, 9000),
//                           new Point(25000, 25000),
//                           new Point(1000, 2000),
//                           new Point(12000, 10000),
//                           new Point(22000, 17000),
//                           new Point(25000, 1000),
//                           new Point(15000, 1000),
//                           new Point(19000, 28000),
//                           new Point(12000, 3000),
//                           new Point(4000, 15000),
//                           new Point(2000, 7000),
//                           new Point(18000, 27000),
//                           new Point(9000, 26000),
//                           new Point(11000, 26000),
//                           new Point(6000, 16000),
//                           new Point(18000, 26000),
//                           new Point(24000, 30000),
//                           new Point(10000, 25000),
//                           new Point(7000, 10000),
//                           new Point(19000, 24000),
//                           new Point(6000, 0),
//                           new Point(26000, 15000),
//                           new Point(1000, 23000),
//                           new Point(23000, 29000),
//                           new Point(15000, 7000),
//                           new Point(15000, 19000),
//                           new Point(17000, 31000),
//                           new Point(6000, 2000),
//                           new Point(17000, 16000),
//                           new Point(1000, 26000),
//                           new Point(11000, 19000),
//                           new Point(25000, 0),
//                           new Point(17000, 30000),
//                           new Point(16000, 22000),
//                           new Point(18000, 13000),
//                           new Point(3000, 23000),
//                           new Point(10000, 13000),
//                           new Point(1000, 9000),
//                           new Point(11000, 21000),
//                           new Point(29000, 19000),
//                           new Point(9000, 29000),
//                           new Point(30000, 3000),
//                           new Point(9000, 1000),
//                           new Point(5000, 29000),
//                           new Point(26000, 6000)
//        };

//        FastCollinearPoints fcp48 = new FastCollinearPoints(input48);
//        for (LineSegment ls: fcp48.segments()) {
//            System.out.println(ls.toString());
//        }
//
//        System.out.println("----------");
//        for (LineSegment ls: fcp6.segments()) {
//            System.out.println(ls.toString());
//        }
//
//        System.out.println("----------");
//        for (LineSegment ls: fcp8.segments()) {
//            System.out.println(ls.toString());
//        }
    }
}
