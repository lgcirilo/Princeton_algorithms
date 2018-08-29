/**
 * Created by cyfa on 28/08/2018.
 */

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        double slope;
        if ((this.x == that.x) && (this.y == that.y)) {
            return Double.NEGATIVE_INFINITY;
        }

        if (this.y == that.y) {
            return +0.0;
        }

        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        slope = (double)(that.y - this.y) / (that.x - this.x);
        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {

        if ((this.x == that.x) && (this.y == that.y)) {
            return 0;
        }
        if (this.y < that.y || (this.y == that.y && this.x < that.x) ) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeComparator();
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class SlopeComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            int result;
            double slopeToA = slopeTo(a);
            double slopeToB = slopeTo(b);
            if (slopeToA - slopeToB < 0) {
                result = -1;
            } else {
                if (slopeToA - slopeToB > 0) {
                    result = 1;
                } else {
                    result = 0;
                }
            }
            return result;
        }
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point a = new Point(-1,3);
        Point b = new Point(1,3);
        Point c = new Point(2,2);
        Point d = new Point(-2,2);
        Point e = new Point(-1,1);
        Point f = new Point(1,1);
        Point g = new Point(-2,-2);
        Point h = new Point(2,-2);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        points.add(e);
        points.add(f);
        points.add(g);
        points.add(h);

        // tests comparator
        // can't call slopeOrder directly. Need to call it from within a point object.
        Collections.sort(points, points.get(0).slopeOrder());
        for (Point p: points) {
            System.out.println(p);
        }

        // tests slopeTo() method
        // horizontal line segments
        System.out.println("should return 0.0\n---------------------");
        System.out.println(a.slopeTo(b)); // should return +0.0
        System.out.println(b.slopeTo(a)); // should return +0.0
        System.out.println(c.slopeTo(d)); // should return +0.0
        System.out.println(e.slopeTo(f)); // should return +0.0
        System.out.println(f.slopeTo(e)); // should return +0.0
        System.out.println(g.slopeTo(h)); // should return +0.0
        System.out.println(h.slopeTo(g)); // should return +0.0

        //vertical line segments
        System.out.println("should return Double.POSITIVE_INFINITY\n--------------------------------");
        System.out.println(d.slopeTo(g)); // should return Double.POSITIVE_INFINITY
        System.out.println(g.slopeTo(d)); // should return Double.POSITIVE_INFINITY
        System.out.println(a.slopeTo(e)); // should return Double.POSITIVE_INFINITY
        System.out.println(e.slopeTo(a)); // should return Double.POSITIVE_INFINITY
        System.out.println(b.slopeTo(f)); // should return Double.POSITIVE_INFINITY
        System.out.println(f.slopeTo(b)); // should return Double.POSITIVE_INFINITY
        System.out.println(c.slopeTo(h)); // should return Double.POSITIVE_INFINITY
        System.out.println(h.slopeTo(c)); // should return Double.POSITIVE_INFINITY

        // equal points
        System.out.println("should return Double.NEGATIVE_INFINITY\n-----------------------------------");
        System.out.println(a.slopeTo(a)); // should return Double.NEGATIVE_INFINITY
        System.out.println(b.slopeTo(b)); // should return Double.NEGATIVE_INFINITY
        System.out.println(c.slopeTo(c)); // should return Double.NEGATIVE_INFINITY
        System.out.println(d.slopeTo(d)); // should return Double.NEGATIVE_INFINITY
        System.out.println(e.slopeTo(e)); // should return Double.NEGATIVE_INFINITY
        System.out.println(f.slopeTo(f)); // should return Double.NEGATIVE_INFINITY
        System.out.println(g.slopeTo(g)); // should return Double.NEGATIVE_INFINITY
        System.out.println(h.slopeTo(h)); // should return Double.NEGATIVE_INFINITY

        // non zero and non infinity slopes
        System.out.println("should return a double\n----------------------");
        System.out.println(d.slopeTo(b));
        System.out.println(b.slopeTo(d));
        System.out.println(e.slopeTo(b));
        System.out.println(b.slopeTo(e));
        System.out.println(e.slopeTo(c));
        System.out.println(c.slopeTo(e));
        System.out.println(a.slopeTo(h));
        System.out.println(h.slopeTo(a));

        // tests compareTo method
        // equal points
        System.out.println("should return 0\n---------------");
        System.out.println(a.compareTo(a));
        System.out.println(b.compareTo(b));
        System.out.println(c.compareTo(c));
        System.out.println(d.compareTo(d));
        System.out.println(e.compareTo(e));
        System.out.println(f.compareTo(f));
        System.out.println(g.compareTo(g));
        System.out.println(h.compareTo(h));

        // first point greater than second point
        System.out.println("should return 1\n-------------------");
        System.out.println(a.compareTo(d));
        System.out.println(c.compareTo(h));
        System.out.println(b.compareTo(f));
        System.out.println(e.compareTo(g));
        System.out.println(b.compareTo(g));
        System.out.println(d.compareTo(g));
        System.out.println(a.compareTo(e));
        System.out.println(b.compareTo(f));
        System.out.println(c.compareTo(h));

        //first point less than second point
        System.out.println("should return -1\n------------------");
        System.out.println(d.compareTo(a));
        System.out.println(h.compareTo(c));
        System.out.println(f.compareTo(b));
        System.out.println(g.compareTo(e));
        System.out.println(g.compareTo(d));
        System.out.println(e.compareTo(a));
        System.out.println(f.compareTo(b));
        System.out.println(h.compareTo(c));
    }
}

