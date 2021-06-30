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

    public SingleplayerBT(Handler h, Rectangle space, boolean enabled, GameStuff.Menus.Menu mainMenu)
    {
        super(h, space, enabled);

//        int x = 100;
//        int y = 100;
//        int width = 208;
//        int height = 28;
//        setSpace(new Rectangle(x, y, width, height));

        //read in texture
        try
        {
            setImage(ImageIO.read(getClass().getResourceAsStream("/menus/mainmenu/singleplayer.png")));
        }
        catch (IOException e)
        {
            h.println("vvv Singleplayer Button failed to load image vvv");
            e.printStackTrace();
            h.println("^^^ Singleplayer Button failed to load image ^^^");
        }

        //set the color
        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(8, 49, 174));

        //set the Menu
        this.mainMenu = mainMenu;
    }

    public void activate()
    {
        getHandler().getGame().makeBoard(3);
        mainMenu.disable();
    }
}
