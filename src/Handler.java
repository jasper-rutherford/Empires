import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Handler directs information between classes. Its the hub. Most of the variables are gonna stay in this guy's scope,
 * and other classes will just have a copy of this handler.
 */
public class Handler// extends JFrame
{
    private Ticker ticker;
    private Keyboard keyboard;
    private Mouse mouse;
    private Panel screen;
    private Frame frame;

    private Random rand;
    private boolean restart;
    private boolean leftHeld;
    private boolean rightHeld;

    private int screenWidth;
    private int screenHeight;

    private int numTilesWide;
    private int numTilesHigh;
    private Board board;

    private int sideLength;

    /**
     * when zooming or moving the screen around this tile is the tile everything is generated around.
     * This means when zooming this tile stays in place.
     */
    private Tile anchorTile;

    /**
     * Constructor for the Handler class
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
        rand = new Random();

        leftHeld = false;
        rightHeld = false;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;

        restart = false;

        numTilesWide = 75;
        numTilesHigh = 50;
        sideLength = 50;

        anchorTile = new Tile(this, -1, -1);
        anchorTile.setValidity(false);

        board = new Board(this, numTilesWide, numTilesHigh);

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

    public Board getBoard()
    {
        return board;
    }

    public int getSideLength()
    {
        return sideLength;
    }

    public void setAnchorTile(Tile tile)
    {
        if (tile.isValid())
        {
            if (anchorTile.isValid())
            {
                anchorTile.setColor(Color.blue);
            }
            anchorTile = tile;
            anchorTile.setColor(Color.green);
        }
    }

    public Tile getAnchorTile()
    {
        return anchorTile;
    }

    public void setSideLength(int s)
    {
        sideLength = s;
    }

    public int getNumTilesWide()
    {
        return numTilesWide;
    }

    public int getNumTilesHigh()
    {
        return numTilesHigh;
    }

    public void repaint()
    {
        frame.repaint();
    }
}
