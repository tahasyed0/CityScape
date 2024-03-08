import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class CityScape extends JPanel {

    private Building[] b = new Building[40];
    private Ufo[] ufos = new Ufo[5];
    private Car car = new Car(this, 0);

    private ArrayList<Integer[]> stars = new ArrayList<>();

    public CityScape() {
        int[] xWindows = {3, 2, 3, 5, 4, 4, 1, 3};
        int[] yWindows = {4, 6, 2, 5, 9, 6, 8, 3};
        double[] lights = {0.5, 0.6, 0.74, 0.6, 0.5, 0.3, 0.7, 0.5};
        int curr = 10;
        for (int i = 0; i < b.length; i++) {
            b[i] = new Building(this, curr, xWindows[i%xWindows.length], yWindows[i%yWindows.length], lights[i%lights.length]);
            curr += 10 + xWindows[i%xWindows.length]*30 + (xWindows[i%xWindows.length]+1)*5;
        }

        for (int i = 0; i < 900; i++) {
            stars.add(new Integer[]{(int) Math.floor(Math.random() * 2000), (int) Math.floor(Math.random() * 2000)});
        }

        ufos[0] = new Ufo(this, 0, 0);
        ufos[1] = new Ufo(this, 300, 20);
        ufos[2] = new Ufo(this, 5, 300);
        ufos[3] = new Ufo(this, 250, 250);
        ufos[4] = new Ufo(this, 70, 2);
    }

    public void move() {
        for (Ufo u : ufos) {
            u.move();
        }
        car.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //stars
        g2d.setColor(Color.WHITE);
        for (Integer[] arr : stars) {
            g2d.fillOval(arr[0], arr[1], 5, 5);
        }

        //moon
        g2d.setColor(Color.WHITE);
        g2d.fillOval(60, 60, 110, 110);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(80, 50, 110, 110);


        //buildings
        g2d.setColor(Color.GRAY);
        for (Building k : b) {
            if (k.getX() > this.getWidth()) {
                break;
            }
            k.paint(g2d);
        }

        for (Ufo u : ufos) {
            u.paint(g2d);
        }

        car.paint(g2d);

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("My Drawing");

        CityScape cityScape = new CityScape();

        frame.add(cityScape);
        frame.setSize(1020, 640);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(1020, 640));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true)
        {
            cityScape.move();
            cityScape.repaint();
            Thread.sleep(10);
        }
    }
}