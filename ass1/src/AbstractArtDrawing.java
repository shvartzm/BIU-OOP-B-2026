
// ID: 216763235
import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A graphical program that generates random line segments on a canvas.
 * It visually highlights the midpoints of the lines in blue, the intersection
 * points in red, and the triangles formed by intersecting lines in green.
 */
public class AbstractArtDrawing {
    private Line[] lines = new Line[10];
    private Point[] intersectiPoints;

    /**
     * Generates an array of random line segments, draws them on a GUI window,
     * and invokes helper methods to paint their midpoints, intersections, and
     * triangles.
     */
    public void drawRandomLines() {
        Random rnd = new Random();
        GUI gui = new GUI("run-gui-example", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; i++) {
            int x1 = rnd.nextInt(800) + 1;
            int x2 = rnd.nextInt(800) + 1;
            int y1 = rnd.nextInt(600) + 1;
            int y2 = rnd.nextInt(600) + 1;
            Line l1 = new Line(new Point(x1, y1), new Point(x2, y2));
            this.lines[i] = l1;
            paintLine(d, x1, y1, x2, y2, Color.BLACK);
            Point middlePoint = l1.middle();
            paintCircle(d, middlePoint.getX(), middlePoint.getY(), 3, Color.BLUE);
        }
        paintIntersectionPoints(d);
        paintTriangles(d);
        gui.show(d);
    }

    /**
     * Helper method to calculate and draw all unique intersection points between
     * the generated lines.
     * Iterates through all possible line pairs and paints a red circle at each
     * valid intersection.
     *
     * @param d the DrawSurface on which to paint the intersection points
     */
    private void paintIntersectionPoints(DrawSurface d) {
        for (int i = 0; i <= lines.length - 2; i++) {
            for (int j = i + 1; j <= lines.length - 1; j++) {
                Point p1 = lines[i].intersectionWith(lines[j]);
                if (p1 != null) {
                    paintCircle(d, p1.getX(), p1.getY(), 3, Color.RED);
                }
            }
        }
    }

    /**
     * Helper method to identify and highlight triangles formed by intersecting
     * lines.
     * Checks all triplets of lines, and if they intersect to form a closed
     * triangle,
     * paints the connecting line segments green.
     *
     * @param d the DrawSurface on which to paint the triangles
     */
    private void paintTriangles(DrawSurface d) {
        for (int i = 0; i <= lines.length - 3; i++) {
            for (int j = i + 1; j <= lines.length - 2; j++) {
                for (int k = j + 1; k < lines.length; k++) {
                    Point p1 = lines[i].intersectionWith(lines[j]);
                    Point p2 = lines[i].intersectionWith(lines[k]);
                    Point p3 = lines[j].intersectionWith(lines[k]);
                    if (p1 != null && p2 != null && p3 != null) {
                        paintLine(d, p1.getX(), p1.getY(), p2.getX(), p2.getY(), Color.GREEN);
                        paintLine(d, p1.getX(), p1.getY(), p3.getX(), p3.getY(), Color.GREEN);
                        paintLine(d, p3.getX(), p3.getY(), p2.getX(), p2.getY(), Color.GREEN);
                    }

                }
            }
        }
    }

    /**
     * Helper method to draw a filled circle of a specified color on the canvas.
     * Automatically handles the type casting from double mathematical coordinates
     * to integer pixels.
     *
     * @param d        the DrawSurface to paint on
     * @param x        the mathematical x-coordinate of the circle's center
     * @param y        the mathematical y-coordinate of the circle's center
     * @param r        the radius of the circle in pixels
     * @param argColor the color to fill the circle with
     */
    private void paintCircle(DrawSurface d, double x, double y, int r, Color argColor) {
        d.setColor(argColor);
        d.fillCircle((int) x, (int) y, r);
    }

    /**
     * Helper method to draw a line segment of a specified color on the canvas.
     * Automatically handles the type casting from double mathematical coordinates
     * to integer pixels.
     *
     * @param d        the DrawSurface to paint on
     * @param x1       the starting x-coordinate
     * @param y1       the starting y-coordinate
     * @param x2       the ending x-coordinate
     * @param y2       the ending y-coordinate
     * @param argColor the color of the line segment
     */
    private void paintLine(DrawSurface d, double x1, double y1, double x2, double y2, Color argColor) {
        d.setColor(argColor);
        d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    /**
     * The entry point of the program.
     * Creates an instance of AbstractArtDrawing and triggers the random drawing
     * sequence.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
