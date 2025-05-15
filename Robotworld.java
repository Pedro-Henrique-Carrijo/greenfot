import greenfoot.*;
import java.util.*;

public class RobotWorld extends World {
    private int currentMaxTurnSpeed = 2;
    private int currentLevel = 1;

    public RobotWorld() {
        super(800, 600, 1);
        prepare();
    }

    private void prepare() {
        addObject(new Robot(), 48, 50);
        addObject(new ScorePanel(), 71, 554);
        addObject(new Home(), 751, 552);

        // Paredes
        addObject(new Wall(), 48, 147);
        addObject(new Wall(), 159, 147);
        addObject(new Wall(), 266, 147);
        addObject(new Wall(), 587, 147);
        addObject(new Wall(), 692, 147);
        addObject(new Wall(), 791, 147);

        // Bloco giratório
        addObject(new Block(2), 427, 145);

        // Pizzas
        addObject(new Pizza(), 720, 46);
        addObject(new Pizza(), 433, 38);
        addObject(new Pizza(), 183, 302);
        addObject(new Pizza(), 682, 312);
        addObject(new Pizza(), 417, 537);
    }

    public void increaseLevel() {
        currentLevel++;
        setUpLevel();
    }

    public void setUpLevel() {
        if (currentLevel == 2 || currentLevel == 3) {
            currentMaxTurnSpeed++;
            addObject(new Block(currentMaxTurnSpeed), Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600));
            for (int i = 0; i < 5; i++) {
                addObject(new Pizza(), Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(600));
            }
        } else if (currentLevel == 4) {
            Greenfoot.stop(); // Final customizado
        }
    }
}
