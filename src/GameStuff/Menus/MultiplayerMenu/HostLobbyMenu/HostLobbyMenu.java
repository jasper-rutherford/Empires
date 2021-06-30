package GameStuff.Menus.MultiplayerMenu.HostLobbyMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MultiplayerMenu.BackBT;
import GameStuff.Menus.MultiplayerMenu.HostLobbyBT;
import GameStuff.Menus.MultiplayerMenu.JoinLobbyBT;

import java.awt.*;

public class HostLobbyMenu extends Menu
{
    private Handler h;

    public HostLobbyMenu(Handler h, boolean isActive)
    {
        //initial menu stuff
        super(h, isActive);
        this.h = h;

        //create/add buttons
        Rectangle space1 = new Rectangle(100, 100, 100, 100);
        GameStuff.Menus.Buttons.Button codeBT = new CodeBT(h, space1, isActive);
        addButton(codeBT);

        //TODO: create back button
    }
}
