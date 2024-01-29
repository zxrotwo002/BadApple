import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class VideoPanel extends JPanel implements Runnable {
    private int i = 1;
    private Thread videoThread;

    public void startVideoThread() {
        videoThread = new Thread(this);
        videoThread.start();
    }

    public void run() {
        double drawInterval = 1000000000.0 / 30;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (videoThread != null) {
            currentTime = System.nanoTime();
            delta = delta + (currentTime - lastTime) / drawInterval;
            timer = timer + (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                repaint();
                delta = delta - 1;
                drawCount = drawCount + 1;
            }

            if (timer >= 1000000000.0) {
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.getColor(null,4587570));
        g2.fillRect(0,0,650,510);
        if (i == 6569) {
            System.exit(0);
        }
        else {
            i++;
        }

        String fileName = "/data/out (" + i + ").txt";
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = getClass().getResourceAsStream(fileName);
            assert is != null;
            br = new BufferedReader(new InputStreamReader(is));
            String temp;

            int j = 0;

            while ((temp = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(temp);
                while (st.hasMoreTokens()) {
                    int start = Integer.parseInt(st.nextToken());
                    int end = Integer.parseInt(st.nextToken());
                    g2.setColor(Color.black);
                    g2.fillRect(start, j, end - start, 1);
                }
                j++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert is != null;
                is.close();
                assert br != null;
                br.close();
            } catch (Exception ignored) {
            }
        }
    }
}