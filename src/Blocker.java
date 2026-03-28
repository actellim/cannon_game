public class Blocker extends GameObject{

    private double width;
    private double height;

    public Blocker(double x, double y, double w, double h) {
        
        this.width = w;
        this.height = h;
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

    // Getters
    public double getHeight(){
        return height;
    }
    
    public double getWidth(){
        return width;
    }
/* 
        blocker = new Rectangle(20, 120);

        blocker.setX(400);
        blocker.setY(rand.nextInt(HEIGHT - 150));

        blockerSpeed = 2.5;

        root.getChildren().add(blocker);
*/
}