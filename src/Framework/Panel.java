package Framework;

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
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //fill a black background
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, h.getScreenWidth(), h.getScreenHeight());


        h.getGame().render(g);


//        drawCrosshair(g);
    }

    public Handler getHandler()
    {
        return h;
    }

    public void drawCrosshair(Graphics g)
    {
        g.setColor(Color.red);
        g.drawLine(h.getScreenWidth() / 2, h.getScreenHeight(), h.getScreenWidth() / 2, 0);
        g.drawLine(h.getScreenWidth(), h.getScreenHeight() / 2, 0, h.getScreenHeight() / 2);
    }
}