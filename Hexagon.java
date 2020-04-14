import java.awt.*;

/**
 * for use with generating/manipulating hexagons for the tile class
 */
public class Hexagon extends Polygon
{
    private Handler h;
    private int npoints;
    private int[] xpoints;
    private int[] ypoints;

    public Hexagon(Handler h)
    {
        this.h = h;
    }
}
