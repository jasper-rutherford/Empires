import java.awt.*;

/**
 * The tile class is used to represent each hexagon of the map.
 */
public class Tile
{
    private Handler h;

    /**
     * Constructor for the tile class
     * @param handler the handler that is keeping track of everything. (The tile uses some variables from handler such
     *               as tileSideLength)
     */
    public Tile(Handler handler)
    {
        h = handler;
    }

    /**
     * renders the tile
     * @param g a graphics object to draw with
     */
    public void render(Graphics g)
    {

    }
}
