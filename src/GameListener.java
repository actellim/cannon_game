public interface GameListener {
    
    public abstract void gameOver();
    public abstract void fireCannon(double x, double y, double size, double theta);
    public abstract void addTime(double t);
    public abstract void removeTime(double t);
    public abstract void removeGameObject(GameObject obj);
    public abstract void playTargetHit();
    public abstract void playBlockerHit();
    public abstract void playWallHit();
    public abstract void increaseScore();

}  