public class CollisionDetector {
    
    private double canvasHeight;
    private double canvasWidth;
    
    // Everything is about to be a square,
    // will fix in an iteration.
    // CannonBall and a Target or Blocker
    public CollisionDetector(double canvasWidth, double canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }
    
    public boolean checkCollision(GameObject o){
        boolean val = false;
        if (o.getY() < 0 || o.getY() + o.getHeight() > canvasHeight)
            val = true;
        return val;
    }
}
