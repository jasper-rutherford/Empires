import java.awt.*;

/**
 * class used to represent a game board
 */
public class Board
{
    private final Handler h;

    /**
     * the width of the board (number of tiles)
     */
    private int width;

    /**
     * the height of the board (number of tiles)
     */
    private int height;

    /**
     * the 2d array representation of the hex grid
     */
    private Tile[][] tiles;

    public Board(Handler handler, int width, int height)
    {
        h = handler;

        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        generateTiles();
    }

    /**
     * Renders the board
     *
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

    /**
     * Used for initial generation of the tiles
     * (not for repeated use)
     */
    public void generateTiles()
    {
        //generate the center tile, to be used as the first anchor tile
        Tile center = new Tile(h, width / 2, height / 2);
        center.setXCoord(h.getScreenWidth() / 2);
        center.setYCoord(h.getScreenHeight() / 2);

        tiles[width / 2][height / 2] = center;

        h.setAnchorTile(center);

        //initialize all the tiles
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                tiles[x][y] = new Tile(h, x, y);
                tiles[x][y].updateHex();
            }
        }

        h.setAnchorTile(tiles[width / 2][height / 2]);
    }

    /**
     * Do not use for initial generation. Use generateTiles() for that.
     * used to generate new tile locations
     * (if the screen is zoomed or moved)
     */
    public void reload()
    {
        //update all the tiles
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                tiles[x][y].updateHex();
            }
        }
    }

    /**
     * gets the tile currently at the center of the screen.
     * Does so by checking what tile overlaps with the coordinates of the center of the screen
     *
     * @return the tile currently at the center of the screen.
     */
    public Tile getCenterTile()
    {
        return getTileAt(h.getScreenWidth() / 2, h.getScreenHeight() / 2);
    }

    /**
     * gets the tile at the given coordinates on screen.
     * <p>
     * if unable to find a tile with the given coordinates, returns an invalid tile
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return
     */
    public Tile getTileAt(int x, int y)
    {
        for (int xIndex = 0; xIndex < width; xIndex++)
        {
            for (int yIndex = 0; yIndex < height; yIndex++)
            {
                if (tiles[xIndex][yIndex].getHex().contains(x, y))
                {
                    return tiles[xIndex][yIndex];
                }
            }
        }

        Tile none = new Tile(h, -1, -1);
        none.setValidity(false);

        return none;
    }

    public Tile getTileAt(Point point)
    {
        return getTileAt(point.x, point.y);
    }

    public Tile getTileAtIndex(int xIndex, int yIndex)
    {
        return tiles[xIndex][yIndex];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
