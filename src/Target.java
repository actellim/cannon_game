import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Target extends PotentialTarget{

    public Target(double targetNumber, double blockerSpeed, 
                  double canvasWidth, double canvasHeight) {
        
        Random r = new Random();
        double height = 60; 
        double x = (targetNumber*40)+(canvasWidth/2);
        double maxSpeed = 500;
        double speed = r.nextDouble(blockerSpeed, maxSpeed)-
            (blockerSpeed+(blockerSpeed+maxSpeed)/2);
        // Pass x, y to super
        super(x, canvasWidth, canvasHeight, speed, height);
    }
    
    @Override
    public void render(GraphicsContext gc){
        gc.setFill(Paint.valueOf("ORANGE"));
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    // Update logical position every frame.
    @Override
    public void handle(long now){
        if (checkCollision()){
            setSpeed(getSpeed()*-1);
        }
        setY(getY()-(getElapsed(now)*getSpeed()));
    }
}
