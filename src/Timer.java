import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Timer extends GameObject{
	
	// Double for string conversion for display.
	private Double remaining;
	private long previousTime;

	public Timer() {
		this.remaining = 10.0;
		int x = 10;
		int y = 20; // hardcode timer pos.
		super(x, y);
	}
	
	// Getter
	public double getTime(){
		return remaining;
	}

	// Increment
	public void addTime(double t){
		remaining = remaining + t;
	}
	
	// Decrement
	public void removeTime(double t){
		remaining = remaining - t;
	}
	
	public String toString(){
		// Type dance for clean display.
		Integer remainingInt = (int) Math.round(remaining);
		String remainingString = remainingInt.toString();
		return(remainingString);
	}
	
	public void handle(long now){
		double elapsedTime = getElapsed(now);
		// Update the game time.
		removeTime(elapsedTime);

	}
	
	public void render(GraphicsContext gc){
		// Draw the time left.
		gc.setFill(Paint.valueOf("BLACK"));
		if (getTime() > 0) {
			gc.fillText("Time Remaining: " + toString(), getX(), getY());
		}
	}

	/* Tabula Rasa 

        // 10-Second Countdown Variable
        int[] timeRemaining = {10};
        
        
        // Create 10-Second Timer        
        countdown = new Timeline(
        	    new KeyFrame(Duration.seconds(1), e -> {
        	    	// Note: EventHandler<ActionEvent>() == (ActionEvent e) -> == e ->

        	        timeRemaining[0]--;

        	        timerCount.setText("Time Remaining: " + timeRemaining[0]);

        	        // Check if time is up
        	        if (timeRemaining[0] <= 0) {

        	            timer.stop();      // stops movement of everything
        	            countdown.stop();  // stops timer
        	            
        	            // Switch scene to GameOver Scene
        	            stage.setScene(GameOverScene(stage));
        	        }

        	    })
        	);
        
        // Run KeyFrame 10 times
        countdown.setCycleCount(10);
        countdown.play();
		*/
}
