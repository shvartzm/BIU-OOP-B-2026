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
     SimpleGuiExample example = new SimpleGuiExample();
     example.drawRandomCircles();
  }




    //     public void drawRandomCircles() {
//     Random rand = new Random(); // create a random-number generator
//     // Create a window with the title "Random Circles Example"
//     // which is 400 pixels wide and 300 pixels high. 
//     GUI gui = new GUI("Random Circles Example", 400, 300);
//     DrawSurface d = gui.getDrawSurface();
//     for (int i = 0; i < 10; ++i) {
//        int x = rand.nextInt(400) + 1; // get integer in range 1-400
//        int y = rand.nextInt(300) + 1; // get integer in range 1-300
//        int r = 5*(rand.nextInt(4) + 1); // get integer in 5,10,15,20
//        d.setColor(Color.RED);
//        d.fillCircle(x,y,r);
//     }   
//     gui.show(d);
//   }
}
