package GameStuff.Menus.MainMenu.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.HostLobbyMenu;

import java.awt.*;


public class HostLobbyBT extends Button
{
    private Menu multiplayerMenu;
    private Menu hostLobbyMenu;

    public HostLobbyBT(Handler h, Rectangle space, boolean isActive, Menu multiplayerMenu)
    {
        super(h, space, isActive);

        //set up the menus
        this.multiplayerMenu = multiplayerMenu;
        hostLobbyMenu = new HostLobbyMenu(h, false, multiplayerMenu);
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
        //deactivate current menu (multiplayer Menu)
        multiplayerMenu.disable();

        //activate next Menu (HostLobbyMenu)
        hostLobbyMenu.enable();
    }
}
