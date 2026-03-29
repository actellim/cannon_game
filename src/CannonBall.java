import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public class CannonBall extends GameObject{
    private double theta, speed, size;

    public CannonBall(double x, double y, double size, double theta) {
        this.theta = theta;
        this.speed = 400;
        this.size = size;
        super(x-size/2, y-size/2);
    }
    

    public double getSize(){
        return this.size;
    }
    
    public double getSpeed(){
        return this.speed;
    }

    
    public void render(GraphicsContext gc){
        
        gc.setFill(Paint.valueOf("BLACK"));
        gc.fillOval(getX(), getY(), this.size, this.size);
    }
    

    public void handle(long now){
        
        // Only call getElapsed once per frame!
        double dt = getElapsed(now);
        // x2 = x1+(speed*time*cos(theta))
        this.setX(getX()+(this.speed*dt*Math.cos(this.theta)));
        // y2 = y1+(speed*time*sin(theta))
        this.setY(getY()+(this.speed*dt*Math.sin(this.theta)));

    }
}
