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
    
    // Collision Detection needs to be handled for both cases
    // to deal with time desync jitter bug.
    public void checkCollision(){
        // Top boundary.
        if (this.getY() <= 0){
            this.setY(0);
            // Stop the jitter!
            if (this.getSpeed() > 0){
                this.setSpeed(this.getSpeed()*-1);
            }
        } 
        // Bottom Boundary
        if (getY() + getHeight() >= canvasHeight){
            this.setY(canvasHeight-this.getHeight());
            if (this.getSpeed() < 0){
                this.setSpeed(this.getSpeed()*-1);
            }
        }
    }
}
