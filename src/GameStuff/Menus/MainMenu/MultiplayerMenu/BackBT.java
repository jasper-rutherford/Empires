package GameStuff.Menus.MainMenu.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.MainMenu.MainMenu;
import GameStuff.Menus.Menu;

import java.awt.*;


public class BackBT extends Button
{
    private Handler h;
    private Menu multiplayerMenu;

    public BackBT(Handler h, Rectangle space, boolean isActive, Menu multiplayerMenu)
    {
        super(h, space, isActive);
        this.h = h;
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
        h.getMenuManager().remove(multiplayerMenu);

        //enable next menu (main menu)
        h.getMenuManager().add(new MainMenu(h, true));
    }
}
