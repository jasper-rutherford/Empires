package Framework.MouseStuff;

import Framework.Handler;
import Stuff.Tile;

import java.awt.*;

public class LeftMouse
{
    private Handler h;
    private Mouse mouse;


    public LeftMouse(Handler h, Mouse mouse)
    {
        this.h = h;
        this.mouse = mouse;
    }

    public void pressed()
    {

    }

    public void released()
    {

    }

    public void clicked()
    {
        if (!h.getButtonManager().activateButtons(mouse.getCoords()))
        {
            h.getBoard().select(h.getBoard().getTileAt(mouse.getCoords()));
        }
    }

    public void held()
    {
        moveTiles();
    }

    /**
     * moves the tiles the distance from last tick's mouse coordinates to this tick's mouse coordinates
     */
    public void moveTiles()
    {
        //the difference between last tick's mouse coordinates and this tick's mouse coordinates
        int xDiff = mouse.getCoords().x - mouse.getPrevCoords().x;
        int yDiff = mouse.getCoords().y - mouse.getPrevCoords().y;

        //move the screen (if either above case triggered, yDiff is zero)
        Tile anchorTile = h.getBoard().getAnchorTile();
        anchorTile.getHex().moveCenter(xDiff, yDiff);

        h.getBoard().reload();
    }
}
