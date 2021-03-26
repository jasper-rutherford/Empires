package GameStuff.Buildings;

import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Menus.Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Building
{
    private Handler h;

    private boolean hasSprite;
    private BufferedImage sprite;

    private Menu menu;

    //the tile where the building sits
    private Tile locTile;

    public Building(Handler h, Tile locTile)
    {
        this.h = h;
        hasSprite = false;

        this.locTile = locTile;
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

    public void render(Graphics g)
    {
        //draw the sprite if there is one
        if (hasSprite)
        {
            int width = h.getGame().getBoard().getSideLength() / 2;

            g.drawImage(sprite, locTile.getXCoord() - width, locTile.getYCoord() - width, 2 * width, 2 * width, null);
        }
    }
}
