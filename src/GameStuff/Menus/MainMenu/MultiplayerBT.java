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
    private Menu mainMenu;
    private Menu multiplayerMenu;

    //deprecated
    public MultiplayerBT(Handler h, Rectangle space, boolean enabled, Menu mainMenu)
    {
        //send stuff to super
        super(h, space, enabled);

        //set up menus that this button cares about
        this.mainMenu = mainMenu;
        multiplayerMenu = new MultiplayerMenu(h, false, mainMenu);
        h.getMenuManager().add(multiplayerMenu); //add the multiplayer menu to the menu manager

//            setImage();

        //set the color
//        setColor();
//        setBorderColor();
    }

    public void activate()
    {
        //deactivate the main menu
        mainMenu.disable();

        //activate the multiplayer menu
        multiplayerMenu.enable();
    }
}
