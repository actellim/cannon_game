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
    
    /* Moved inside of PotentialTarget
    public boolean checkCollision(PotentialTarget t){
        boolean val = false;
        if (t.getY() < 0 || t.getY() + t.getHeight() > canvasHeight)
            val = true;
        return val;
    }
    */
}
