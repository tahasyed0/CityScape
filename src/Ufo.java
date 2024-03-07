import java.awt.*;

public class Ufo {

    private int x;
    private int y;
    private int dx = Math.random() >= 0.5 ? 1 : -1;
    private int dy = Math.random() >= 0.5 ? 1 : -1;
    private CityScape cityScape;

    public Ufo(CityScape cityScape, int x, int y) {
        this.cityScape = cityScape;
        this.x = x;
        this.y = y;
    }

    public void move() {

        if (y > cityScape.getHeight()-440) {
            y = (int) (Math.random()*(cityScape.getHeight()-440));
        }
        if (x > cityScape.getWidth()-40) {
            x = (int) (Math.random()*(cityScape.getWidth()-40));
        }

        if (x + dx < 0) {
            dx = 1;
        } else if (x + dx + 40 > cityScape.getWidth()) {
            dx = -1;
        } else if (y + dy < Math.max(0, cityScape.getHeight()-700)) {
            dy = 1;
        } else if (y + dy + 15 > cityScape.getHeight()-440) {
            dy = -1;
        }
        x += dx;
        y += dy;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillOval(x, y+5, 40, 15);
        g2d.setColor(Util.getColor(97, 178, 184));
        g2d.fillOval(x+8, y, 23, 12);
    }
}
