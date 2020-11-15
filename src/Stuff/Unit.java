package Stuff;

import Framework.Handler;

import java.awt.*;

public class Unit
{
    private Handler h;
    private Tile locTile;
    private int num;

    public Unit(Handler h, Tile locTile, int num)
    {
        this.h = h;
        this.locTile = locTile;
        this.num = num;
    }

    public void render(Graphics g)
    {
        int radius = h.getBoard().getSideLength() / 2;

        g.setColor(new Color(184, 44, 44));
        g.fillOval(locTile.getXCoord() - radius, locTile.getYCoord() - radius, 2 * radius, 2 * radius);

        g.setColor(new Color(1, 1, 1));
        g.drawString("" + num, locTile.getXCoord(), locTile.getYCoord());
    }

    public void setLocTile(Tile locTile)
    {
        this.locTile = locTile;
    }
}
