import java.util.Random;

public class Target extends GameObject{

    // Each target gets a different speed
    private double speed;
    private double width;
    private double height;

    public Target(double targetNumber, double blockerSpeed, 
                  double canvasWidth, double canvasHeight) {
        
        Random r = new Random();
        double h = 60; // target height defined here for constructor maths
        double w = 20;
        double x = (targetNumber*40)+(canvasWidth/2);
        double y = r.nextDouble(canvasHeight/4, canvasHeight*3/4);
        this.height = h;
        this.width = w;
        double maxSpeed = 500;
        this.speed = r.nextDouble(blockerSpeed, maxSpeed)-
            (blockerSpeed+(blockerSpeed+maxSpeed)/2);
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
    
    

        /* tabula rasa
        for (int i = 0; i < 9; i++) {

            Rectangle target = new Rectangle(20, 60);

            target.setX(500 + (i * 40));
            target.setY(rand.nextInt(HEIGHT - 60));
            target.setFill(Color.ORANGE); 

            // Each target gets a different speed
            speeds[i] = 1 + rand.nextDouble() * 3;

            targets[i] = target;

            root.getChildren().add(target);
        }*/
}
