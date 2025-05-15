import greenfoot.*;

public class Block extends Actor {
    private int turnSpeed;

    public Block(int maxTurnSpeed) {
        turnSpeed = Greenfoot.getRandomNumber(maxTurnSpeed * 2 + 1) - maxTurnSpeed;
        if (turnSpeed == 0) {
            turnSpeed = 1;
        }
    }

    public void act() {
        turn(turnSpeed);
    }
}
