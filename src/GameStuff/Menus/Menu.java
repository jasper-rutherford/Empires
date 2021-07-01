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

    public Menu(Handler h, boolean isActive)
    {
        buttons = new ArrayList<>();
        menus = new ArrayList<>();

        this.isEnabled = isActive;
        foundButton = null;

        h.getMenuManager().add(this);
    }

    //enables the menu
    public void enable()
    {
        //enable buttons
        for (Button button : buttons)
        {
            button.enable();
        }

        //enable menus
        for (Menu menu : menus)
        {
            menu.enable();
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

    /**
     * tells the button manager to activate buttons relative to point p (if a button contains the point it activates)
     *
//     * @param p the point to check
//     */
//    public boolean activateButtons(Point p)
//    {
//        boolean buttonFound = false;
//        Button clicked;
//
//        //finds a button that
//        for (Button button : buttons)
//        {
//            if (button.isEnabled() && button.contains(p))
//            {
//                clicked = button;
//                buttonFound = true;
//            }
//        }
//
//        if (buttonActivated)
//        {
//            clicked.activate();
//        }
//
//        return buttonActivated;
//    }

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
}
