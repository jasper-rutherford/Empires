package Framework;

import GameStuff.Menus.MainMenu.MainMenu;
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

        //creates/adds the main menu to the list
        Menu mainMenu = new MainMenu(h, true);
        menus.add(mainMenu);
    }

    //renders all active menus
    public void render(Graphics g)
    {
        for (Menu menu : menus)
        {
            if (menu.isActive())
            {
                menu.render(g);
            }
        }
    }

    /**
     * Checks all active menus for enabled buttons, and activates every enabled button that contains point p
     * @param p the point to check
     * @return whether or not a button was activated
     */
    public boolean activateButtons(Point p)
    {
        boolean buttonActivated = false;

        //checks every menu
        for (Menu menu : menus)
        {
            //tries to activate buttons only in activated menus
            if (menu.isActive() && menu.activateButtons(p))
            {
                //if a button is activated then this is set accordingly
                buttonActivated = true;
            }
        }

        return buttonActivated;
    }

    //adds the given menu to the list
    public void add(Menu menu)
    {
        menus.add(menu);
    }
}
