package GameStuff.Menus.MainMenu.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.HostLobbyMenu;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import java.awt.*;

public class MultiplayerMenu extends Menu
{
    public MultiplayerMenu(Handler h, boolean isEnabled, Menu mainMenu)
    {
        //initial menu stuff
        super(h, isEnabled);

        //Host Lobby Button
        Button hostLobbyBT = new MenuButton(
                h,
                new Rectangle(100, 100, 100, 100),
                false,
                this,
                new HostLobbyMenu(h, false, this),
                new Color(176, 41, 23),
                new Color(0, 0, 0),
                null,
                "Host");
        addButton(hostLobbyBT);

        //Join Lobby Button
        Button joinLobbyBT = new MenuButton(
                h,
                new Rectangle(300, 100, 100, 100),
                false,
                this,
                this,
                new Color(23, 143, 176),
                new Color(0, 0, 0),
                null,
                "Join");
        addButton(joinLobbyBT);

        //Back Button
        Button backBT = new MenuButton(
                h,
                new Rectangle(500, 100, 100, 100),
                false,
                this,
                mainMenu,
                new Color(69, 176, 23),
                new Color(1, 2, 9),
                null,
                "Back");
        addButton(backBT);
    }
}
