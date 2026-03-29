import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

public class Cannon extends GameObject{
    private double[] barrelX, barrelY;

    public Cannon(double canvasHeight) {
        double y = canvasHeight/2;
        double x = 0;
        this.barrelX = new double[]{0, 0, 120, 120};
        this.barrelY = new double[]{y-10, y+10, y+10, y-10};
        super(x, y);
    }
    
    
    public void render(GraphicsContext gc){
        
        // Draw the barrel
        gc.setFill(Paint.valueOf("GREY"));
        gc.fillPolygon(barrelX, barrelY, 4);
        // Draw the base
        gc.setFill(Paint.valueOf("BROWN"));
        gc.fillArc(getX()-30, getY()-30, 60.0, 60.0, 90, -180.0, ArcType.valueOf("OPEN"));
    }

    public void aimCannon(double x, double y){

        // Draw a line from the base of the cannon
        // to the location of the mouse click.
        double a = getX();
        double b = getY();
        double theta = 0;   // tan^-1 (y-b)/(x-a)
        double alpha = 0;   // 120*cos(theta) 
        double beta = 0;    // 120*sin(theta)
        // This gets me a line from the base of the cannon
        // to the middle of the end of the cannon.
        // Still need to offset.
        
        // Rotate the barrel around the base of the 
        // cannon to align with the line.
    }

    public void handle(long now){
        
    }
}
