import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject extends AnimationTimer{

    private double xPos;
    private double yPos;
    private Long previousTime;

    public GameObject(double x, double y){
        this.xPos = x;
        this.yPos = y;
        // Objects need to be created and immediately used.
        previousTime = null;

    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public void setX(double x) {
        this.xPos = x;
    }

    public void setY(double y) {
        this.yPos = y;
    }
    
    public double getElapsed(long current){
        double elapsedTime;
        if (previousTime != null) 
            elapsedTime = (current - previousTime) / 1000000000.0; 
        else
            elapsedTime = 0.0; 
        previousTime = current;
        return elapsedTime;
    }
    
    public abstract void render(GraphicsContext gc);
}
