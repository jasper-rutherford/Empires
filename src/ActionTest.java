import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionTest extends AbstractAction
{
    private Handler h;

    public ActionTest(Handler h)
    {
        this.h = h;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        h.getBoard().reload();
    }
}
