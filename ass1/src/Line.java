public class Line {
   private Point start;
   private Point end;
   public static final double EPSILON = 0.00001;

   // constructors
   public Line(Point start, Point end) { 
     this.start = new Point(start.getX(), start.getY());
     this.end = new Point(end.getX(), end.getY());
   }
   public Line(double x1, double y1, double x2, double y2) { 
    this.start = new Point(x1,y1);
    this.end = new Point(x2,y2);
   }
   
   // Return the length of the line
   public double length() {
        return this.start.distance(this.end);
    }

   // Returns the middle point of the line
   public Point middle() {
      double midx = (this.start.getX() + this.end.getX()) / 2;
      double midy = (this.start.getY() + this.end.getY()) / 2;
      return new Point(midx, midy);
    }
   
   // Returns the start point of the line
   public Point start() {
       return new Point(this.start.getX(), this.start.getY());
   }
   
   // Returns the end point of the line
   public Point end() { 
      return new Point(this.end.getX(), this.end.getY());
   }
   // check if line is vertical
   private boolean isVertical(){
     return this.start.getX() - this.end.getX() == 0;
   }
   // find and returns slope of the line
   private double slope(){
    return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
   }
   private double yintercept(){
        return this.start.getY() - (slope() * this.start.getX());
   }
   private boolean isPointOnLine(double x, double y){
     return x >= (Math.min(this.start.getX(), this.end.getX()) - EPSILON) &&
      y >= (Math.min(this.start.getY(), this.end.getY()) - EPSILON) &&
      x <= (Math.max(this.start.getX(), this.end.getX()) + EPSILON) &&
      y <= (Math.max(this.start.getY(), this.end.getY()) + EPSILON);
   }
   // Returns true if the lines intersect, false otherwise
   public boolean isIntersecting(Line other) {
    Point interPoint = this.intersectionWith(other);
    if (interPoint != null){
        return true;
    }
    else{
       if (this.isVertical() && other.isVertical()){
        return this.isPointOnLine(other.start.getX(), other.start.getY()) || this.isPointOnLine(other.end.getX(), other.end.getY()) ||
        other.isPointOnLine(this.start.getX(), this.start.getY()) || other.isPointOnLine(this.end.getX(), this.end.getY()) ;
       }else{
        if (Math.abs(this.slope() - other.slope()) < EPSILON && Math.abs(this.yintercept() - other.yintercept()) < EPSILON){
            return this.isPointOnLine(other.start.getX(), other.start.getY()) || this.isPointOnLine(other.end.getX(), other.end.getY()) ||
            other.isPointOnLine(this.start.getX(), this.start.getY()) || other.isPointOnLine(this.end.getX(), this.end.getY()) ;
        }
       }
    }
    return false;
    }

   // Returns true if these 2 lines intersect with this line, false otherwise
   public boolean isIntersecting(Line other1, Line other2) { 
       return this.isIntersecting(other1) && this.isIntersecting(other2);
   }
  
   // Returns the intersection point if the lines intersect,
   // and null otherwise.
   public Point intersectionWith(Line other) { 
      if (this.isVertical() && other.isVertical()){
        return null;
      }
      if(this.isVertical()){
        double y1 = other.slope() * this.start.getX() + other.yintercept();
        if (this.isPointOnLine(this.start.getX(), y1) && other.isPointOnLine(this.start.getX(), y1)){
           return new Point(this.start.getX(), y1);
        }else {
            return null;
        }
      }else if(other.isVertical()){
        double y2 = this.slope() * other.start.getX() + this.yintercept();
        if (this.isPointOnLine(other.start.getX(), y2) && other.isPointOnLine(other.start.getX(), y2)){
           return new Point(other.start.getX(), y2);
        }else {
            return null;
        }
      }
      if (Math.abs(this.slope() - other.slope()) < EPSILON){
        return null;
      }else{
         double xint = (other.yintercept() - this.yintercept()) / (this.slope() - other.slope());
         double yint = this.slope() * xint + this.yintercept();
         if (this.isPointOnLine(xint,yint) && other.isPointOnLine(xint, yint)){
            return new Point(xint, yint);
         }else {
            return null;
         }
      }
   }

   // equals -- return true if the lines are equal, false otherwise
   public boolean equals(Line other) { 
       return (this.start.equals(other.start) && this.end.equals(other.end)) || (this.start.equals(other.end) && this.end.equals(other.start));
   }
}
 