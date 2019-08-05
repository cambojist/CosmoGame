package object;

import graphic.HUD;
import graphic.Texture;
import main.Game;
import main.Handler;
import main.Spawn;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject {
    private Handler handler;

    private Texture texture = Game.getInstance();
    private Spawn s;

    private Random random = new Random();
    private int speed = random.nextInt(2) + 2;

    public BasicEnemy(int x, int y, ObjectID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y-64, 64, 64);
    }

    private void collision() {
        s = new Spawn(handler);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject obj = handler.object.get(i);
            if(obj.getId() == ObjectID.Bullet) {
                if(getBounds().intersects(obj.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(obj);
                    HUD.score += 1;
                    s.spawn();

                }
            }
        }
    }

    public void tick() {
        y += speed;

        if (y > Game.HEIGHT) {
            speed = random.nextInt(2) + 2;
            x = random.nextInt(Game.HEIGHT-80);
            y = -10;
        }
        collision();
    }

    public void render(Graphics g) {
        if (this.id == ObjectID.BasicEnemy1)
            g.drawImage(texture.enemy[0], x, y-64, null);
        if (this.id == ObjectID.BasicEnemy2)
            g.drawImage(texture.enemy[1], x, y-64, null);
        if (this.id == ObjectID.BasicEnemy3)
            g.drawImage(texture.enemy[2], x, y-64, null);
    }
}
