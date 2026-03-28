public class Blocker extends GameObject{

    public Blocker(int[] xy) {
        super(xy);
    }
    
    @Override
    public void setPos(int[] pos) {
        System.err.println("Can't change cannon pos");
    }
        blocker = new Rectangle(20, 120);

        blocker.setX(400);
        blocker.setY(rand.nextInt(HEIGHT - 150));

        blockerSpeed = 2.5;

        root.getChildren().add(blocker);
}
