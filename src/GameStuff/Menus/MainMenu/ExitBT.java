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

        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(8, 49, 174));
        setImage("/menus/mainmenu/exit.png");
    }

    public void activate()
    {
        System.exit(0);
    }
}
