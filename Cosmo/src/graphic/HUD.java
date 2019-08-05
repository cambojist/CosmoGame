package graphic;

import main.Game;

import java.awt.*;

public class HUD {
    public static int health = 5;
    public static int score = 0;

    public static void playerHit() {
        health--;
        HUD.score++;

    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < 5; i++) {
            g.fillRect(20*i + 15, 15, 16, 16);
        }

        g.setColor(Color.green);
        for (int i = 0; i < health; i++) {
            g.fillRect(20*i + 15, 15, 16, 16);
        }

        Font font = new Font("arial", 1, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, Game.WIDTH - 150, 30);
    }
}
