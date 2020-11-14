import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;

/**
 * Class that does stuff when the mouse does stuff. Clicks, wheel movement, coordinates.
 */
public class Mouse implements MouseListener, MouseWheelListener
{
    private Handler h;
    private Frame frame;
    private boolean leftHeld;
    private boolean rightHeld;
    private int mouseX;
    private int mouseY;
    private int prevMouseX;
    private int prevMouseY;
    private Tile mouseTile;

    /**
     * Constructor for the Mouse Class.
     *
     * @param h the game's handler. This is passed in so that the mouse can access whatever variables are needed.
     */
    public Mouse(Handler h, Frame frame)
    {
        this.h = h;
        this.frame = frame;
        leftHeld = false;
        rightHeld = false;
        Point point = getMouseCoords();
        mouseX = point.x;
        mouseY = point.y;
        mouseTile = new Tile(h, -1, -1);
        mouseTile.setValidity(false);
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
            leftPressed();
        }
        else if (e.getButton() == 3)
        {
            rightHeld = true;
            rightPressed();
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
            leftReleased();
        }
        else if (e.getButton() == 3)
        {
            rightHeld = false;
            rightReleased();
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
        zoom(e);
    }

    /**
     * gets the current coordinates of the mouse relative to the handler/frame
     *
     * @return a Point object containing the mouse's coordinates
     */
    public Point getMouseCoords()
    {
        int screenX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int screenY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        Point point = new Point(screenX, screenY);
        SwingUtilities.convertPointFromScreen(point, frame);

        return point;
    }

    /**
     * method to respond whenever the left mouse button is pressed down.
     */
    public void leftPressed()
    {
        updateCoords();
    }

    /**
     * method to respond whenever the right mouse button is pressed down.
     */
    public void rightPressed()
    {

    }

    /**
     * method to respond whenever the left mouse button is released.
     */
    private void leftReleased()
    {

    }

    /**
     * method to respond whenever the right mouse button is released.
     */
    private void rightReleased()
    {

    }

    /**
     * Method is called once a tick if the left mouse button is held
     */
    public void leftHeld()
    {
        moveTiles();
    }

    /**
     * Method is called once a tick if the right mouse button is held
     */
    public void rightHeld()
    {

    }

    /**
     * moves the tiles the distance from last tick's mouse coordinates to this tick's mouse coordinates
     */
    public void moveTiles()
    {
        //the difference between last tick's mouse coordinates and this tick's mouse coordinates
        int xDiff = mouseX - prevMouseX;
        int yDiff = mouseY - prevMouseY;

        //move the screen (if either above case triggered, yDiff is zero)
        Tile anchorTile = h.getAnchorTile();
        anchorTile.getHex().moveCenter(xDiff, yDiff);

        h.getBoard().reload();
    }

    /**
     * zooms in/out of the screen
     * <p>
     * if left mouse is held, the tile the mouse is over remains in place and everything adjusts around it
     * otherwise the map adjusts around the tile at the center of the screen
     */
    public void zoom(MouseWheelEvent e)
    {
        updateAnchorTile();

        //increases sidelength by 10% or decreases by 10% based on wheel rotation direction
        if (e.getWheelRotation() > 0 && h.getSideLength() > 30)
        {
            h.setSideLength((int) (h.getSideLength() * (.9)));
        }
        if (e.getWheelRotation() < 0 && h.getSideLength() < 130)
        {
            h.setSideLength((int) (h.getSideLength() / (.9)));
        }

        h.getBoard().reload();
    }

    /**
     * updates the anchortile
     * First tries to make the anchortile the tile the mouse is over
     * then if that fails try to make the tile at the center of the screen the anchortile
     * <p>
     * "fail" means it gets an invalid tile
     */
    public void updateAnchorTile()
    {
        Tile anchor = new Tile(h, -1, -1);
        anchor.setValidity(false); //default to setting an invalid tile

        //try to set the anchortile to the tile the mouse is over
        if (leftHeld)
        {
            Point point = getMouseCoords();
            anchor = h.getBoard().getTileAt(point);
        }

        if (!anchor.isValid()) //if no anchortile has already been set
        {
            anchor = h.getBoard().getCenterTile();
        }

        h.setAnchorTile(anchor); //if anchor is still invalid setAnchorTile() can handle that
    }

    /**
     * called once every tick, helps catch some of the tasks that the listeners dont deal with
     */
    public void tick()
    {
        cycleCoords();

        if (leftHeld)
        {
            leftHeld();
        }
        if (rightHeld)
        {
            rightHeld();
        }
    }

    /**
     * updates prevCoordinates to last tick's coordinates and updates the current coordinates
     */
    public void cycleCoords()
    {
        prevMouseX = mouseX;
        prevMouseY = mouseY;

        updateCoords();
    }

    /**
     * updates the mouse's current coordinates
     */
    public void updateCoords()
    {
        Point point = getMouseCoords();
        mouseX = point.x;
        mouseY = point.y;
    }
}
