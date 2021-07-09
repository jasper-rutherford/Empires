package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SingleplayerBT extends Button
{
    private Menu mainMenu;

    //doomed to be deprecated
    //aka TODO: kill this/create an actual singleplayer menu
    public SingleplayerBT(Handler h, Rectangle space, boolean enabled, GameStuff.Menus.Menu mainMenu)
    {
        super(h, enabled, true, space);
        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(8, 49, 174));
        setImage("/menus/mainmenu/singleplayer.png");
        this.mainMenu = mainMenu;
    }

    public void activate()
    {
        getHandler().getGame().makeBoard(3);
        mainMenu.disable();
    }
}
