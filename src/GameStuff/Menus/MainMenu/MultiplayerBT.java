package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MainMenu.MultiplayerMenu.MultiplayerMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MultiplayerBT extends Button
{
    private Handler h;

    private Menu mainMenu;

    public MultiplayerBT(Handler h, Rectangle space, boolean enabled, Menu mainMenu)
    {
        //send stuff to super
        super(h, space, enabled);

        //keep necessary stuff
        this.h = h;
        this.mainMenu = mainMenu;

        //read in texture
        try
        {
            setImage(ImageIO.read(getClass().getResourceAsStream("/menus/mainmenu/multiplayer.png")));
        }
        catch (IOException e)
        {
            h.println("vvv Multiplayer Button failed to load image vvv");
            e.printStackTrace();
            h.println("^^^ Multiplayer Button failed to load image ^^^");
        }

        //set the color
        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(8, 49, 174));
    }

    public void activate()
    {
        //remove the main menu from the menu manager
        h.getMenuManager().remove(mainMenu);

        //create and add a multiplayer menu to the menu manager
        h.getMenuManager().add(new MultiplayerMenu(h, true));
    }
}
