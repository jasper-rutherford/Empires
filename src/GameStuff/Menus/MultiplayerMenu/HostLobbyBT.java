package GameStuff.Menus.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MultiplayerMenu.HostLobbyMenu.HostLobbyMenu;

import java.awt.*;


public class HostLobbyBT extends Button
{
    private Menu mainMenu;
    private Menu hostLobbyMenu;

    public HostLobbyBT(Handler h, Rectangle space, boolean isActive, Menu mainMenu)
    {
        super(h, space, isActive);

        //set up the menus
        this.mainMenu = mainMenu;
        hostLobbyMenu = new HostLobbyMenu(h, false);
        h.getMenuManager().add(hostLobbyMenu); //hostLobbyMenu is new and must be added to the manager

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
        //deactivate current menu (Main Menu)
        mainMenu.deactivate();

        //activate next Menu (HostLobbyMenu)
        hostLobbyMenu.deactivate();
    }
}
