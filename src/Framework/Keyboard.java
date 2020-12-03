package Framework;

import Keys.EscapeKey;
import Keys.RKey;
import Keys.SKey;
import Keys.WKey;

import javax.swing.*;

/**
 * Class that handles all of the keyboard stuff.
 */
public class Keyboard
{
    private Handler h;
    private Panel screen;
    private EscapeKey close;

    public Keyboard(Handler h, Panel screen)
    {
        this.h = h;
        this.screen = screen;

        setupKeys();
    }

    public void setupKeys()
    {
        //Escape
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)27), "esc");
        screen.getActionMap().put("esc", new EscapeKey());

        //S
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "s");
        screen.getActionMap().put("s", new SKey(h));

        //W
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "w");
        screen.getActionMap().put("w", new WKey(h));

        //R
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('r'), "r");
        screen.getActionMap().put("r", new RKey(h));
    }
}
