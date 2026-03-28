import java.util.Random;

public class Blocker extends GameObject{

    private double speed;
    private double width;
    private double height;

    public Blocker(double canvasWidth, double canvasHeight) {
        
        Random r = new Random();
        double h = 120; // blocker height defined here for constructor maths
        double w = 20;
        double x = canvasWidth*9/20;
        double y = r.nextDouble(canvasHeight/4, canvasHeight*3/4);
        this.height = h;
        this.width = w;
        double maxSpeed = 250;
        this.speed = r.nextDouble(maxSpeed)-maxSpeed/2;
        // Pass x, y to super
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
/* 
        blocker = new Rectangle(20, 120);

        blocker.setX(400);
        blocker.setY(rand.nextInt(HEIGHT - 150));

        blockerSpeed = 2.5;

        root.getChildren().add(blocker);
*/
}