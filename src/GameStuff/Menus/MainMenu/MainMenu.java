package GameStuff.Menus.MainMenu;

import GameStuff.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;

public class MainMenu extends Menu
{
    //handler
    private Handler h;

    public MainMenu(Handler h, boolean isActive)
    {
        super(h, isActive);

        this.h = h;

        //create/add the four buttons
        Button singleplayerBT = new SingleplayerBT(h, true);
        addButton(singleplayerBT);

        Button multiplayerBT = new MultiplayerBT(h, true);
        addButton(multiplayerBT);

        Button settingsBT = new SettingsBT(h, true);
        addButton(settingsBT);

        Button exitBT = new ExitBT(h, true);
        addButton(exitBT);
    }
}
