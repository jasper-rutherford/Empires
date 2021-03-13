package Framework.MouseStuff;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import Framework.*;
import GameStuff.Board;
import GameStuff.Tile;

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

        Board board = h.getGame().getBoard();

        //increases sidelength by 10% or decreases by 10% based on wheel rotation direction
        if (e.getWheelRotation() > 0 && board.getSideLength() > 30)
        {
            //updates tile sidelengths
            board.setSideLength((int) (board.getSideLength() * (.9)));

            //updates the distance between mouse and anchortile
            Point deltaPoint = mouse.getLeftMouse().getDeltaPoint();
            mouse.getLeftMouse().setDeltaPoint(new Point((int)(deltaPoint.x * .9), (int)(deltaPoint.y * .9)));
        }
        if (e.getWheelRotation() < 0 && board.getSideLength() < 130)
        {
            //updates tile sidelengths
            board.setSideLength((int) (board.getSideLength() / (.9)));

            //updates the distance between mouse and anchortile
            Point deltaPoint = mouse.getLeftMouse().getDeltaPoint();
            mouse.getLeftMouse().setDeltaPoint(new Point((int)(deltaPoint.x / .9), (int)(deltaPoint.y / .9)));
        }

        board.reload();
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
        Board board = h.getGame().getBoard();
        Tile anchor = board.getCenterTile();

        //try to set the anchortile to the tile the mouse is over
        if (mouse.leftHeld())
        {
            Tile potentialAnchor = board.getTileAt(mouse.getCoords());

            if (potentialAnchor != null)
            {
                anchor = potentialAnchor;
            }
        }

        board.setAnchorTile(anchor);

        //update the distance between the anchortile and the mouse (as this likely have changed)
        mouse.getLeftMouse().updateDelta();
    }
}