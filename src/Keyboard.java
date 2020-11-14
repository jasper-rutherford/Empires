import javax.swing.*;

/**
 * Class that handles all of the keyboard stuff.
 */
public class Keyboard
{
    private Handler h;
    private Panel screen;
    private ActionClose close;

    public Keyboard(Handler h, Panel screen)
    {
        this.h = h;
        this.screen = screen;

        setupActions();
    }

    public void setupActions()
    {
        close = new ActionClose();

        //adds the close action to the escape button
        screen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)27), "close");
        screen.getActionMap().put("close", close);
    }
}
