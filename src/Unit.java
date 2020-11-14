import java.awt.*;

public class Unit
{
    private Handler h;
    private Tile locTile;

    public Unit(Handler h, Tile locTile)
    {
        this.h = h;
        this.locTile = locTile;
    }

    public void render(Graphics g)
    {
        int radius = h.getBoard().getSideLength() / 2;

        g.setColor(new Color(184, 44, 44));
        g.fillOval(locTile.getXCoord() - radius, locTile.getYCoord() - radius, 2 * radius, 2 * radius);
    }
}
