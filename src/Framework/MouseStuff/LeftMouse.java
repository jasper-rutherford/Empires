package Framework.MouseStuff;

import Framework.Handler;
import GameStuff.Tile;

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
        //try to click any buttons on that location
        if (!h.getButtonManager().activateButtons(mouse.getCoords()))
        {
            //if there were no buttons to press, select the unit on the board at that location
            h.getGame().getBoardManager().considerTile(h.getGame().getBoard().getTileAt(h.getMouse().getCoords()));
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
        Tile anchorTile = h.getGame().getBoard().getAnchorTile();
        anchorTile.getHex().moveCenter(xDiff, yDiff);

        h.getGame().getBoard().reload();
    }
}
