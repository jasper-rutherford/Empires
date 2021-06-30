package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu;

import Framework.Handler;
import GameStuff.Menus.Menu;

import java.awt.*;

public class HostLobbyMenu extends Menu
{
    private Handler h;
    private Menu multiplayerMenu;

    public HostLobbyMenu(Handler h, boolean isActive, Menu multiplayerMenu)
    {
        //initial menu stuff
        super(h, isActive);
        this.h = h;
        this.multiplayerMenu = multiplayerMenu;

        //create/add buttons
        Rectangle space1 = new Rectangle(100, 100, 100, 100);
        GameStuff.Menus.Buttons.Button codeBT = new CodeBT(h, space1, isActive);
        addButton(codeBT);

        Rectangle space2 = new Rectangle(300, 100, 100, 100);
        GameStuff.Menus.Buttons.Button backBT = new BackBT(h, space2, isActive, multiplayerMenu, this);
        addButton(backBT);
    }
}
