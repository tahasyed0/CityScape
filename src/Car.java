import javax.swing.*;
import java.awt.*;

public class Car {

    private int x;
    private int speed = 3;
    private int dx = speed;
    private Image car = new ImageIcon("car.png").getImage();
    private CityScape cityScape;

    public Car(CityScape cityScape, int x) {
        this.cityScape = cityScape;
        this.x = x;
    }

    public void move() {
        if (x > cityScape.getWidth()-car.getWidth(null) || x < 0) {
            dx = -speed;
            x = cityScape.getWidth()-car.getWidth(null);
        }
        if (x + dx < 0) {
            dx = speed;
        }
        x += dx;
    }

    public void paint(Graphics2D g2d) {
        if (dx < 0) {
            g2d.drawImage(car, x+car.getWidth(null), cityScape.getHeight() - car.getHeight(null), -car.getWidth(null), car.getHeight(null), null);
        } else {
            g2d.drawImage(car, x, cityScape.getHeight() - car.getHeight(null), null);
        }
    }
}
