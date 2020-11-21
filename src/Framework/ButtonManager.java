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

        buildEndTurnButton();
    }

    public void buildEndTurnButton()
    {
        int screenWidth = h.getScreenWidth();
        int screenHeight = h.getScreenHeight();

        int[] endTurnXPoints = {screenWidth - 100, screenWidth - 10, screenWidth - 10, screenWidth - 100};
        int[] endTurnYPoints = {screenHeight - 100, screenHeight - 100, screenHeight - 10, screenHeight - 10};

        EndTurnButton endTurnButton = new EndTurnButton(h, new Polygon(endTurnXPoints, endTurnYPoints, 4), true, new Color(99, 28, 215));

        buttons.add(endTurnButton);
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
}
