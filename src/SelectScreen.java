import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

// ref: https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.html
public class SelectScreen extends Alert{

    // Start Game
    public SelectScreen(){
        super(AlertType.CONFIRMATION);
        this.setTitle("Cannon Game Startup");
        this.setHeaderText("Why hello there!");
        this.setContentText("Wanna play a game?");

        ButtonType buttonYes = new ButtonType("Yes!", ButtonData.YES);
        ButtonType buttonNo = new ButtonType("Absolutely Not.", ButtonData.NO);
        this.getButtonTypes().setAll(buttonYes, buttonNo);
    }
    
    // Game Over
    // SelectScreen(score, timer.getTotalTime(), shotsFired, gameWon);
    public SelectScreen(Integer finalScore, Double totalTime, Integer shotsFired, boolean gameWon){
        super(AlertType.CONFIRMATION);
        this.setTitle("Game Over");
        String headerText;
        if (gameWon){
            headerText = new String("You win!\n");
        }
        else{
            headerText = new String("You lose.\n");
        }
        headerText = headerText + "Final Score: " + finalScore.toString() 
        + "\nShots Fired: " + shotsFired.toString() + "\nSeconds Elapsed: " 
        + totalTime.intValue();
        this.setHeaderText(headerText);
        this.setContentText("Play Again?");

        ButtonType buttonYes = new ButtonType("Yes", ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonData.NO);
        this.getButtonTypes().setAll(buttonYes, buttonNo);
    }
    
    public boolean promptUser(){
        Optional<ButtonType> result = showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonData.YES)
            return true;
        else
            return false;
    }
}
