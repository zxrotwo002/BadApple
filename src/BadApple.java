import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class BadApple extends JFrame {
    private static Clip music;

    public BadApple() {
        try {
            AudioInputStream stream = AudioSystem
                    .getAudioInputStream(Objects.requireNonNull(this.getClass()
                            .getResource("BadApple.wav")));
            music = AudioSystem.getClip();
            music.open(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        VideoPanel videoPanel = new VideoPanel();
        add(videoPanel);
        setTitle("BadApple");
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 510);
        setResizable(false);
        setVisible(true);
        videoPanel.startVideoThread();
    }

    public static void main(String[] args) {
        new BadApple();
        music.start();
    }

}