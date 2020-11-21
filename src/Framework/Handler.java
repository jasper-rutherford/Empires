package Framework;

import Framework.MouseStuff.Mouse;
import GameStuff.Board;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Framework.Handler directs information between classes. Its the hub. Most of the variables are gonna stay in this guy's scope,
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

//    private Board board;

    /**
     * Constructor for the Framework.Handler class
     */
    public Handler()
    {

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
        game = new Game(this);

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

//    public Board getBoard()
//    {
//        return board;
//    }

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
}
