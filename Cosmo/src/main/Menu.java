package main;

import object.ObjectID;
import graphic.HUD;
import object.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static main.Game.HEIGHT;
import static main.Game.WIDTH;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Spawn spawn;

    public Menu(Game game, Handler handler, Spawn spawn) {
        this.game = game;
        this.handler = handler;
        this.spawn = spawn;
    }

    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (e.getButton() == MouseEvent.BUTTON1) {

            if (mouseOver(mouseX, mouseY, 210, 150, 200, 64) && game.gameState == Game.State.MENU) {
                game.gameState = Game.State.GAME;
                spawn.spawn();
                handler.addObject(new Player(WIDTH / 2, HEIGHT - 150, ObjectID.Player, handler));
            }

            if (mouseOver(mouseX, mouseY, 210, 250, 200, 64) && game.gameState == Game.State.MENU) {
                System.exit(1);
            }

            if (mouseOver(mouseX, mouseY, 210, 250, 200, 64) && game.gameState == Game.State.END) {
                game.gameState = Game.State.GAME;
                HUD.health = 5;
                HUD.score = 0;
                spawn = new Spawn(handler);
                spawn.spawn();
                handler.addObject(new Player(WIDTH / 2, HEIGHT - 150, ObjectID.Player, handler));
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        if (mouseX > x && mouseX < x + width) {
            if (mouseY > y && mouseY < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void render(Graphics g) {
        Font font = new Font("arial", 1, 50);
        Font font1 = new Font("arial", 1, 30);

        if (game.gameState == Game.State.MENU) {
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Cosmo", 225, 70);

            g.setFont(font1);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play",270,190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Exit",270,290);
        }

        if (game.gameState == Game.State.END) {
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString("YOU DIED", Game.WIDTH / 2 - 120, 150);
            g.setFont(font1);
            g.drawString("Your score: " + HUD.score, Game.WIDTH / 2 - 120, 220);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Try again",240,290);
        }
    }
}
