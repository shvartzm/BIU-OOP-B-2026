/**
 * A class that represents a mathematical line semgant in 2D space.
 * It stores start and end point and provides methods for measuring length,
 * middle point
 * and comparsions between line segmants
 */
public class Line {
   private Point start;
   private Point end;
   public static final double EPSILON = 0.00001;

   /**
    * Constructs a new Line object using the specified start and end points.
    *
    * @param start point of the line segmant
    * @param end   point of the line segamant
    */
   public Line(Point start, Point end) {
      this.start = new Point(start.getX(), start.getY());
      this.end = new Point(end.getX(), end.getY());
   }

   /**
    * Constructs a new line object using the coordinates of the ends of the line.
    * segmant
    *
    * @param x1 is the x- coordinate of the first end
    * @param y1 is the y- coordinate of the first end
    * @param x2 is the x- coordinate of the second end
    * @param y2 is the y- coordinate of the second end
    */
   public Line(double x1, double y1, double x2, double y2) {
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
   }

   /**
    * Calculates distance between the start and end point.
    *
    * @return the calculated distance
    */
   public double length() {
      return this.start.distance(this.end);
   }

   /**
    * Calculates the middle point by comparing the average of the x and y.
    * coordinates
    *
    * @return the middle point object
    */
   public Point middle() {
      double midx = (this.start.getX() + this.end.getX()) / 2;
      double midy = (this.start.getY() + this.end.getY()) / 2;
      return new Point(midx, midy);
   }

   /**
    * @return start point object of the line segmant
    */
   public Point start() {
      return new Point(this.start.getX(), this.start.getY());
   }

   /**
    * @return end point object of the line segmant
    */
   public Point end() {
      return new Point(this.end.getX(), this.end.getY());
   }

   /**
    * helper method to check if line is vertical by checking if x change is 0.
    *
    * @return true if vertical, otherwise false
    */
   private boolean isVertical() {
      return this.start.getX() - this.end.getX() == 0;
   }

   /**
    * helper method to calculate the "infinite" line slope of the line segmant.
    * using the slope formula
    *
    * @return the value of the slope
    */
   private double slope() {
      return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
   }

   /***
    * helper method to calculate the y-intercept of the "infinite" line.
    * representive of the line segmant using the formula
    *
    * @return the y intercept of the line
    */
   private double yintercept() {
      return this.start.getY() - (slope() * this.start.getX());
   }

   /**
    * helper method to check if a certain point sits on a line.
    *
    * @param x coordinate of the point
    * @param y coordinate of the point
    * @return true if the point is on the line, otherwise false
    */
   private boolean isPointOnLine(double x, double y) {
      return x >= (Math.min(this.start.getX(), this.end.getX()) - EPSILON)
             && y >= (Math.min(this.start.getY(), this.end.getY()) - EPSILON)
             && x <= (Math.max(this.start.getX(), this.end.getX()) + EPSILON)
             && y <= (Math.max(this.start.getY(), this.end.getY()) + EPSILON);
   }

   /**
    * Checks if this line segment intersects with another line segment.
    * This method handles standard intersections, as well as edge cases such as
    * vertical lines and collinear overlapping segments using an EPSILON threshold.
    *
    * @param other the other Line object to check for an intersection with
    * @return true if the two line segments intersect or overlap, false otherwise
    */
   public boolean isIntersecting(Line other) {
      Point interPoint = this.intersectionWith(other);
      if (interPoint != null) {
         return true;
      } else {
         if (this.isVertical() && other.isVertical()) {
            return this.isPointOnLine(other.start.getX(), other.start.getY())
                  || this.isPointOnLine(other.end.getX(), other.end.getY())
                  || other.isPointOnLine(this.start.getX(), this.start.getY())
                  || other.isPointOnLine(this.end.getX(), this.end.getY());
         } else {
            if (Math.abs(this.slope() - other.slope()) < EPSILON &&
                  Math.abs(this.yintercept() - other.yintercept()) < EPSILON) {
               return this.isPointOnLine(other.start.getX(), other.start.getY())
                     || this.isPointOnLine(other.end.getX(), other.end.getY())
                     || other.isPointOnLine(this.start.getX(), this.start.getY())
                     || other.isPointOnLine(this.end.getX(), this.end.getY());
            }
         }
      }
      return false;
   }

   /**
    * Checks wether a line is intersecting with two other lines.
    *
    * @param other1 the first line to compare to
    * @param other2 the second line to compare to
    * @return True if intersecting with both, False if not
    */
   public boolean isIntersecting(Line other1, Line other2) {
      return this.isIntersecting(other1) && this.isIntersecting(other2);
   }

   /**
    * Calculates the intersection point between this line segment and another line
    * segment.
    * This method safely handles edge cases such as vertical lines (undefined
    * slopes) and
    * parallel lines. It also verifies that the calculated mathematical
    * intersection point
    * actually lies within the bounds of both line segments.
    *
    * @param other the other Line segment to check for an intersection with
    * @return a new Point representing the exact intersection coordinate, or null
    *         if the lines
    *         do not intersect, are strictly parallel, or overlap in a way that
    *         yields infinite points
    */
   public Point intersectionWith(Line other) {
      if (this.isVertical() && other.isVertical()) {
         return null;
      }
      if (this.isVertical()) {
         double y1 = other.slope() * this.start.getX() + other.yintercept();
         if (this.isPointOnLine(this.start.getX(), y1) && other.isPointOnLine(this.start.getX(), y1)) {
            return new Point(this.start.getX(), y1);
         } else {
            return null;
         }
      } else if (other.isVertical()) {
         double y2 = this.slope() * other.start.getX() + this.yintercept();
         if (this.isPointOnLine(other.start.getX(), y2) && other.isPointOnLine(other.start.getX(), y2)) {
            return new Point(other.start.getX(), y2);
         } else {
            return null;
         }
      }
      if (Math.abs(this.slope() - other.slope()) < EPSILON) {
         return null;
      } else {
         double xint = (other.yintercept() - this.yintercept()) / (this.slope() - other.slope());
         double yint = this.slope() * xint + this.yintercept();
         if (this.isPointOnLine(xint, yint) && other.isPointOnLine(xint, yint)) {
            return new Point(xint, yint);
         } else {
            return null;
         }
      }
   }

   /**
    * Checks if this line segment is equal to another line segment.
    * Two lines are considered equal if they connect the exact same two points.
    * This method treats lines as undirected geometric segments, meaning the order
    * of the points does not matter (a line from A to B is equal to a line from B
    * to A).
    *
    * @param other the other Line object to compare against this line
    * @return true if the lines represent the exact same geometric segment, false
    *         otherwise
    */
   public boolean equals(Line other) {
      return (this.start.equals(other.start) && this.end.equals(other.end))
            || (this.start.equals(other.end) && this.end.equals(other.start));
   }
}
