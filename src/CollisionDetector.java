import java.util.ArrayList;
import java.util.Random;

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
    

    // Check collision between a canonball and the blocker or a target.
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
    
    // Check collision with canvas edge.
    public boolean checkCollision(CannonBall cannonBall){
        double cannonBallLeft = cannonBall.getX();
        double cannonBallRight = cannonBallLeft + cannonBall.getSize();
        double cannonBallTop = cannonBall.getY();
        double cannonBallBottom = cannonBallTop + cannonBall.getSize();
        // Potentially need to add direction checking here to prevent jitter
        if (cannonBallTop < 0 || cannonBallBottom > canvasHeight){
            // Allows shot banking.
            cannonBall.setSpeedY(cannonBall.getSpeedY() * -1);
            return false;
        }
        else if (cannonBallLeft < 0 || cannonBallRight > canvasWidth){
            return true;
        }
        else
            return false;
    }
    

    public void handle(long now){
        // Can't use iterators and remove the objects
        // from the field, need to for loop. Also,
        // need to count down to avoid list removal issues.
        // Iterate through the list of cannon balls.
        for(int i = cannonBalls.size()-1; i >= 0; i--){
            CannonBall ball = cannonBalls.get(i);
            // Check for collision with the walls.
            if (checkCollision(ball)){
                Random r = new Random();
                // Don't play the same sound effect.
                // Don't play it every time.
                int chanceForOffscreenCollision = r.nextInt(1, 5);
                if (chanceForOffscreenCollision == 3)
                    listener.playWallHit();
                if (chanceForOffscreenCollision == 5)
                    listener.playTargetHit();
                listener.removeGameObject(ball);
            }
            // Check for collision with the target list.
            else{
                for(int j = targets.size()-1; j >=0; j--){
                    Target target = targets.get(j);
                    if(checkCollision(ball, target)){
                        listener.addTime(3);
                        listener.increaseScore();
                        listener.playTargetHit();
                        listener.removeGameObject(ball);
                        listener.removeGameObject(target);
                        if(targets.isEmpty()){
                            listener.gameOver(true);
                        }
                        break;
                    }
                }
                // ...and the blocker!
                if(checkCollision(ball, blocker)){
                    listener.playBlockerHit();
                    ball.setSpeedX(ball.getSpeedX() * -1);
                    listener.removeTime(3);
                }
            }
        }
    }
}
