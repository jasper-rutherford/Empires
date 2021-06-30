package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;

import java.awt.*;

public class BackBT extends Button
{
    private GameStuff.Menus.Menu multiplayerMenu;
    private GameStuff.Menus.Menu hostLobbyMenu;

    public BackBT(Handler h, Rectangle space, boolean isActive, GameStuff.Menus.Menu multiplayerMenu, GameStuff.Menus.Menu hostLobbyMenu)
    {
        super(h, space, isActive);
        this.multiplayerMenu = multiplayerMenu;
        this.hostLobbyMenu = hostLobbyMenu;


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
        //disable current menu (host lobby menu)
        hostLobbyMenu.disable();

        //enable next menu (multiplayer menu)
        multiplayerMenu.enable();
    }
}
