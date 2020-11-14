import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The tile class is used to represent each hexagon of the board.
 */
public class Tile
{
    private Handler h;
    private Board board;
    private Hexagon hex;
    private int xIndex;
    private int yIndex;
    private Color color;
    private Color borderColor;
    private boolean isValid;
    private ArrayList<Unit> units;

    /**
     * Constructor for the Tile class.
     *
     * @param h      the Game's Handler instance
     * @param xIndex the x index of the Tile in the Board
     * @param yIndex the y index of the Tile in the Board
     */
    public Tile(Handler h, Board board, int xIndex, int yIndex)
    {
        this.h = h;
        this.board = board;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        color = new Color(17, 82, 24);
        borderColor = new Color(1, 1, 1);
        isValid = true;

        hex = new Hexagon(h, board, xIndex, yIndex);

        units = new ArrayList<>();
    }

    /**
     * renders the tile
     *
     * @param g a graphics object to draw with
     */
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillPolygon(hex);

        g.setColor(borderColor);
        g.drawPolygon(hex);

        for (Unit unit : units)
        {
            unit.render(g);
        }
    }

    /**
     * used to update the hexagon that represents this tile.
     */
    public void updateHex()
    {
        hex.generateHexagon();
    }

    /**
     * gets this tile's x index in the board
     *
     * @return the x index in the board
     */
    public int getXIndex()
    {
        return xIndex;
    }

    public int getYIndex()
    {
        return yIndex;
    }

    public Hexagon getHex()
    {
        return hex;
    }

    public void setXCoord(int x)
    {
        hex.setXCoord(x);
    }

    public void setYCoord(int y)
    {
        hex.setYCoord(y);
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public boolean isValid()
    {
        return isValid;
    }

    public void setValidity(boolean val)
    {
        isValid = val;
    }

    public int getXCoord()
    {
        return hex.getXCoord();
    }

    public int getYCoord()
    {
        return hex.getYCoord();
    }

    /**
     * changes the tile's bordercolor to the given color
     *
     * @param color a Color to change borderColor to
     */
    public void setBorderColor(Color color)
    {
        borderColor = color;
    }

    /**
     * resets this tile's bordercolor to black.
     */
    public void resetBorderColor()
    {
        borderColor = Color.black;
    }

    public void setColorToDefault()
    {
        if (yIndex == 0 || yIndex == h.getBoard().getNumTilesHigh() - 1)
        {
            color = Color.red;
        }
        else
        {
            color = Color.blue;
        }
    }

    public void createUnit()
    {
        units.add(new Unit(h, this));
    }
}
