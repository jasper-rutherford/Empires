package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MultiplayerBT extends Button
{
    public MultiplayerBT(Handler h, Rectangle space, boolean enabled)
    {
        super(h, space, enabled);

//        int x = 100;
//        int y = 200;
//        int width = 208;
//        int height = 28;
//        setSpace(new Rectangle(x, y, width, height));

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
        getHandler().println("multi :D");
    }
}
