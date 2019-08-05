package graphic;

import main.Game;

import java.awt.image.BufferedImage;

public class Texture {

    private SpriteSheet ps, bs, es, ls;
    private BufferedImage playerSheet = null;
    private BufferedImage bulletSheet = null;
    private BufferedImage enemySheet = null;
    private BufferedImage levelSheet = null;

    private int sizeInPX = 64;

    public BufferedImage player = null;
    public BufferedImage bullet = null;
    public BufferedImage enemy[] = new BufferedImage[3];
    public BufferedImage level = null;

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            playerSheet = loader.loadImage("/spaceship.png");
            bulletSheet = loader.loadImage("/bullet.png");
            enemySheet = loader.loadImage("/enemy_sheet.png");
            levelSheet = loader.loadImage("/level.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        ps = new SpriteSheet(playerSheet);
        bs = new SpriteSheet(bulletSheet);
        es = new SpriteSheet(enemySheet);
        ls = new SpriteSheet(levelSheet);

        getTextures();
    }

    private void getTextures() {
        player = ps.loadImage(1, 1,sizeInPX, sizeInPX);
        bullet = bs.loadImage(1, 1, sizeInPX, sizeInPX);
        enemy[0] = es.loadImage(1, 1, sizeInPX, sizeInPX);
        enemy[1] = es.loadImage(2, 1, sizeInPX , sizeInPX);
        enemy[2] = es.loadImage(3, 1, sizeInPX, sizeInPX);
        level = ls.loadImage(1,1, Game.WIDTH, Game.HEIGHT);
    }
}
