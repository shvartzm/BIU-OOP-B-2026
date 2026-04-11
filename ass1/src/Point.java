/**
 * A class that represents a mathematical point in 2D space.
 * It stores x and y coordinates and provides methods for measuring distance
 * and checking coordinate equality.
 */
public class Point {
  private double x;
  private double y;
  public static final double EPSILON = 0.00001;

  /**
   * Constructs a new Point object using the specified coordinates.
   *
   * @param x the starting x coordinate
   * @param y the starting y coordinate
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * uses the x and y coordinates from the two points and uses the distance.
   * formula
   * d = sqrt((x1-x2)^2 + (y1-y2)^2)
   *
   * @param other point which is given to calculate distance to
   * @return the calculated distance
   */
  public double distance(Point other) {
  return Math.sqrt(((other.getX() - this.x) * (other.getX() - this.x))
          + ((other.getY() - this.y) * (other.getY() - this.y)));
  }

  /**
   * Checks if this point is equal to another point by comparing their x and y
   * coordinates.
   * This method uses a small threshold (EPSILON) to safely handle floating-point
   * precision errors.
   *
   * @param other the other Point object to compare against this point
   * @return true if the points are mathematically equal within the threshold,
   *         false otherwise
   */
  public boolean equals(Point other) {
    return Math.abs(this.x - other.x) < EPSILON && Math.abs(this.y - other.y) < EPSILON;
  }

  /**
   * @return the x coordinate of this point
   */
  public double getX() {
    return this.x;
  }

  /**
   * @return the y coordinate of this point
   */
  public double getY() {
    return this.y;
  }
}
