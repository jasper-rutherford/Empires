package GameStuff.Menus.Multiplayer;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.MainMenu.SettingsBT;
import GameStuff.Menus.Menu;

import java.awt.*;

public class MultiplayerMenu extends Menu
{
    private Handler h;

    //TODO add this to mainmenus creation and add this to the menu list when that happens
    public MultiplayerMenu(Handler h, boolean isActive)
    {
        //initial menu stuff
        super(h, isActive);
        this.h = h;

        //create/add buttons
        Rectangle settings = new Rectangle(1,1,1,1);
        Button hostLobbyBT = new SettingsBT(h, settings, isActive);
        addButton(hostLobbyBT);
    }
}
