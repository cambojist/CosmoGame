package main;

import object.Bullet;
import object.GameObject;
import object.ObjectID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean isSpacePressed = false;

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectID.Player) {
                if (key == KeyEvent.VK_UP) {
                    isUpPressed = true;
                    tempObject.setVelY(-5);
                }
                if (key == KeyEvent.VK_DOWN) {
                    isDownPressed = true;
                    tempObject.setVelY(5);
                }
                if (key == KeyEvent.VK_LEFT) {
                    isLeftPressed = true;
                    tempObject.setVelX(-5);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    isRightPressed = true;
                    tempObject.setVelX(5);
                }
                if (key == KeyEvent.VK_SPACE & !isSpacePressed) {
                    isSpacePressed = true;
                    handler.addObject(new Bullet(tempObject.getX(), tempObject.getY()-20, ObjectID.Bullet, handler));
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE)
           System.exit(1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectID.Player) {
                if (key == KeyEvent.VK_UP) isUpPressed = false;
                if (key == KeyEvent.VK_DOWN) isDownPressed = false;
                if (key == KeyEvent.VK_LEFT) isLeftPressed = false;
                if (key == KeyEvent.VK_RIGHT) isRightPressed = false;
                if (key == KeyEvent.VK_SPACE) isSpacePressed = false;

                if (!isUpPressed & !isDownPressed) tempObject.setVelY(0);
                if (!isLeftPressed & !isRightPressed) tempObject.setVelX(0);
            }
        }
    }

}
