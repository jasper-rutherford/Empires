package Actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Framework.Handler;
import Framework.MouseStuff.Mouse;

/**
 * action that spawns a unit at the mouse's location
 */
public class ActionSpawn extends AbstractAction
{
    private Handler h;
    private int numSpawns;


    public ActionSpawn(Handler h)
    {
        super();

        this.h = h;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        numSpawns++;

        Mouse mouse = h.getMouse();
        h.getGame().getBoard().getTileAt(mouse.getCoords()).createUnit(numSpawns);
    }
}
