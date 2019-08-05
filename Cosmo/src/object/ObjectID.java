package object;

        import java.util.Random;

public enum ObjectID {
    BasicEnemy1(),
    BasicEnemy2(),
    BasicEnemy3(),
    Player(),
    Bullet();

    public static ObjectID getRandomEnemy() {
        Random random = new Random();
        return values()[random.nextInt(values().length-2)];
    }
}
