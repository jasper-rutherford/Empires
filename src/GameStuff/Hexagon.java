package GameStuff;

import Framework.Handler;

import java.awt.*;

/**
 * for use with generating/manipulating hexagons for the tile class
 */
public class Hexagon extends Polygon
{
    private Handler h;

    private Board board;

    /**
     * the tile's x index in the map array
     */
    private int xIndex;

    /**
     * the tile's y index in the map array
     */
    private int yIndex;

    /**
     * the x coordinate of the center of the hexagon
     */
    private int xCoord;

    /**
     * the y coordinate of the center of the hexagon
     */
    private int yCoord;

    /**
     * the sidelength of the hexagon
     * <p>
     * this is only to be used with independent, random hexagons
     * </p>
     */
    private int sidelength;

    /**
     * an int array that holds the pattern of addition needed to generate a hexagon's x points
     */
    private final int[] xPattern = new int[]{0, 1, 1, 0, -1, -1};


    /**
     * an int array that holds the pattern of addition needed to generate a hexagon's y points
     */
    private final int[] yPattern = new int[]{-2, -1, 1, 2, 1, -1};

    private boolean independent;


    /**
     * Constructor for the Stuff.Hexagon class
     *
     * @param h      the game's Framework.Handler instance
     * @param xIndex the x index of the Stuff.Hexagon's Stuff.Tile in the Map
     * @param yIndex the y index of the Stuff.Hexagon's Stuff.Tile in the Map
     */
    public Hexagon(Handler h, Board board, int xIndex, int yIndex)
    {
        this.h = h;
        this.board = board;
        this.xIndex = xIndex;
        this.yIndex = yIndex;

        //this stuff exists from extending polygon
        npoints = 6;
        xpoints = new int[npoints];
        ypoints = new int[npoints];

        independent = false;
    }

    public Hexagon(int xCoord, int yCoord, int sidelength)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.sidelength = sidelength;

        //this stuff exists from extending polygon
        npoints = 6;
        xpoints = new int[npoints];
        ypoints = new int[npoints];

        independent = true;

        updatePoints();
    }

    /**
     * generates the hexagon's center coordinates and creates a hexagon around them
     * sets bounds to null so that Polygon will recalculate them
     */
    public void generateHexagon()
    {
        bounds = null;
        updateCenterCoords();
        updatePoints();
    }

    /**
     * updates the coordinates of the center of the hexagon.
     * if a hexagon from the top/bottom row's center goes on screen it is made the new anchortile,
     * its y coord is set to the border of the screen, and everything is reloaded
     */
    public void updateCenterCoords()
    {
        //the tile to generate around
        Tile anchorTile = board.getAnchorTile();

        //half of the board's sidelength, used for calculating the y points
        double l = board.getSideLength() / 2.0;

        //distance from the center of a tile to the side, used for calculating x points
        int d = (int) (Math.sqrt(3) * l);

        //the distances (index wise) from this hexagon to the anchortile
        int dxIndex = xIndex - anchorTile.getXIndex();
        int dyIndex = yIndex - anchorTile.getYIndex();

        //the distance (coordinate wise) to offset this hexagon from the anchortile
        int xOffset = (int) (dxIndex * 2 * d);
        int yOffset = (int) (dyIndex * 3 * l);


        //keeps every other row of hexagons offset to line up the edges
        if (anchorTile.getYIndex() % 2 == 0 && yIndex % 2 == 1) //if anchortile's y index is even, shift odd tiles left
        {
            xOffset -= d;
        }
        else if (anchorTile.getYIndex() % 2 == 1 && yIndex % 2 == 0) //if anchortile's y index is odd, shift even tiles right
        {
            xOffset += d;
        }

        //calculate the center coords
        xCoord = anchorTile.getHex().xCoord + xOffset;
        yCoord = anchorTile.getHex().yCoord + yOffset;

        //ensure the top and bottom row's centers remain off screen
        if (yCoord > 0 && yIndex == 0)
        {
            yCoord = 0;
            board.setAnchorTile(board.getTileAtIndex(xIndex, yIndex));
            board.reload();
        }
        else if (yCoord < h.getScreenHeight() && yIndex == board.getNumTilesHigh() - 1)
        {
            yCoord = h.getScreenHeight();
            board.setAnchorTile(board.getTileAtIndex(xIndex, yIndex));
            board.reload();
        }

        //loop the screen
        int boardWidth = (int) (board.getNumTilesWide() * d * 2);
        if (xCoord < -(int) (2 * d))
        {
            xCoord += boardWidth;
        }
        else if (xCoord > h.getScreenWidth() + (int) (2 * d))
        {
            xCoord -= boardWidth;
        }
    }

    /**
     * generates a new hexagon around the center coordinates
     */
    public void updatePoints()
    {
        //half of the sidelength, used for calculating the y points
        double l;

        if (!independent)
        {
            l = board.getSideLength() / 2.0;
        }
        else
        {
            l = sidelength / 2.0;
        }

        //distance from the center of a tile to the side, used for calculating x points
        double d = Math.sqrt(3) * l;


        //calculate x points
        for (int x = 0; x < 6; x++)
        {
            xpoints[x] = (int) (xCoord + xPattern[x] * d);
        }

        //calculate y points
        for (int y = 0; y < 6; y++)
        {
            ypoints[y] = (int) (yCoord + yPattern[y] * l);
        }
    }

    public void setXCoord(int x)
    {
        xCoord = x;
    }

    public void setYCoord(int y)
    {
        yCoord = y;
    }

    public void setCenter(Point center)
    {
        xCoord = center.x;
        yCoord = center.y;

        updatePoints();
    }

    public void setSidelength(int sidelength)
    {
        this.sidelength = sidelength;

        updatePoints();
    }

    public void setInfo(Point center, int sidelength)
    {
        xCoord = center.x;
        yCoord = center.y;
        this.sidelength = sidelength;

        updatePoints();
    }

    public String toString()
    {
        String out = xIndex + ", " + yIndex + " [" + xCoord + ", " + yCoord + "]: ";

        for (int lcv = 0; lcv < 6; lcv++)
        {
            out += "(" + xpoints[lcv] + ", " + ypoints[lcv] + "), ";
        }

        return out;
    }

    public int getXCoord()
    {
        return xCoord;
    }

    public int getYCoord()
    {
        return yCoord;
    }

    public String spaceString()
    {
        String out = "";

        for (int lcv = 0; lcv < 6; lcv++)
        {
            out += "(" + xpoints[lcv] + ", " + ypoints[lcv] + "), ";
        }

        return out;
    }

    public void moveCenter(int deltaX, int deltaY)
    {
        xCoord += deltaX;
        yCoord += deltaY;

        if (independent)
        {
            updatePoints();
        }
    }
}
