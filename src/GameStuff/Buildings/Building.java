package GameStuff.Buildings;

import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Menus.Menu;
import GameStuff.Units.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Building
{
    private Handler h;

    private boolean hasSprite;
    private BufferedImage sprite;

    private Menu menu;

    //the tile where the building sits
    private Tile locTile;

    //the number of the player that controls the building
    private int playerNumber;

    //the units currently inside the building
    private ArrayList<Unit> units;

    public Building(Handler h, Tile locTile, int playerNumber)
    {
        this.h = h;
        hasSprite = false;

        this.locTile = locTile;
        this.playerNumber = playerNumber;

        units = new ArrayList<>();
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

        //render the number of units in the building
        g.setColor(Color.BLACK);
        g.drawString(units.size() + "", locTile.getXCoord() + 10, locTile.getYCoord() + 10);
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public void addUnit(Unit aUnit)
    {
        //add the unit to the list if it isn't already on it.
        if (!units.contains(aUnit))
        {
            units.add(aUnit);
        }
    }
}
