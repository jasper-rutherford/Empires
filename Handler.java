import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.Random;

/**
 * Handler directs information between classes. Its the hub. Most of the variables are gonna stay in this guy's scope,
 * and other classes will just have a copy of this handler.
 */
public class Handler extends JFrame implements MouseListener, MouseWheelListener
{
    private Ticker ticker;
    private Keyboard keyboard;
    private Mouse mouse;
    private Panel screen;

    private Random rand;
    private boolean restart;
    private boolean leftHeld;
    private boolean rightHeld;

    private int screenWidth;
    private int screenHeight;

    private int numTilesWide;
    private int numTilesHigh;
    private Map map;

    /**
     * Constructor for the Handler class
     */
    public Handler()
    {
        //initializes the frame aspect of the handler
        setTitle("Test");
        setSize(960, 960);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        addMouseListener(this);
        addMouseWheelListener(this);

        //these are the big bois
        ticker = new Ticker(this);
        mouse = new Mouse(this);
        screen = new Panel(this);
        add(screen);
        keyboard = new Keyboard(this, screen);

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
        map = new Map(this, numTilesWide, numTilesHigh);
    }

    /**
     * paint is called automatically very often from the JFrame, it redraws the screen. This method simply calls the
     * paint method for the JPanel (screen), because everything is actually drawn there.
     *
     * @param g the graphics object to draw things with. This is automatically generated.
     */
    @Override
    public void paint(Graphics g)
    {
        screen.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == 1)
        {
            mouse.leftClicked();
        }
        else if (e.getButton() == 3)
        {
            mouse.rightClicked();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == 1)
        {
            leftHeld = true;
        }
        else if (e.getButton() == 3)
        {
            rightHeld = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == 1)
        {
            leftHeld = false;
        }
        else if (e.getButton() == 3)
        {
            rightHeld = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        mouse.wheelMoved(e);
    }

    public void tick()
    {

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

    public Map getMap()
    {
        return map;
    }

    public void test()
    {
        System.out.println("TeST");
    }
}
