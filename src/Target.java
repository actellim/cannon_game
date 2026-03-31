import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Target extends PotentialTarget{

    public Target(double targetNumber, double blockerSpeed, 
                  double canvasWidth, double canvasHeight) {
        
        Random r = new Random();
        double height = 60; 
        double x = (targetNumber*40)+(canvasWidth/2);
        double maxSpeed = 200;
        double speed = r.nextDouble((double)blockerSpeed, maxSpeed);
        // Pass x, y to super
        super(x, canvasWidth, canvasHeight, speed, height);
    }
    
    @Override
    public void render(GraphicsContext gc){
    	gc.setFill(new LinearGradient(
    		    0, 0,   // start top
    		    0, 1,   // end bottom
    		    true,   // proportional
    		    CycleMethod.NO_CYCLE,
    		    new Stop(0, Color.LIGHTCYAN), // top
    		    new Stop(0.5, Color.LIGHTSKYBLUE),  // mid
    		    new Stop(1, Color.SKYBLUE)       // bottom
    		));
        gc.fillRect(getX(), getY(), getWidth(), getHeight());

    		// I wanted it look like glass so highlight line for "glossy" effect
    		gc.setStroke(Color.rgb(255, 255, 255, 0.6));
    		gc.setLineWidth(2);
    		gc.strokeLine(getX(), getY() + getHeight() * 0.2, getX() + getWidth(), getY() + getHeight() * 0.2);
    }


    // Update logical position every frame.
    @Override
    public void handle(long now){
        double dy = this.getElapsed(now) * this.getSpeed();
        this.checkCollision();
        setY(getY()-dy);
    }
}
