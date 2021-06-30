package Framework;

import Framework.MouseStuff.Mouse;
import GameStuff.Menus.MainMenu.MainMenu;
import GameStuff.Menus.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
    private Game game;
    private MenuManager menuManager;

    private boolean restart;

    private int screenWidth;
    private int screenHeight;

    private Random rand;

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

        //try to read in the icon
        try
        {
            Image icon = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
            frame.setIconImage(icon);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed to load textures in Board creation");
        }

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

        //create menumanager
        menuManager = new MenuManager(this);

        //creates/adds the main menu to the list
        Menu mainMenu = new MainMenu(this, true);
        menuManager.add(mainMenu);

        //TODO: move this
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

    public void repaint()
    {
        frame.repaint();
    }

    public Mouse getMouse()
    {
        return mouse;
    }

    public Game getGame()
    {
        return game;
    }

    //literally just a shorter print method
    public void println(String out)
    {
        System.out.println(out);
    }

    public Random getRandom()
    {
        return rand;
    }

    public MenuManager getMenuManager()
    {
        return menuManager;
    }
}
