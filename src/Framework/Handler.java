package Framework;

import Framework.MouseStuff.Mouse;
import GameStuff.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Framework.Handler directs information between classes. Its the hub.
 * and other classes will just have a copy of this handler.
 */
public class Handler
{
    private Ticker ticker;
    private Keyboard keyboard;
    private Mouse mouse;
    private Panel screen;
    private Frame frame;
    private ButtonManager buttonManager;
    private Game game;

    private boolean restart;

    private int screenWidth;
    private int screenHeight;

    private Random rand;

//    private Board board;

    /**
     * Constructor for the Framework.Handler class
     */
    public Handler()
    {
        rand = new Random();

        //initializes the frame
        frame = new Frame();
        frame.setTitle("Test");
        frame.setSize(960, 960);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        //these are the big bois
        ticker = new Ticker(this);
        mouse = new Mouse(this, frame);
        screen = new Panel(this);
        frame.add(screen);
        keyboard = new Keyboard(this, screen);

        frame.addMouseListener(mouse);
        frame.addMouseWheelListener(mouse);

        //initializes variables
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        buttonManager = new ButtonManager(this);
        game = new Game(this, 3);

        restart = false;

        frame.setVisible(true); //setvisible calls paintcomponent() in panel, make sure this is always last to prevent bugs
    }

    public void tick()
    {
        ticker.tick();
        mouse.tick();
    }

    public boolean getRestart()
    {
        return restart;
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }

    public void repaint()
    {
        frame.repaint();
    }

    public Mouse getMouse()
    {
        return mouse;
    }

    public ButtonManager getButtonManager()
    {
        return buttonManager;
    }

    public Game getGame()
    {
        return game;
    }

    //literally just a shorter log method
    public void log(String out)
    {
        System.out.println(out);
    }

    public Random getRandom()
    {
        return rand;
    }

    public void restart()
    {
        restart = true;
    }
}
