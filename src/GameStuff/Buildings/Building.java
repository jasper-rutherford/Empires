package GameStuff.Buildings;

import Framework.Handler;
import GameStuff.Menus.Menu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Building
{
    private Handler h;

    private boolean hasSprite;
    private BufferedImage sprite;

    private Menu menu;

    public Building(Handler h)
    {
        this.h = h;
        hasSprite = false;
    }

    public void readSprite(String path)
    {
        //read in sprite
        try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream(path));
            hasSprite = true;
        }
        catch (IOException e)
        {
            h.println("vvv Building failed to load sprite <" + path + "> vvv");
            e.printStackTrace();
            h.println("^^^ Building failed to load sprite <" + path + "> ^^^");
        }
    }
}
