import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;

// ref: https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Alert.html
public class SelectScreen extends Alert{

    // Start Game
    public SelectScreen(){
        super(AlertType.CONFIRMATION);
        this.setGraphic(null); // Gets rids of the question mark
        this.setTitle("Startup");
        this.setHeaderText("Why hello there! ✨ ");
        this.setContentText("Wanna play a game?");

        ButtonType buttonYes = new ButtonType("Yes!", ButtonData.YES);
        ButtonType buttonNo = new ButtonType("Nope.", ButtonData.NO);
        this.getButtonTypes().setAll(buttonYes, buttonNo);
        
        // Attempt at Beautifying~
        DialogPane pane = this.getDialogPane();

        // Background
        pane.setStyle(
            "-fx-background-color: #f5f5f5;" +
            "-fx-padding: 25; -fx-background-radius: 10; -fx-cursor: hand;" );
        
        // Content Text
        pane.lookup(".content.label").setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 20pt; -fx-text-fill: #191970; -fx-padding: 10 0 20 20;");
        
        // Header text    N: Been messing with this for hours, doesn't work, remove then re-add it, now it works.... -_-
        Label headerLabel = (Label) pane.lookup(".header-panel .label");
        if (headerLabel != null) {
            headerLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 20pt; -fx-text-fill: #191970; -fx-font-weight: bold;");
        }
        

        this.setOnShown(_ -> {

        	// Create direct references to the dialog buttons so to be able to style
            Button yesBtn = (Button) pane.lookupButton(buttonYes);
            Button noBtn = (Button) pane.lookupButton(buttonNo);

            // Beautifying the "Yes" button
            yesBtn.setStyle(
                "-fx-font-family: 'Comic Sans MS'; -fx-background-color: linear-gradient(#4a90e2, #357abd);" +
                "-fx-text-fill: white; -fx-background-radius: 20;" +
                "-fx-padding: 8 40; -fx-font-size: 12pt;");
            yesBtn.setDefaultButton(true);

            // Beautifying the "No" button
            noBtn.setStyle(
                "-fx-font-family: 'Comic Sans MS'; -fx-background-color: #e0e0e0;" +
                "-fx-text-fill: #333; -fx-background-radius: 20;" +
                "-fx-padding: 8 40; -fx-font-size: 12pt;");
            
         // Adding hover effects for the "Yes" button
            yesBtn.setOnMouseEntered(_ ->
                yesBtn.setStyle(yesBtn.getStyle() +
                    "-fx-background-color: linear-gradient(#5aa0f2, #3f8ae0);"));
            
            yesBtn.setOnMouseExited(_ ->
            yesBtn.setStyle(
                "-fx-font-family: 'Comic Sans MS'; -fx-background-color: linear-gradient(#4a90e2, #357abd);" +
                "-fx-text-fill: white; -fx-background-radius: 20;" +
                "-fx-padding: 8 20; -fx-font-size: 13pt;"));
            
         // Adding hover effects for the "No" button
            noBtn.setOnMouseEntered(_ ->
            noBtn.setStyle(noBtn.getStyle() +
                "-fx-background-color: #d5d5d5;"));
            
	        noBtn.setOnMouseExited(_ ->
	            noBtn.setStyle(
	                "-fx-font-family: 'Comic Sans MS'; -fx-background-color: #e0e0e0; -fx-text-fill: #333;" +
	                "-fx-background-radius: 20;-fx-padding: 8 20; -fx-font-size: 13pt;"));
        });
            
    }

    
    // Game Over
    // SelectScreen(score, timer.getTotalTime(), shotsFired, gameWon);
    public SelectScreen(Integer finalScore, Double totalTime, Integer shotsFired, boolean gameWon){
        super(AlertType.CONFIRMATION);
        this.setGraphic(null); // Again it gets rids of the question mark
        this.setTitle("Game Over");
        String headerText;
        if (gameWon){
            headerText = new String("You won!\n \n");
            
            ImageView icon = new ImageView(
            	    new Image(getClass().getResource("/YouWon.png").toExternalForm()));
            icon.setPreserveRatio(true);
            icon.setFitWidth(80);
            icon.setFitHeight(80);
            this.setGraphic(icon);
        }
        else{
            headerText = new String("You lost.\n \n");
            
            ImageView icon = new ImageView(
            	    new Image(getClass().getResource("/YouLost.png").toExternalForm()));
            icon.setPreserveRatio(true);
            icon.setFitWidth(80);
            icon.setFitHeight(80);
            this.setGraphic(icon);
        }
        headerText = headerText + "Final Score: " + finalScore.toString() 
        + "\nShots Fired: " + shotsFired.toString() + "\nSeconds Elapsed: " 
        + totalTime.intValue();
        this.setHeaderText(headerText);
        this.setContentText("Play Again?");

        ButtonType buttonYes = new ButtonType("Yes", ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonData.NO);
        this.getButtonTypes().setAll(buttonYes, buttonNo);
     // More Beautifying~
        DialogPane pane = this.getDialogPane();

        // Background
        pane.setStyle("-fx-background-color: #f5f5f5;" +
            "-fx-padding: 25; -fx-background-radius: 10; -fx-cursor: hand;" );
        
        // Content Text
        pane.lookup(".content.label").setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 20pt; -fx-text-fill: #191970; -fx-padding: 10 0 20 10;");
        
        Label headerLabel = (Label) pane.lookup(".header-panel .label");
        if (headerLabel != null) {
            headerLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 20pt; -fx-text-fill: #191970; -fx-font-weight: bold;");
        }
    }

    
    public boolean promptUser(){
        Optional<ButtonType> result = showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonData.YES)
            return true;
        else
            return false;
    }
}
