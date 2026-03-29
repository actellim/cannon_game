import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

public class Cannon extends GameObject{
    private double[] barrelX, barrelY;
    private double currentTheta;
    private double nextTheta;

    public Cannon(double canvasHeight) {
        double y = canvasHeight/2;
        double x = 0;
        this.barrelX = new double[]{0, 0, 120, 120};
        this.barrelY = new double[]{y-10, y+10, y+10, y-10};
        this.currentTheta = 0;
        this.nextTheta = 0;
        super(x, y);
    }
    
    
    public void render(GraphicsContext gc){
        
        // Draw the barrel
        gc.setFill(Paint.valueOf("GREY"));
        gc.fillPolygon(barrelX, barrelY, 4);
        // Draw the base
        gc.setFill(Paint.valueOf("BROWN"));
        // Magic numbers because it doesn't move.
        gc.fillArc(getX()-30, getY()-30, 60.0, 60.0, 90, -180.0, ArcType.valueOf("OPEN"));
    }

    public void calcNewTheta(double x, double y){

        // Draw a line from the base of the cannon
        // to the location of the mouse click.
        double a = getX();
        double b = getY();
        this.nextTheta = Math.atan2((y-b), (x-a));   // tan^-1 (y-b)/(x-a)
    }
    
    public void updateBarrelPoints(double theta){
        
        double a = getX();
        double b = getY();
        // Add base coords for relative positioning.
        double alpha = a + 120*Math.cos(theta);       // a + 120*cos(theta)
        double beta = b + 120*Math.sin(theta);        // b + 120*sin(theta)
        double phi = Math.PI/2 - theta;         // 90 - theta
        // This gets me a line from the base of the cannon
        // to the middle of the end of the cannon.
        // Still need to offset.
        // c = a - 10*cos(phi)
        double c = a - 10*Math.cos(phi);
        // d = b + 10*sin(phi)
        double d = b + 10*Math.sin(phi);
        // e = a + 10*cos(phi)
        double e = a + 10*Math.cos(phi);
        // f = b - 10*sin(phi)
        double f = b - 10*Math.sin(phi);
        // g = alpha - 10*cos(phi)
        double g = alpha - 10*Math.cos(phi);
        // h = beta + 10*sin(phi)
        double h = beta + 10*Math.sin(phi);
        // i = alpha + 10*cos(phi)
        double i = alpha + 10*Math.cos(phi);
        // j = beta - 10*sin(phi)
        double j = beta - 10*Math.sin(phi);
        // Points: (c, d), (e, f), (i, j), (g, h)
        // barrelX: [c, e, i, g] 
        barrelX[0] = c;
        barrelX[1] = e;
        barrelX[2] = i;
        barrelX[3] = g;
        // barrelY: [d, f, j, h]
        barrelY[0] = d;
        barrelY[1] = f;
        barrelY[2] = j;
        barrelY[3] = h;
    }

    public void handle(long now){
        
        // pass the new angle to update the cannon position
        // Barrel Velocity in degrees/s.
        double barrelVelocity = Math.PI/4;                                 
        // Calc the degrees to rotate.
        double adjustThetaBy = getElapsed(now) * barrelVelocity;  
        if (currentTheta > nextTheta){
            currentTheta = currentTheta - adjustThetaBy;
            if (currentTheta < nextTheta){
                currentTheta = nextTheta; // we got there, snap to position
            }
        }
        if (currentTheta < nextTheta){
            currentTheta = currentTheta + adjustThetaBy;
            if (currentTheta > nextTheta) // don't overshoot!
                currentTheta = nextTheta;
        }
        updateBarrelPoints(currentTheta);
        if (currentTheta == nextTheta){
            // fire the cannon!
        }
    }
}
