import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

public class Cannon extends GameObject{
    private double[] barrelX, barrelY;
    private double currentTheta, nextTheta, tipX, tipY, 
        barrelLength, barrelWidth, barrelVelocity;
    private GameListener listener;
    private boolean cannonFired;

    public Cannon(double canvasHeight, GameListener listener) {
        double y = canvasHeight/2;
        double x = 0;
        double barrelLength = 100;
        double barrelWidth = 30;
        this.barrelLength = barrelLength;
        this.barrelWidth = barrelWidth;
        this.barrelVelocity = Math.PI * 2;     // radians/s.
        this.tipX = barrelLength;
        this.tipY = y;
        this.barrelX = new double[]{0, 0, barrelLength, barrelLength};
        this.barrelY = new double[]{y-(barrelWidth/2), y+(barrelWidth/2), 
            y+(barrelWidth/2), y-(barrelWidth/2)};
        this.currentTheta = 0;
        this.nextTheta = 0;
        // State variable
        this.cannonFired = true;
        this.listener = listener;
        super(x, y);
    }
    
    
    public void calcNewTheta(double x, double y){

        this.cannonFired = false;
        // Draw a line from the base of the cannon
        // to the location of the mouse click.
        double a = getX();
        double b = getY();
        // src: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Math.html
        // I learned this here!
        this.nextTheta = Math.atan2((y-b), (x-a));   // tan^-1 (y-b)/(x-a)
    }
    
    public void updateBarrelPoints(double theta){
        
        double a = getX();
        double b = getY();
        // Add base coords for relative positioning.
        this.tipX = a + this.barrelLength*Math.cos(theta);       // a + 120*cos(theta)
        this.tipY = b + this.barrelLength*Math.sin(theta);        // b + 120*sin(theta)
        double phi = Math.PI/2 - theta;         // 90 - theta
        // This gets me a line from the base of the cannon
        // to the middle of the end of the cannon.
        // Still need to offset.
        // c = a - 10*cos(phi)
        double c = a - this.barrelWidth/2*Math.cos(phi);
        // d = b + 10*sin(phi)
        double d = b + this.barrelWidth/2*Math.sin(phi);
        // e = a + 10*cos(phi)
        double e = a + this.barrelWidth/2*Math.cos(phi);
        // f = b - 10*sin(phi)
        double f = b - this.barrelWidth/2*Math.sin(phi);
        // g = alpha - 10*cos(phi)
        double g = this.tipX - this.barrelWidth/2*Math.cos(phi);
        // h = beta + 10*sin(phi)
        double h = this.tipY + this.barrelWidth/2*Math.sin(phi);
        // i = alpha + 10*cos(phi)
        double i = this.tipX + this.barrelWidth/2*Math.cos(phi);
        // j = beta - 10*sin(phi)
        double j = this.tipY - this.barrelWidth/2*Math.sin(phi);
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
    

    public void render(GraphicsContext gc){
        
        // Draw the barrel
        gc.setFill(Paint.valueOf("GREY"));
        gc.fillPolygon(barrelX, barrelY, 4);
        // Draw the base
        gc.setFill(Paint.valueOf("BROWN"));
        // Magic numbers because it doesn't move.
        gc.fillArc(getX()-30, getY()-30, 60.0, 60.0, 90, -180.0, ArcType.valueOf("OPEN"));
    }


    @Override
    public void handle(long now){
        
        // pass the new angle to update the cannon position
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
        if (currentTheta == nextTheta && cannonFired == false){
            // fire the cannon!
            listener.fireCannon(this.tipX, this.tipY, this.barrelWidth, this.currentTheta);
            this.cannonFired = true;
        }
    }
}
