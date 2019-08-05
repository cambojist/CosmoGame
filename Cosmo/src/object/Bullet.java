package object;

import graphic.Texture;
import main.Game;
import main.Handler;

import java.awt.*;

public class Bullet extends GameObject {
    private Handler handler;

    private Texture texture = Game.getInstance();
    private int speed = 10;

    public Bullet(int x, int y, ObjectID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x+28, y+25, 8,11);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject obj = handler.object.get(i);
            if (obj.getId() == ObjectID.Bullet) {
                if (y < -64)
                    handler.removeObject(this);
            }
        }
    }

    public void tick() {
        y -= speed;

        collision();
    }

    public void render(Graphics g) {
        g.drawImage(texture.bullet, x, y, null);
    }
}
