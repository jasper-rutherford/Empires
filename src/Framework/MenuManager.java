package Framework;

import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;

import java.awt.*;
import java.util.ArrayList;

//the hub of menu activity
public class MenuManager
{
    private Handler h;

    //list of all the menus
    private ArrayList<Menu> menus;

    //creates and sets up all the menus
    public MenuManager(Handler h)
    {
        this.h = h;
        menus = new ArrayList<>();
    }

    //renders all active menus
    public void render(Graphics g)
    {
        for (Menu menu : menus)
        {
            if (menu.isEnabled())
            {
                menu.render(g);
            }
        }
    }

    /**
     * Activates the first button (from the top layer of menus to the bottom) that contains the point p
     *
     * @param p the point to check
     * @return whether or not a button was activated
     */
    public boolean activateButtons(Point p)
    {
        boolean buttonActivated = false;

        //traverses the list in reverse order - ensures that a button from the topmost menu is activated
        for (int lcv = menus.size() - 1; lcv >= 0; lcv--)
        {
            Menu menu = menus.get(lcv);

            //if the given menu is currently enabled
            if (menu.isEnabled())
            {
                //checks for a button on the menu that contains point p
                for (Button button : menu.getButtons())
                {
                    //if such a button is found it is activated and we stop looking for buttons
                    //checks if the button is enabled because i feel that not doing that could cause problems later
                    if (button.isEnabled() && button.contains(p))
                    {
                        button.activate();
                        buttonActivated = true;
                        lcv = -1;
                        break;
                    }
                }
            }
        }

        //return whether a button was activated
        return buttonActivated;
    }

    //adds the given menu to the list
    public void add(Menu menu)
    {
        menus.add(menu);
    }
}
