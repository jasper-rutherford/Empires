package GameStuff;

import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Unit
{
    private Handler h;
    private Tile locTile;
    private int id;

    private int playerNumber;

    private int maxMoveEnergy;
    private int moveEnergy;

    private BufferedImage icon;

    private Color teamColor;


    public Unit(Handler h, Tile locTile, int id, int playerNumber)
    {
        this.h = h;
        this.locTile = locTile;
        this.id = id;
        this.playerNumber = playerNumber;

        maxMoveEnergy = 2;
        moveEnergy = maxMoveEnergy;

        try
        {
            icon = ImageIO.read(getClass().getResourceAsStream("/unit.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        teamColor = h.getGame().getCurrentPlayer().getTeamColor();
    }

    public void render(Graphics g)
    {
        int width = h.getGame().getBoard().getSideLength() / 2;

        g.drawImage(icon, locTile.getXCoord() - width, locTile.getYCoord() - width, 2 * width, 2 * width, null);

        g.setColor(Color.BLACK);
        g.drawString("" + playerNumber, locTile.getXCoord(), locTile.getYCoord());
    }

    public void setLocTile(Tile locTile)
    {
        this.locTile = locTile;
    }

    public boolean canStep(Tile aTile)
    {
        return true;
    }

    public void refillMoves()
    {
        moveEnergy = maxMoveEnergy;
    }

    public boolean hasEnergy()
    {
        return moveEnergy > 0;
    }

    public void takeEnergy(int take)
    {
        moveEnergy -= take;
    }

    public int getID()
    {
        return id;
    }

    public int getMoveEnergy()
    {
        return moveEnergy;
    }

    public int getMaxMoveEnergy()
    {
        return maxMoveEnergy;
    }

    public Color getTeamColor()
    {
        return teamColor;
    }
}
