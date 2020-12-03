package Keys;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Framework.Handler;
import Framework.MouseStuff.Mouse;
import GameStuff.Player;
import GameStuff.Tile;
import GameStuff.Units.Unit;
import GameStuff.Units.Worker;

/**
 * action that spawns a unit at the mouse's location
 */
public class WKey extends AbstractAction
{
    private Handler h;
    private int numSpawns;


    public WKey(Handler h)
    {
        super();

        this.h = h;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //the number of units that have been spawned. Each unit stores this number as their ID
        numSpawns++;

        Mouse mouse = h.getMouse();

        //the tile to spawn the unit on (the tile the mouse is above)
        Tile spawnTile = h.getGame().getBoard().getTileAt(mouse.getCoords());

        //a new worker, tied to the spawnTile
        Unit newUnit = new Worker(h, spawnTile, numSpawns, h.getGame().getCurrentPlayerIndex() + 1);

        //add the unit to the spawnTile
        spawnTile.addUnit(newUnit);

        //get the current player
        Player currentPlayer = h.getGame().getCurrentPlayer();

        //add the unit to the player's list of units
        currentPlayer.addUnit(newUnit);

        //select the tile and the new unit
        h.getGame().getBoardManager().selectTile(spawnTile, newUnit);
    }
}
