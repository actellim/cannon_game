import javafx.scene.media.AudioClip;

// src: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/AudioClip.html
public class SoundManager {
    private AudioClip targetHit;
    private AudioClip cannonFire;
    private AudioClip blockerHit;
    private AudioClip wallHit;
    
    public SoundManager(){
        this.targetHit = new AudioClip(getClass().getResource("target_hit.wav").toString());
        this.cannonFire = new AudioClip(getClass().getResource("cannon_fire.wav").toString());
        this.blockerHit = new AudioClip(getClass().getResource("blocker_hit.wav").toString());
        this.wallHit = new AudioClip(getClass().getResource("wilhelm_scream.wav").toString());
    }
    
    public void targetHit(){
        this.targetHit.play();
    }
    
    public void cannonFire(){
        this.cannonFire.play();
    }
    
    public void blockerHit(){
        this.blockerHit.play();
    }
    
    public void wallHit(){
        this.wallHit.play();
    }

}
