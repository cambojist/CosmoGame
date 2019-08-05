package main;

import object.ObjectID;
import object.BasicEnemy;
import graphic.HUD;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private Random random = new Random();
    private int count = 1;

    public Spawn(Handler handler) {
        this.handler = handler;
    }

    public void spawn() {
        if (HUD.score % 5 == 0)
            count++;
        createEnemy(count);
    }

    public void createEnemy(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 80), -10, ObjectID.getRandomEnemy(), handler));
        }
    }
}
