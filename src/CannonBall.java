import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class CannonBall extends GameObject{
    private double theta, speedX, speedY, size;

    public CannonBall(double x, double y, double size, double theta) {
        this.theta = theta;
        double speed = 400;
        // Need seperate x and y velocities for reflection
        this.speedX = speed*Math.cos(theta);
        this.speedY = speed*Math.sin(theta);
        // Support for dynamic barrel length resize
        this.size = size;
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
    
    public void setSpeedX(double newSpeedX){
        this.speedX = newSpeedX;
    }

    public void setSpeedY(double newSpeedY){
        this.speedY = newSpeedY;
    }
    
    public void setTheta(double newTheta){
        this.theta = newTheta;
    }

    
    public void render(GraphicsContext gc){
        
        gc.setFill(Paint.valueOf("BLACK"));
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
