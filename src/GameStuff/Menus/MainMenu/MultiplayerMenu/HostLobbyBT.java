package GameStuff.Menus.MainMenu.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.HostLobbyMenu;

import java.awt.*;


public class HostLobbyBT extends Button
{
    private Handler h;
    private Menu multiplayerMenu;

    public HostLobbyBT(Handler h, Rectangle space, boolean isActive, Menu multiplayerMenu)
    {
        super(h, space, isActive);
        this.h = h;
        this.multiplayerMenu = multiplayerMenu;

        //set the color
        setColor(new Color(176, 41, 23));
        setBorderColor(new Color(0, 0, 0));
    }

    public void render(Graphics g)
    {
        super.render(g);

        g.setColor(Color.black);
        g.drawString("Host", getSpace().x + 20, getSpace().y + 40);
    }

    public void activate()
    {
        //deactivate current menu (multiplayer Menu)
        h.getMenuManager().remove(multiplayerMenu);

        //activate next Menu (HostLobbyMenu)
        h.getMenuManager().add(new HostLobbyMenu(h, true));
    }
}
