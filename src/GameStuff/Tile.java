package GameStuff;

import Framework.Handler;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

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
    private Color defaultColor;
    private Color defaultBorderColor;
    private Color color;
    private Color borderColor;
    private ArrayList<Unit> units;
    private int selectedUnitIndex;
    private Unit selectedUnit;
    private int moveCost;
    private Color teamColor;


    /**
     * Constructor for the Stuff.Tile class.
     *
     * @param h      the Framework.Game's Framework.Handler instance
     * @param xIndex the x index of the Stuff.Tile in the Stuff.Board
     * @param yIndex the y index of the Stuff.Tile in the Stuff.Board
     */
    public Tile(Handler h, Board board, int xIndex, int yIndex)
    {
        this.h = h;
        this.board = board;
        this.xIndex = xIndex;
        this.yIndex = yIndex;

        defaultColor = new Color(17, 82, 24);
        defaultBorderColor = new Color(1, 1, 1);
        color = defaultColor;
        borderColor = defaultBorderColor;

        hex = new Hexagon(h, board, xIndex, yIndex);

        units = new ArrayList<>();

        selectedUnitIndex = 0;
        selectedUnit = null;

        moveCost = 1;

        teamColor = null;
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

        if (teamColor != null)
        {
            Area teamIndicator = new Area(hex);

        }

        g.setColor(borderColor);
        g.drawPolygon(hex);

        for (int lcv = units.size() - 1; lcv >= 0; lcv--)
        {
            units.get(lcv).render(g);
        }

        if (selectedUnit != null)
        {
            selectedUnit.render(g);
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
        borderColor = defaultBorderColor;
    }

    public void setColorToDefault()
    {
        color = defaultColor;
    }

    public void createUnit(int val, int playerNumber)
    {
        addUnit(new Unit(h, this, val, playerNumber));
    }

    /**
     * selects the tile, selects the next unit in line
     */
    public void select()
    {
        borderColor = new Color(255, 255, 255);

        if (units.size() > 0)
        {
            selectedUnitIndex++;

            while (selectedUnitIndex >= units.size())
            {
                selectedUnitIndex -= units.size();
            }

            selectedUnit = units.get(selectedUnitIndex);
        }
    }

    /**
     * selects the tile, selects the specific unit (if possible)
     */
    public void select(Unit aUnit)
    {
        if (units.contains(aUnit))
        {
            borderColor = new Color(255, 255, 255);

            selectedUnit = aUnit;
            selectedUnitIndex = units.indexOf(aUnit);
        }
    }

    public void deselect()
    {
        resetBorderColor();

        selectedUnitIndex = -1;
        selectedUnit = null;
    }

    public Unit getSelectedUnit()
    {
        return selectedUnit;
    }

    public void addUnit(Unit aUnit)
    {
        if (units.size() == 0)
        {
            teamColor = aUnit.getTeamColor();
        }

        units.add(aUnit);
        aUnit.setLocTile(this);

        selectedUnitIndex = units.size() - 1;
        selectedUnit = aUnit;
    }

    public void removeUnit(Unit aUnit)
    {
        units.remove(aUnit);

        if (units.size() == 0)
        {
            teamColor = null;
        }
        else
        {
            teamColor = units.get(0).getTeamColor();
        }
    }

    /**
     * gets the tile adjacent to this one, in the direction of dir
     *
     * @param dir the direction to grab an adjacent tile. starts with zero bottom left and goes clockwise to 5.
     * @return
     */
    public Tile adjTile(int dir)
    {
        dir %= 6;

        //half of the sidelength, used for calculating the y points
        double l = board.getSideLength() / 2.0;

        //distance from the center of a tile to the side, used for calculating x points
        int d = (int) (Math.sqrt(3) * l);

        int xCoord = getXCoord();
        int yCoord = getYCoord();

        xCoord -= (int) Math.pow(-1, dir / 3) * (((dir + 1) % 3) / 2 + 1) * d;
        yCoord += ((int) Math.pow(-1, (((dir + 1) % 6) / 3))) * (((((dir + 1) % 3) / 2) - 1) * -1) * 3 * l;


        //loop the screen
        int boardWidth = board.getNumTilesWide() * d * 2;

        if (xCoord < -(2 * d))
        {
            xCoord += boardWidth;
        }
        else if (xCoord > h.getScreenWidth() + (2 * d))
        {
            xCoord -= boardWidth;
        }

        return board.getTileAt(xCoord, yCoord);
    }

    public int getMoveCost()
    {
        return moveCost;
    }

    public boolean hasUnits()
    {
        return  units.size() > 0;
    }

    public Unit firstUnit()
    {
        return units.get(0);
    }
}
