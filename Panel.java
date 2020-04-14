import javax.swing.*;
import java.awt.*;

/**
 * This class is in charge of drawing stuff to the screen for the user to see.
 */
public class Panel extends JPanel
{
    private Handler h;

    public Panel(Handler handler)
    {
        h = handler;
    }

    @Override
    public void paint(Graphics g)
    {
        //fill a black background
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, h.getScreenWidth(), h.getScreenHeight());

        //draw the map
        h.getMap().render(g);

    }
}