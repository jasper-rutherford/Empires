package Framework.MouseStuff;

import Framework.Handler;
import GameStuff.Board.Tile;

import java.awt.*;

public class LeftMouse
{
    private Handler h;
    private Mouse mouse;

    //the distance from the mouse coordinates to the anchor tile's coordinates, used for moving the screen
    private int deltaX;
    private int deltaY;


    public LeftMouse(Handler h, Mouse mouse)
    {
        this.h = h;
        this.mouse = mouse;
        deltaX = -1;
        deltaY = -1;
    }

    //when the left button is pressed
    public void pressed()
    {
        if (h.getGame().hasBoard())
            updateDelta();
    }

    //when the left button is released
    public void released()
    {

    }

    //when the left button is pressed and released without moving too much
    public void clicked()
    {
        //try to click any buttons on that location (via the menuManager and the generic buttonManager)
        if (!h.getMenuManager().activateButtons(mouse.getCoords()))
        {
            //if there were no buttons to press and there is a board, attempt to select the tile on the board at the mouse's location
            if (h.getGame().hasBoard())
            {
                h.getGame().getBoardManager().selectMouseTile();
            }
        }
    }

    //while the left button is held down
    public void held()
    {
        if (h.getGame().hasBoard())
        {
            moveTiles();
        }
    }

    /**
     * updates the anchortile's position to be the same distance from the mouse as it was when the left button was initially pressed
     */
    public void moveTiles()
    {
        //calculates the new center
        Point newCenter = new Point(deltaX + mouse.getCurrentX(), deltaY + mouse.getCurrentY());

        //updates the center
        Tile anchorTile = h.getGame().getBoard().getAnchorTile();
        anchorTile.getHex().setCenter(newCenter);

        //reloads the board
        h.getGame().getBoard().reload();
    }

    public Point getDeltaPoint()
    {
        return new Point(deltaX, deltaY);
    }

    public void setDeltaPoint(Point p)
    {
        deltaX = p.x;
        deltaY = p.y;
    }

    //update the distance from the mouse to the anchortile
    public void updateDelta()
    {
        deltaX = h.getGame().getBoard().getAnchorTile().getXCoord() - mouse.getCurrentX();
        deltaY = h.getGame().getBoard().getAnchorTile().getYCoord() - mouse.getCurrentY();
    }

    //update the distance from the mouse to the anchortile
    public void updateDelta(Tile anchor)
    {
        deltaX = anchor.getXCoord() - mouse.getCurrentX();
        deltaY = anchor.getYCoord() - mouse.getCurrentY();
    }
}
