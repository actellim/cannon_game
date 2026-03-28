public abstract class GameObject {

    private double xPos;
    private double yPos;

    public GameObject(double x, double y){
        this.xPos = x;
        this.yPos = y;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }
    
    public abstract double getHeight();

    public void setX(double x) {
        this.xPos = x;
    }

    public void setY(double y) {
        this.yPos = y;
    }
}
