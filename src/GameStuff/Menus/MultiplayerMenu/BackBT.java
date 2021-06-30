package GameStuff.Menus.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;

import java.awt.*;


public class BackBT extends Button
{
    private Menu mainMenu;
    private Menu multiplayerMenu;

    public BackBT(Handler h, Rectangle space, boolean isActive, Menu mainMenu, Menu multiplayerMenu)
    {
        super(h, space, isActive);

        //pass in the menus
        this.mainMenu = mainMenu;
        this.multiplayerMenu = multiplayerMenu;

        //set the color
        setColor(new Color(69, 176, 23));
        setBorderColor(new Color(1, 2, 9));
    }

    public void render(Graphics g)
    {
        super.render(g);

        g.setColor(Color.black);
        g.drawString("Back", getSpace().x + 20, getSpace().y + 40);
    }

    public void activate()
    {
        //disable current menu (multiplayer menu)
        multiplayerMenu.deactivate();

        //enable next menu (main menu)
        mainMenu.activate();
    }
}
