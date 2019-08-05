package object;

import graphic.HUD;
import graphic.Texture;
import main.Game;
import main.Handler;
import main.Spawn;

import java.awt.*;

public class Player extends GameObject {
    private Handler handler;
    Spawn s;
    Texture texture = Game.getInstance();

    public Player(int x, int y, ObjectID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x+23, y, 20,18 );
    }

    public Rectangle getBounds2() {
        return new Rectangle(x+13, y+20, 30, 20);
    }

    public Rectangle getBounds3() {
        return new Rectangle(x+7, y+42, 52, 22);
    }

    public Rectangle getBounds4() {
        return new Rectangle(x, y+50, 64, 14);
    }


    private void collision() {
        s = new Spawn(handler);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject obj = handler.object.get(i);
            if(obj.getId() == ObjectID.BasicEnemy1 || obj.getId() == ObjectID.BasicEnemy2 || obj.getId() == ObjectID.BasicEnemy3) {
                if(getBounds().intersects(obj.getBounds()) || getBounds2().intersects(obj.getBounds()) || getBounds3().intersects(obj.getBounds()) || getBounds4().intersects(obj.getBounds())) {
                    HUD.playerHit();
                    handler.removeObject(obj);
                    s.spawn();
                }
            }
        }
    }

    public int collisionBorder(int value, int min, int max) {
        if (value <= min)
            return min;
        else if (value >= max)
            return max;
        else
            return value;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = collisionBorder(x, 0, Game.WIDTH-80);
        y = collisionBorder(y, 0, Game.HEIGHT-100);
        collision();
    }

    public void render(Graphics g) {
        g.drawImage(texture.player, x, y, null);

    }
}
