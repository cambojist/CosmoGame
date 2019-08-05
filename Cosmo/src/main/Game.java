package main;

import graphic.HUD;
import graphic.Texture;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final String TITLE = "Cosmo";

    private Thread thread;
    private boolean isRunning = false;

    private static Texture texture;

    private Spawn spawn;
    private Handler handler;
    private HUD hud;
    private Menu menu;

    public enum State {
        MENU,
        GAME,
        END
    }

    public State gameState = State.MENU;

    public Game() {
        new Window(WIDTH, HEIGHT, TITLE, this);
        this.start();
    }

    private void init() {
        this.requestFocus();
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        spawn = new Spawn(handler);
        menu = new Menu(this, handler, spawn);
        this.addMouseListener(menu);
        texture = new Texture();
        hud = new HUD();

    }

    private synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();

        }
        stop();
    }

    private void tick() {
        if (gameState == State.GAME) {
            handler.tick();
            if (HUD.health <= 0) {
                gameState = State.END;
                handler.clearAllObject();
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(texture.level, 0, 0, null);

        if (gameState == State.GAME) {
            handler.render(g);
            hud.render(g);
        } else if(gameState == State.MENU || gameState == State.END) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static Texture getInstance() {
        return texture;
    }

    public static void main(String[] args) {
        new Game();
    }

}



