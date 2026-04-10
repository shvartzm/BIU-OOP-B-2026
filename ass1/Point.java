public class Point {
   private double x;
   private double y;
   public static final double EPSILON = 0.00001;

   // constructor
   public Point(double x, double y) { 
     this.x = x;
     this.y = y;
   }
 
   // distance -- return the distance of this point to the other point
   public double distance(Point other) { 
          return Math.sqrt( ( (other.getX() - this.x) * (other.getX() - this.x)) + ((other.y - this.y) * (other.y - this.y) ) );
   }
   
   // equals -- return true is the points are equal, false otherwise
   public boolean equals(Point other) { 
     return Math.abs(this.x - other.x) < EPSILON && Math.abs(this.y - other.y) < EPSILON;
   }

   // Return the x and y values of this point
   public double getX() {
       return this.x;
    }
   public double getY() { 
       return this.y;
   }
}

