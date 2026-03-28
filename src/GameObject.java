public class GameObject {

    private int[] xyPos;

    public GameObject(int[] xy){
        this.xyPos = xy;
    }

    public int[] getPos() {
        return xyPos;
    }

    public void setPos(int[] pos) {
        this.xyPos = pos;
    }

}
