import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Timer extends GameObject{
	
	// Double for string conversion for display.
	private Double remaining;
	private GameListener listener;

	public Timer(GameListener listener) {
		int x = 10;
		int y = 20; // hardcode timer pos.
		this.remaining = 10.0;
		this.listener = listener;
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
		// Update the remaining time.
		removeTime(elapsedTime);
		
		// Check for game over.
		if (getTime() < 0){
			listener.gameOver();
		}
	}
	
	public void render(GraphicsContext gc){
		// Draw the time left.
		gc.setFill(Paint.valueOf("BLACK"));
		if (getTime() > 0) {
			gc.fillText("Time Remaining: " + toString(), getX(), getY());
		}
	}
}
