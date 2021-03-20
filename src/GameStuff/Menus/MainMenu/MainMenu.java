package GameStuff.Menus.MainMenu;

import Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;

import java.awt.*;

public class MainMenu extends Menu
{
    //handler
    private Handler h;

    public MainMenu(Handler h, boolean isActive)
    {
        super(h, isActive);

        this.h = h;

        //create/add the singleplayer button
        Button singleplayerBT = new SingleplayerBT(h, true);
        addButton(singleplayerBT);
    }
}
