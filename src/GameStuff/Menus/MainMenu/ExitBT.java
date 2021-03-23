package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ExitBT extends Button
{
    public ExitBT(Handler h, Rectangle space, boolean enabled)
    {
        super(h, space, enabled);

//        int x = 100;
//        int y = 400;
//        int width = 208;
//        int height = 28;
//        setSpace(new Rectangle(x, y, width, height));

        //read in texture
        try
        {
            setImage(ImageIO.read(getClass().getResourceAsStream("/menus/mainmenu/exit.png")));
        }
        catch (IOException e)
        {
            h.println("vvv Exit Button failed to load image vvv");
            e.printStackTrace();
            h.println("^^^ Exit Button failed to load image ^^^");
        }

        //set the color
        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(8, 49, 174));
    }

    public void activate()
    {
        System.exit(0);
    }
}
