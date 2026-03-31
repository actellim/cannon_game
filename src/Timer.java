import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Timer extends GameObject{
	
	// Double for string conversion for display.
	private Double remaining, totalTime;
	private GameListener listener;

	public Timer(GameListener listener) {
		int x = 10;
		int y = 20; // hardcode timer pos.
		double timeLimit = 10;
		this.remaining = timeLimit;
		this.totalTime = timeLimit;
		this.listener = listener;
		super(x, y);
	}
	
	// Getter
	public Double getTime(){
		return remaining;
	}
	
	public Double getTotalTime(){
		return totalTime;
	}
	
	public Double getPlayedTime(){
		return totalTime-remaining;
	}

	// Increment
	public void addTime(double t){
		remaining = remaining + t;
		totalTime = totalTime + t;
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
		// Update the remaining time.
		removeTime(elapsedTime);
		
		// Check for game over.
		if (getTime() < 0){
			listener.gameOver(false);
		}
	}
	
	public void render(GraphicsContext gc){
		// Draw the time left.
		gc.setFill(Paint.valueOf("BLACK"));
		gc.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
		if (getTime() > 0) {
			gc.fillText("Time Remaining: " + toString(), getX(), getY());
		}
	}
}
