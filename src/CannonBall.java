import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;


public class CannonBall extends GameObject{
    private double theta, speedX, speedY, size;
    private boolean ballCounted;

    public CannonBall(double x, double y, double size, double theta) {
        this.theta = theta;
        double speed = 400;
        // Need seperate x and y velocities for reflection
        this.speedX = speed*Math.cos(theta);
        this.speedY = speed*Math.sin(theta);
        // Support for dynamic barrel length resize
        this.size = size;
        this.ballCounted = false;
        super(x-size/2, y-size/2);
    }
    

    public double getSize(){
        return this.size;
    }
    
    public double getSpeedX(){
        return this.speedX;
    }

    public double getSpeedY(){
        return this.speedY;
    }
    
    public double getTheta(){
        return this.theta;
    }
    
    public boolean getBallCounted(){
        return this.ballCounted;
    }
    
    public void setSpeedX(double newSpeedX){
        this.speedX = newSpeedX;
    }

    public void setSpeedY(double newSpeedY){
        this.speedY = newSpeedY;
    }
    
    public void setTheta(double newTheta){
        this.theta = newTheta;
    }
    
    public void setBallCounted(){
        this.ballCounted = true;
    }

    
    public void render(GraphicsContext gc){
        
    	gc.setFill(new LinearGradient(
    		    0, 0,   // start at top
    		    0, 1,   // end at bottom
    		    true,   // proportional coordinates (0–1)
    		    CycleMethod.NO_CYCLE,
    		    new Stop(0, Color.SILVER),  // top color
    		    new Stop(1, Color.SLATEGREY)       // bottom color
    		));
        gc.fillOval(getX(), getY(), this.size, this.size);
    }
    

    public void handle(long now){
        
        // Only call getElapsed once per frame!
        double dt = getElapsed(now);
        // x2 = x1+(speed*time*cos(theta))
        this.setX(getX()+(dt*this.speedX));
        // y2 = y1+(speed*time*sin(theta))
        this.setY(getY()+(dt*this.speedY));

    }
}
