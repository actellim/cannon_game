// import javafx.scene.text.Text;

public class Timer {
	
	// Double for string conversion for display.
	private Double remaining;

	public Timer() {
		this.remaining = 10.0;
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
		return(remaining.toString());		
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
