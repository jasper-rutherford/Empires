import java.awt.event.MouseWheelEvent;

/**
 * Class that does stuff when the mouse does stuff. Clicks, wheel movement, coordinates.
 */
public class Mouse
{
    private Handler h;

    /**
     * Constructor for the Mouse Class.
     * @param h the game's handler. This is passed in so that the mouse can access whatever variables are needed.
     */
    public Mouse(Handler h)
    {
        this.h = h;
    }

    /**
     * Runs whenever the left mouse button is pressed and released without moving.
     */
    public void leftClicked()
    {

    }

    /**
     * Runs whenever the right mouse button is pressed and released without moving.
     */
    public void rightClicked()
    {

    }

    /**
     * Is triggered whenever the mousewheel is moved.
     * @param e a mouseWheelEvent to pull data from
     */
    public void wheelMoved(MouseWheelEvent e)
    {

    }
}
