package GameStuff.Menus;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;

import java.awt.*;
import java.util.ArrayList;

//generic menu class
public class Menu
{
    private ArrayList<Button> buttons;
    private ArrayList<Menu> menus;

    //represents if the menu is active
    private boolean isEnabled;

    private Button foundButton;

    //Whether or not the menu should be enabled when its supermenu is enabled
    private boolean defaults;

    public Menu(Handler h, boolean isActive, boolean defaults)
    {
        buttons = new ArrayList<>();
        menus = new ArrayList<>();

        this.isEnabled = isActive;
        foundButton = null;

        h.getMenuManager().add(this);

        this.defaults = defaults;
    }

    //enables the menu
    public void enable()
    {
        //enable buttons
        for (Button button : buttons)
        {
            if (button.isDefault())
            {
                button.enable();
            }
        }

        //enable menus
        for (Menu menu : menus)
        {
            if (menu.isDefault())
            {
                menu.enable();
            }
        }

        isEnabled = true;
    }

    //disables the menu
    public void disable()
    {
        //disable buttons
        for (Button button : buttons)
        {
            button.disable();
        }

        //disable menus
        for (Menu menu : menus)
        {
            menu.disable();
        }

        isEnabled = false;
    }

    //render the stuff
    public void render(Graphics g)
    {
        if (isEnabled)
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
    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void addButton(Button button)
    {
        if (!buttons.contains(button))
        {
            buttons.add(button);
        }
    }

    public void addMenu(Menu menu)
    {
        menus.add(menu);
    }

    public boolean hasButton(Point p)
    {
        boolean buttonWasFound = false;

        //finds a button that
        for (Button button : buttons)
        {
            if (button.isEnabled() && button.contains(p))
            {
                foundButton = button;
                buttonWasFound = true;
            }
        }

        return buttonWasFound;
    }

    public Button getFoundButton()
    {
        return foundButton;
    }

    public ArrayList<Button> getButtons()
    {
        return buttons;
    }

    //gets the value of defaults
    public boolean isDefault()
    {
        return  defaults;
    }
}
