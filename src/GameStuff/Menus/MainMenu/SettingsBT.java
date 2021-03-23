package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SettingsBT extends Button
{
    public SettingsBT(Handler h, Rectangle space, boolean enabled)
    {
        super(h, space, enabled);

        //read in texture
        try
        {
            setImage(ImageIO.read(getClass().getResourceAsStream("/menus/mainmenu/settings.png")));
        }
        catch (IOException e)
        {
            h.println("vvv Settings Button failed to load image vvv");
            e.printStackTrace();
            h.println("^^^ Settings Button failed to load image ^^^");
        }

        //set the color
        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(8, 49, 174));
    }

    public void activate()
    {
        getHandler().println("settings :D");
    }
}
