package GameStuff.Menus.MainMenu;

import GameStuff.Buttons.Button;
import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SettingsBT extends Button
{
    public SettingsBT(Handler h, boolean enabled)
    {
        super(h, new Rectangle(-1, -1, -1, -1), enabled);

        int x = 100;
        int y = 300;
        int width = 208;
        int height = 28;
        setSpace(new Rectangle(x, y, width, height));

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
        setColor(Color.GREEN);
    }

    public void activate()
    {
        getHandler().println("settings :D");
    }
}
