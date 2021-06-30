package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MultiplayerMenu.MultiplayerMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MultiplayerBT extends Button
{
    private Menu mainMenu;
    private Menu multiplayerMenu;

    public MultiplayerBT(Handler h, Rectangle space, boolean enabled, Menu mainMenu)
    {
        //send stuff to super
        super(h, space, enabled);

        //set up menus that this button cares about
        this.mainMenu = mainMenu;
        multiplayerMenu = new MultiplayerMenu(h, false, mainMenu);
        h.getMenuManager().add(multiplayerMenu); //add the multiplayer menu to the menu manager

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
        //deactivate the main menu
        mainMenu.deactivate();

        //activate the multiplayer menu
        multiplayerMenu.activate();
    }
}
