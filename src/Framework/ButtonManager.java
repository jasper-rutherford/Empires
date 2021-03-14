package Framework;

import Buttons.Button;
import Buttons.EndTurnButton;

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

        buttons.add(new EndTurnButton(h, new Color(99, 28, 215)));
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

    public boolean activateButtons(Point p)
    {
        boolean buttonActivated = false;

        for (Button button : buttons)
        {
            if (!buttonActivated && button.isEnabled() && button.contains(p))
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
}
