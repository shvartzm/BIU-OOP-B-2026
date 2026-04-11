import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;

public class AbstractArtDrawing {
    private Line[] lines = new Line[10];
    private Point[] intersectiPoints;
    public void drawRandomLines(){
        Random rnd = new Random();
        GUI gui = new GUI("run-gui-example", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        for(int i = 0; i < 10; i++){
            int x1 = rnd.nextInt(800) +1 ;
            int x2 = rnd.nextInt(800) +1 ;
            int y1 = rnd.nextInt(600) +1 ;
            int y2 = rnd.nextInt(600) +1 ;
            Line l1 = new Line(new Point(x1, y1), new Point(x2, y2));
            lines[i] = l1;
            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
        }
        gui.show(d);
    }
    public static void main(String[] args) {
     AbstractArtDrawing example = new AbstractArtDrawing();
     example.drawRandomLines();
  }
}
