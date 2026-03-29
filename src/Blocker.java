import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Blocker extends PotentialTarget{

    public Blocker(double canvasWidth, double canvasHeight) {
        
        Random r = new Random();
        double height = 120; 
        double x = canvasWidth*9/20;
        double maxSpeed = 200;
        double speed = r.nextDouble(maxSpeed)-maxSpeed/2;
        super(x, canvasWidth, canvasHeight, speed, height);
    }

    @Override
    public void render(GraphicsContext gc){
        gc.setFill(Paint.valueOf("BLACK"));
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void handle(long now){
        double dy = this.getElapsed(now) * this.getSpeed();
        this.checkCollision();
        // Update logical position every frame.
        setY(getY()-dy);
    }
}