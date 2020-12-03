package Framework;

/**
 * GameStuff.Game is the biggest of boys, the bestest of the bestest, the biggest and baddest, the ultra magnus, the alpha and
 * the omega - its the class that does EVERYTHING
 */
public class Empires
{
    private Handler handler;

    public Empires()
    {
        reset();
    }

    /**
     * resets the GameStuff.Game to its default constructor
     */
    private void reset()
    {
        handler = new Handler();

        run(); //restarts the game loop
    }

    /**
     * The run method is where the core game loop happens. It ticks a specified number of times a second
     */
    public void run()
    {
        //MAIN GAME LOOP
        long lastTime = System.nanoTime();
        double amountOfTicks = 30.0; //The desired number of ticks per second
        double ns = 1000000000 / amountOfTicks; //Amount of time between ticks
        double delta = 0; //the number of ticks due for completion

        while (!handler.getRestart()) //loops until handler says it is time for a restart
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; //calculates how many ticks are due for completion
            lastTime = now;
            while (delta >= 1) //completes that many ticks
            {
                tick();
                delta--;
            }
            handler.repaint(); //renders as much as possible
        }

        reset(); //restarts
    }

    /**
     * The part of the main game loop that thinks everything through
     */
    private void tick()
    {
        handler.tick();
    }
}

