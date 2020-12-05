package Keys;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * action that closes the program
 */
public class Key extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }

    public void pressed(ActionEvent e)
    {

    }
}
