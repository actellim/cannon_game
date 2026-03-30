import java.util.ArrayList;

import javafx.animation.AnimationTimer;

public class CollisionDetector extends AnimationTimer{
    
    private double canvasHeight, canvasWidth;
    private ArrayList<CannonBall> cannonBalls;
    private ArrayList<Target> targets;
    private Blocker blocker;
    private Long previousTime;
    private GameListener listener;
    
    public CollisionDetector(double canvasWidth, double canvasHeight,
        ArrayList<CannonBall> cannonBalls, ArrayList<Target> targets,
        Blocker blocker, GameListener listener
    ) {
        this.cannonBalls = cannonBalls;
        this.targets = targets;
        this.blocker = blocker;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.previousTime = null;
        this.listener = listener;
    }
    
    
    // Rework here indicates the need for a superclass common to GameObject and CollisionDetector
    public double getElapsed(long current){
        double elapsedTime;
        if (previousTime != null) 
            elapsedTime = (current - previousTime) / 1000000000.0; 
        else
            elapsedTime = 0.0; 
        previousTime = current;
        return elapsedTime;
    }
    

    private boolean checkCollision(CannonBall cannonBall, PotentialTarget blockerOrTarget){
        boolean xCol = false;
        boolean yCol = false;
        // Check for collision in x
        double cannonBallLeft = cannonBall.getX();
        double cannonBallRight = cannonBallLeft + cannonBall.getSize();
        double blockerOrTargetLeft = blockerOrTarget.getX();
        double blockerOrTargetRight = blockerOrTargetLeft + blockerOrTarget.getWidth();
        if (cannonBallRight > blockerOrTargetLeft && cannonBallLeft < blockerOrTargetRight){
            xCol = true;
        }
        // Check for collision in y
        double cannonBallTop = cannonBall.getY();
        double cannonBallBottom = cannonBallTop + cannonBall.getSize();
        double blockerOrTargetTop = blockerOrTarget.getY();
        double blockerOrTargetBottom = blockerOrTargetTop + blockerOrTarget.getHeight();
        // Game field counts down in y!
        if (cannonBallTop < blockerOrTargetBottom && cannonBallBottom > blockerOrTargetTop){
            yCol = true;
        }
        if (xCol && yCol){
            return true;
        }
        else{
            return false;
        }
    }
    
    // Realizing right about now that boucing the ball off the wall 
    // would have been a lot easier.
    public boolean checkCollision(CannonBall cannonBall){
        double cannonBallRight = cannonBall.getX() + cannonBall.getSize();
        double cannonBallTop = cannonBall.getY();
        double cannonBallBottom = cannonBallTop + cannonBall.getSize();
        if (cannonBallTop < 0 || cannonBallRight > canvasWidth || cannonBallBottom > canvasHeight){
            return true;
        }
        else
            return false;
        // Okay maybe that wasn't so bad.
    }
    

    public void handle(long now){
        // Can't use iterators and remove the objects
        // from the field, need to for loop. Also,
        // need to count down to avoid stack issues.
        // Iterate through the list of cannon balls.
        for(int i = cannonBalls.size()-1; i >= 0; i--){
            CannonBall ball = cannonBalls.get(i);
            // Check for collision with the walls.
            if (checkCollision(ball)){
                listener.playWallHit();
                listener.removeGameObject(ball);
            }
            // Check for collision with the target list.
            else{
                for(int j = targets.size()-1; j >=0; j--){
                    Target target = targets.get(j);
                    if(checkCollision(ball, target)){
                        listener.addTime(3);
                        listener.playTargetHit();
                        listener.removeGameObject(ball);
                        listener.removeGameObject(target);
                        break;
                    }
                }
                // ...and the blocker!
                if(checkCollision(ball, blocker)){
                    listener.playBlockerHit();
                    listener.removeGameObject(ball);
                    listener.removeTime(3);
                }
            }
        }
    }
}
