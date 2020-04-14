import java.awt.*;

/**
 * class used to represent a game board
 */
public class Map
{
    private Handler h;
    private int width;
    private int height;
    private Tile[][] tiles;

    public Map(Handler handler, int width, int height)
    {
        h = handler;

        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        reload();
    }

    /**
     * Renders the map
     * @param g a graphics object to draw with
     */
    public void render(Graphics g)
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                tiles[x][y].render(g);
            }
        }
    }

    public void reload()
    {
        //initialize all the tiles
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                tiles[x][y] = new Tile(h);
            }
        }
    }
}
