package GameStuff;

import Framework.Handler;

import java.awt.*;

public class Unit
{
    private Handler h;
    private Tile locTile;
    private int id;

    private int playerNumber;

    private int maxMoveEnergy;
    private int moveEnergy;



    public Unit(Handler h, Tile locTile, int id, int playerNumber)
    {
        this.h = h;
        this.locTile = locTile;
        this.id = id;
        this.playerNumber = playerNumber;

        maxMoveEnergy = 2;
        moveEnergy = maxMoveEnergy;
    }

    public void render(Graphics g)
    {
        int radius = h.getGame().getBoard().getSideLength() / 2;

        g.setColor(new Color(184, 44, 44));
        g.fillOval(locTile.getXCoord() - radius, locTile.getYCoord() - radius, 2 * radius, 2 * radius);

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
}
