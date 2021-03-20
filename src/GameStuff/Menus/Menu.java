package GameStuff.Menus;

import Buttons.Button;
import Framework.ButtonManager;
import Framework.Handler;

import java.awt.*;

//generic menu class
public class Menu
{
    private Handler h;

    //represents if the menu is active
    private boolean isActive;

    //buttonmanager to do all the button things
    private ButtonManager buttonManager;

    public Menu(Handler h, boolean isActive)
    {
        this.h = h;
        this.isActive = isActive;

        buttonManager = new ButtonManager(h);
    }

    //activates the menu
    public void activate()
    {
        buttonManager.enableAll();
        isActive = true;
    }

    //deactivates the menu
    public void deactivate()
    {
        buttonManager.disableAll();
        isActive = false;
    }

    //render the stuff
    public void render(Graphics g)
    {
        buttonManager.render(g);
    }

    //returns whether the menu is active
    public boolean isActive()
    {
        return isActive;
    }

    public void addButton(Button button)
    {
        buttonManager.addButton(button);
    }

    /**
     * tells the button manager to activate buttons relative to point p (if a button contains the point it activates)
     *
     * @param p the point to check
     */
    public boolean activateButtons(Point p)
    {
        return buttonManager.activateButtons(p);
    }
}
