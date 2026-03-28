import javafx.scene.shape.Rectangle;

public class Target extends GameObject{

    public Target(int[] xy) {
        super(xy);
    }

        for (int i = 0; i < 9; i++) {

            Rectangle target = new Rectangle(20, 60);

            target.setX(500 + (i * 40));
            target.setY(rand.nextInt(HEIGHT - 60));
            target.setFill(Color.ORANGE); 

            // Each target gets a different speed
            speeds[i] = 1 + rand.nextDouble() * 3;

            targets[i] = target;

            root.getChildren().add(target);
        }
}
