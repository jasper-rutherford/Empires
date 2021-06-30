package GameStuff.Menus;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;

import java.awt.*;
import java.util.ArrayList;

//generic menu class
public class Menu
{
    private Handler h;

    private ArrayList<Button> buttons;

    //represents if the menu is active
    private boolean isActive;

    public Menu(Handler h, boolean isActive)
    {
        this.h = h;
        this.isActive = isActive;
        buttons = new ArrayList<>();
    }

    //activates the menu
    public void enable()
    {
        for (Button button : buttons)
        {
            button.enable();
        }

        isActive = true;
    }

    //deactivates the menu
    public void disable()
    {
        for (Button button : buttons)
        {
            button.disable();
        }
        isActive = false;
    }

    //render the stuff
    public void render(Graphics g)
    {
        if (isActive)
        {
            for (Button button : buttons)
            {
                if (button.isEnabled())
                {
                    button.render(g);
                }
            }
        }
    }

    //returns whether the menu is active
    public boolean isActive()
    {
        return isActive;
    }

    public void addButton(Button button)
    {
        if (!buttons.contains(button))
        {
            buttons.add(button);
        }
    }

    /**
     * tells the button manager to activate buttons relative to point p (if a button contains the point it activates)
     *
     * @param p the point to check
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
}
