import java.util.Random;


public abstract class PotentialTarget extends GameObject{

    private double speed;
    private double width;
    private double height;
    private double canvasHeight;

    public PotentialTarget(double x, double canvasWidth, 
        double canvasHeight, double speed, double height){
        Random r = new Random();
        double y = r.nextDouble(canvasHeight/4, canvasHeight*3/4);
        this.speed = speed;
        this.width = 20;
        this.height = height;
        this.canvasHeight = canvasHeight;
        super(x, y);
    }
    // Setters
    public void setWidth(double w){
        width = w;
    }
    
    public void setHeight(double h){
        height = h;
    }

    public void setSpeed(double s){
        speed = s;
    }
    
    // Getters
    public double getHeight(){
        return height;
    }
    
    public double getWidth(){
        return width;
    }

    public double getSpeed(){
        return speed;
    }
    
    // Collision Detection
    public boolean checkCollision(){
        boolean val = false;
        if (getY() < 0 || getY() + getHeight() > canvasHeight){
            val = true;
        }
        return val;
    }
    
}
