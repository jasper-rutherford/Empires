package GameStuff.Menus.MainMenu.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;

import java.awt.*;

public class MultiplayerMenu extends Menu
{
    private Handler h;

    public MultiplayerMenu(Handler h, boolean isActive)
    {
        //initial menu stuff
        super(h, isActive);
        this.h = h;

        //create/add buttons
        Rectangle space1 = new Rectangle(100, 100, 100, 100);
        Button hostLobbyBT = new HostLobbyBT(h, space1, isActive, this);
        addButton(hostLobbyBT);

        //create/add buttons
        Rectangle space2 = new Rectangle(300, 100, 100, 100);
        Button joinLobbyBT = new JoinLobbyBT(h, space2, isActive);
        addButton(joinLobbyBT);

        //create/add buttons
        Rectangle space3 = new Rectangle(500, 100, 100, 100);
        Button backBT = new BackBT(h, space3, isActive, this);
        addButton(backBT);
    }
}
