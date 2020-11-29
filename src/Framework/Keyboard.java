package Framework;

import Keys.EscapeKey;
import Keys.SKey;

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
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)27), "close");
        screen.getActionMap().put("close", new EscapeKey());

        //S
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "spawn");
        screen.getActionMap().put("spawn", new SKey(h));
    }
}
