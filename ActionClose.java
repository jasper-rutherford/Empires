import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionClose extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("TESTTT");
        System.exit(0);
    }
}
