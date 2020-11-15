package Framework.MouseStuff;

import java.awt.event.MouseWheelEvent;
import Framework.*;
import Stuff.Tile;

public class MouseWheel
{
    private Handler h;
    private Mouse mouse;

    public MouseWheel(Handler h, Mouse mouse)
    {
        this.h = h;
        this.mouse = mouse;
    }

    public void wheelMoved(MouseWheelEvent e)
    {
        zoom(e);
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
        if (e.getWheelRotation() > 0 && h.getBoard().getSideLength() > 30)
        {
            h.getBoard().setSideLength((int) (h.getBoard().getSideLength() * (.9)));
        }
        if (e.getWheelRotation() < 0 && h.getBoard().getSideLength() < 130)
        {
            h.getBoard().setSideLength((int) (h.getBoard().getSideLength() / (.9)));
        }

        h.getBoard().reload();
    }

    /**
     * updates the anchortile
     *
     * makes the tile at the center of the screen the anchortile
     * then tries to make the anchortile the tile the mouse is over
     *
     */
    public void updateAnchorTile()
    {
        Tile anchor = h.getBoard().getCenterTile();

        //try to set the anchortile to the tile the mouse is over
        if (mouse.leftHeld())
        {
            Tile potentialAnchor = h.getBoard().getTileAt(mouse.getCoords());

            if (potentialAnchor != null)
            {
                anchor = potentialAnchor;
            }
        }

        h.getBoard().setAnchorTile(anchor);
    }
}