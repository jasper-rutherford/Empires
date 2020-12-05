package Keys;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Framework.Handler;

/**
 * action that spawns a unit at the mouse's location
 */
public class RKey extends AbstractAction
{
    private Handler h;
    private int numSpawns;


    public RKey(Handler h)
    {
        super();

        this.h = h;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       h.restart();
    }
}
