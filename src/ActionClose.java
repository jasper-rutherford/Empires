import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * action that closes the program
 */
public class ActionClose extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}
