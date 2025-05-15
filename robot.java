import greenfoot.*;

public class Robot extends Actor {
    private GreenfootImage robotImage1;
    private GreenfootImage robotImage2;
    private GreenfootImage gameOverImage;

    private int animationCounter = 0;
    private int lives;
    private int score;
    private int pizzasEaten;

    private int timer;
    private final int MAXTIMER = 1000;

    public Robot() {
        robotImage1 = new GreenfootImage("man01.png");
        robotImage2 = new GreenfootImage("man02.png");
        gameOverImage = new GreenfootImage("gameover.png");

        setImage(robotImage1);
        lives = 3;
        score = 0;
        pizzasEaten = 0;
        timer = MAXTIMER;
    }

    public void act() {
        robotMovement();
        detectWallCollision();
        detectBlockCollision();
        detectHome();
        eatPizza();
        updateTimer();
    }

    public void robotMovement() {
        boolean moved = false;

        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + 2, getY());
            moved = true;
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - 2, getY());
            moved = true;
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - 2);
            moved = true;
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + 2);
            moved = true;
        }

        if (moved) {
            animate();
        }
    }

    public void animate() {
        animationCounter++;
        if (animationCounter >= 5) {
            if (getImage() == robotImage1) {
                setImage(robotImage2);
            } else {
                setImage(robotImage1);
            }
            animationCounter = 0;
        }
    }

    public void detectWallCollision() {
        if (isTouching(Wall.class)) {
            Greenfoot.playSound("hurt.wav");
            removeLife();
        }
    }

    public void detectBlockCollision() {
        if (isTouching(Block.class)) {
            Greenfoot.playSound("hurt.wav");
            removeLife();
        }
    }

    public void detectHome() {
        if (isTouching(Home.class) && pizzasEaten >= 5) {
            Greenfoot.playSound("yippee.wav");
            increaseScore();
            pizzasEaten = 0;
            resetPosition();
            resetTimer();
            RobotWorld myWorld = (RobotWorld) getWorld();
            myWorld.increaseLevel();
        }
    }

    public void eatPizza() {
        if (isTouching(Pizza.class)) {
            removeTouching(Pizza.class);
            pizzasEaten++;
            Greenfoot.playSound("eat.wav");
            timer += 200;
        }
    }

    public void removeLife() {
        lives--;
        showStatus();
        resetPosition();
        testEndGame();
    }

    public void increaseScore() {
        score++;
        showStatus();
    }

    public void showStatus() {
        getWorld().showText("Vidas: " + lives, 100, 20);
        getWorld().showText("Pontos: " + score, 200, 20);
    }

    public void testEndGame() {
        if (lives <= 0) {
            setImage(gameOverImage);
            Greenfoot.stop();
        }
    }

    public void updateTimer() {
        timer--;
        getWorld().showText("Tempo: " + timer, 70, 580);
        if (timer <= 0) {
            removeLife();
            resetTimer();
        }
    }

    public void resetPosition() {
        setLocation(48, 50);
    }

    public void resetTimer() {
        timer = MAXTIMER;
    }
}
