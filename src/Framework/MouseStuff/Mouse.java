package Framework.MouseStuff;

import Framework.Frame;
import Framework.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Class that does stuff when the mouse does stuff. Clicks, wheel movement, coordinates.
 */
public class Mouse implements MouseListener, MouseWheelListener
{
    private Handler h;
    private Frame frame;
    private LeftMouse leftMouse;
    private RightMouse rightMouse;
    private MouseWheel mouseWheel;

    private boolean leftHeld;
    private boolean rightHeld;

    private Point coords;
    private Point prevCoords;

    private Point pressedPointL;
    private Point pressedPointR;

    private boolean leftClick;
    private boolean rightClick;

    //the distance the mouse can move in either direction and still be considered a click
    private int clickMargin;


    /**
     * Constructor for the Framework.MouseStuff.Mouse Class.
     *
     * @param h the game's handler. This is passed in so that the mouse can access whatever variables are needed.
     */
    public Mouse(Handler h, Frame frame)
    {
        this.h = h;
        this.frame = frame;
        leftMouse = new LeftMouse(h, this);
        rightMouse = new RightMouse(h, this);
        mouseWheel = new MouseWheel(h, this);

        leftHeld = false;
        rightHeld = false;
        coords = getCoords();

        pressedPointL = coords;
        pressedPointR = coords;

        leftClick = false;
        rightClick = false;

        clickMargin = 10;
    }

    /**
     * Method is called whenever a mouse button is pressed and released without moving the mouse
     * Method is called automatically through mouselistener
     *
     * @param e A mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    /**
     * automatically called through mouselistener
     * Activates whenever a button on the mouse is pressed down
     * Filters what button was pressed and calls the method specific to that button.
     *
     * @param e a MouseEvent sent by the mouselistener
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == 1)
        {
            leftHeld = true;
            leftMouse.pressed();

            pressedPointL = getCoords();
            leftClick = true;
        }
        else if (e.getButton() == 3)
        {
            rightHeld = true;
            rightMouse.pressed();

            pressedPointR = getCoords();
            rightClick = true;
        }
    }

    /**
     * Automatically called through mouselistener
     * Activates whenever a button on the mouse is released.
     * Filters what button was released and calls the method specific to that button.
     *
     * @param e a MouseEvent sent by the mouseListener
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == 1)
        {
            leftHeld = false;
            leftMouse.released();

            if (leftClick)
            {
                leftMouse.clicked();
                leftClick = false;
            }
        }
        else if (e.getButton() == 3)
        {
            rightHeld = false;
            rightMouse.released();

            if (rightClick)
            {
                rightMouse.clicked();
                rightClick = false;
            }
        }
    }

    /**
     * Automatically called through mouselistener
     * Activates whenever the mouse cursor enters the JPanel.
     *
     * @param e a MouseEvent sent by the mouseListener
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    /**
     * Automatically called through mouselistener
     * Activates whenever a the mouse cursor exits the JPanel.
     *
     * @param e a MouseEvent sent by the mouseListener
     */
    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    /**
     * Automatically called through mouselistener
     * Activates whenever a the wheel on the mouse moves.
     * calls the Zoom method to zoom the screen.
     *
     * @param e a MouseEvent sent by the mouseListener
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        mouseWheel.wheelMoved(e);
    }

    /**
     * gets the current coordinates of the mouse relative to the handler/frame
     *
     * @return a Point object containing the mouse's coordinates
     */
    public Point getCoords()
    {
        int screenX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int screenY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        Point point = new Point(screenX, screenY);
        SwingUtilities.convertPointFromScreen(point, frame);

        return point;
    }

    /**
     * called once every tick, helps catch some of the tasks that the listeners dont deal with
     */
    public void tick()
    {
        cycleCoords();

        if (leftHeld)
        {
            leftMouse.held();
        }
        if (rightHeld)
        {
            rightMouse.held();
        }
    }

    /**
     * updates prevCoordinates to last tick's coordinates and updates the current coordinates
     */
    public void cycleCoords()
    {
        prevCoords = coords;
        coords = getCoords();

        if (coords.distance(pressedPointL) > clickMargin)
        {
            leftClick = false;
        }

        if (coords.distance(pressedPointR) > clickMargin)
        {
            rightClick = false;
        }
    }

    public int getCurrentX()
    {
        return getCoords().x;
    }

    public int getCurrentY()
    {
        return getCoords().y;
    }

    public boolean leftHeld()
    {
        return leftHeld;
    }

    public Point getPrevCoords()
    {
        return prevCoords;
    }

    public MouseWheel getMouseWheel()
    {
        return mouseWheel;
    }

    public LeftMouse getLeftMouse()
    {
        return leftMouse;
    }
}
