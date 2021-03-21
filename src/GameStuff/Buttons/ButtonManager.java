package GameStuff.Buttons;

import Framework.Handler;
import GameStuff.Buttons.Button;

import java.awt.*;
import java.util.ArrayList;

public class ButtonManager
{
    private Handler h;

    private ArrayList<Button> buttons;

    public ButtonManager(Handler h)
    {
        this.h = h;
        buttons = new ArrayList<>();

    }

    public void render(Graphics g)
    {
        for (Button button : buttons)
        {
            if (button.isEnabled())
            {
                button.render(g);
            }
        }
    }

    /**
     * activates every button that is enabled and contains the point p
     * @param p the point to check
     * @return whether or not a button was activated
     */
    public boolean activateButtons(Point p)
    {
        boolean buttonActivated = false;

        for (Button button : buttons)
        {
            if (button.isEnabled() && button.contains(p))
            {
                button.activate();
                buttonActivated = true;
            }
        }

        return buttonActivated;
    }

    public void addButton(Button button)
    {
        if (!buttons.contains(button))
        {
            buttons.add(button);
        }
    }

    //enables all the buttons
    public void enableAll()
    {
        for (Button button : buttons)
        {
            button.enable();
        }
    }

    //disables all the buttons
    public void disableAll()
    {
        for (Button button : buttons)
        {
            button.disable();
        }
    }
}
