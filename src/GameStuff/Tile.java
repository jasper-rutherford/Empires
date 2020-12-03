package GameStuff;

import Framework.Handler;
import GameStuff.Units.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    private Polygon teamIndicator;


    private BufferedImage texture;

    private String resourceType;
    private BufferedImage resourceIcon;

    private int maxResourceCount;
    private int resourceCount;


    /**
     * Constructor for the Stuff.Tile class.
     *
     * @param h      the GameStuff.Game's Framework.Handler instance
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
        teamIndicator = new Polygon();
        teamIndicator.npoints = 14;
        teamIndicator.xpoints = new int[14];
        teamIndicator.ypoints = new int[14];

        texture = board.getTexture("grass");

        //be a random resource
        int num = h.getRandom().nextInt(3);
        if (num == 0)
        {
            resourceType = "wood";
            moveCost++;

            maxResourceCount = 50;
        }
        else if (num == 1)
        {
            resourceType = "stone";
            moveCost++;

            maxResourceCount = 50;
        }
        else
        {
            resourceType = null;
            maxResourceCount = 1;
        }

        resourceCount = maxResourceCount;

        if (resourceType == null)
        {
            resourceCount = 0;
        }

        resourceIcon = board.getTexture(resourceType);
    }

    /**
     * renders the tile
     *
     * @param g a graphics object to draw with
     */
    public void render(Graphics g)
    {
        //render solid color for the hex
        g.setColor(color);
        g.fillPolygon(hex);

        //render an image for the hex
//        drawImageInPolygon((Graphics2D) g, texture, hex, 1, 1);

        //render team indicator
        if (teamColor != null)
        {
            g.setColor(teamColor);
            g.fillPolygon(teamIndicator);
        }

        //draw hex grid
        g.setColor(borderColor);
        g.drawPolygon(hex);

        //render resource icon
        int width = h.getGame().getBoard().getSideLength() / 2 * resourceCount / maxResourceCount;
        g.drawImage(resourceIcon, getXCoord() - width, getYCoord() - width, 2 * width, 2 * width, null);

        //render units
        for (int lcv = units.size() - 1; lcv >= 0; lcv--)
        {
            units.get(lcv).render(g);
        }

        //render selected unit last
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

        if (teamColor != null)
        {
            updateTeamIndicator();
        }
    }

    public void updateTeamIndicator()
    {
        Hexagon inner = new Hexagon(hex.getXCoord(), hex.getYCoord(), (int) (board.getSideLength() * .9));

        int[] xPoints = new int[14];
        int[] yPoints = new int[14];

        for (int lcv = 0; lcv < 7; lcv++)
        {
            int index = lcv % 6;

            xPoints[lcv] = hex.xpoints[index];
            yPoints[lcv] = hex.ypoints[index];
        }
        for (int lcv = 6; lcv >= 0; lcv--)
        {
            int index = lcv % 6;

            xPoints[13 - lcv] = inner.xpoints[index];
            yPoints[13 - lcv] = inner.ypoints[index];
        }

        teamIndicator.xpoints = xPoints;
        teamIndicator.ypoints = yPoints;
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
        addUnit(new Unit(h, this, val, playerNumber, null));
    }

    /**
     * selects the tile, selects the next unit in line
     */
    public void select()
    {
        //set border to white
        borderColor = new Color(255, 255, 255);

        if (units.size() > 0)
        {
            selectedUnitIndex++;

            while (selectedUnitIndex >= units.size())
            {
                selectedUnitIndex -= units.size();
            }

            if (selectedUnit != null)
            {
                selectedUnit.deselect();
            }

            selectedUnit = units.get(selectedUnitIndex);

            selectedUnit.select();
        }
    }

    /**
     * selects the tile, selects the specific unit (if possible)
     */
    public void select(Unit aUnit)
    {
//        if (units.contains(aUnit))
//        {
            borderColor = new Color(255, 255, 255);

//            if (selectedUnit != null)
//            {
//                selectedUnit.deselect();
//            }
//
//            selectedUnit = aUnit;
//            selectedUnitIndex = units.indexOf(aUnit);
//
//            selectedUnit.select();
//        }
    }

    public boolean selectUnit()
    {
        //if there was already a unit selected on this tile, select the next one
        if (selectedUnit != null)
        {
            selectedUnit.deselect();
            selectedUnitIndex++;

            if (selectedUnitIndex >= units.size())
            {
                selectedUnitIndex = 0;
            }

            selectedUnit.select();
            teamColor = selectedUnit.getTeamColor();
        }
        //otherwise if there are any units on the tile just select the first
        else if (units.size() > 0)
        {
            selectedUnitIndex = 0;
            selectedUnit = units.get(selectedUnitIndex);
            selectedUnit.select();
            teamColor = selectedUnit.getTeamColor();
        }

        //return whether or not a unit was selected
        return selectedUnitIndex != -1;
    }

    /**
     * tries to deselect the given unit
     *
     * @param aUnit the unit to try to deselect
     */
    public void deselectUnit(Unit aUnit)
    {
        if (selectedUnit != null && selectedUnit.equals(aUnit))
        {
            selectedUnit.deselect();
            selectedUnit = null;
            selectedUnitIndex = -1;

            teamColor = null;
        }
    }

    /**
     * deselects the selected unit on this tile (if there is one)
     */
    public void deselectUnit()
    {
        deselectUnit(selectedUnit);
    }

    public void deselect()
    {
        resetBorderColor();

//        selectedUnitIndex = -1;
//
//        if (selectedUnit != null)
//        {
//            selectedUnit.deselect();
//        }
//
//        selectedUnit = null;
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
            updateTeamIndicator();
        }

        units.add(aUnit);
        aUnit.setLocTile(this);

        selectedUnitIndex = units.size() - 1;
        selectedUnit = aUnit;
    }

    public void removeUnit(Unit aUnit)
    {
        if (units.contains(aUnit))
        {
            units.remove(aUnit);
        }

//        if (aUnit.equals(selectedUnit))
//        {
//            selectedUnit = null;
//            selectedUnitIndex = -1;
//        }

//        if (h.getGame().getPlayer(aUnit.getPlayerNumber()).getSelectedUnit().equals(aUnit))
//        {
//            h.getGame().getPlayer(aUnit.getPlayerNumber()).clearSelectedUnit();
//        }
//
//        if (units.size() == 0)
//        {
//            teamColor = null;
//        }
//        else
//        {
//            teamColor = units.get(0).getTeamColor();
//        }
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

    public boolean isAdjacent(Tile aTile)
    {
        for (int lcv = 0; lcv < 6; lcv++)
        {
            if (adjTile(lcv).equals(aTile))
            {
                return true;
            }
        }

        return false;
    }

    public int getMoveCost()
    {
        return moveCost;
    }

    public boolean hasUnits()
    {
        return units.size() > 0;
    }

    public Unit firstUnit()
    {
        if (units.size() > 0)
        {
            return units.get(0);
        }
        return null;
    }

    public boolean hasUnit(Unit aUnit)
    {
        return units.contains(aUnit);
    }

    public Rectangle drawImageInPolygon(Graphics g2d, BufferedImage img, Polygon poly, double xfactor,
                                        double yfactor)
    {
        int[] xPts = poly.xpoints, yPts = poly.ypoints;
        int minXPt = xPts[0], minYPt = yPts[0], maxXPt = xPts[0], maxYPt = yPts[0];
        // System.out.println("Pt: 0 x: " + xPts[0] + " y: " + yPts[0]);
        for (int i = 1; i < poly.npoints; i++)
        {
            // System.out.println("Pt: " + i + " x: " + xPts[i] + " y: " +
            // yPts[i]);
            if (xPts[i] < minXPt)
                minXPt = xPts[i];/* w w  w. j  a va 2 s.  c o m*/
            if (yPts[i] < minYPt)
                minYPt = yPts[i];
            if (xPts[i] > maxXPt)
                maxXPt = xPts[i];
            if (yPts[i] > maxYPt)
                maxYPt = yPts[i];
        }
        int panelWt = maxXPt - minXPt, panelHt = maxYPt - minYPt;
        // System.out.println("MinXPt: " + minXPt + " MinYPt: " + minYPt);
        // System.out.println("MaxXPt: " + maxXPt + " MaxYPt: " + maxYPt);
        // System.out.println("Width: " + panelWt + " Height: " + panelHt);

        Rectangle bounds = poly.getBounds();
        panelWt = bounds.width;
        panelHt = bounds.height;
        // System.out.println("Poly Bounds: " + bounds.toString());

        panelWt = xPts[1] - xPts[0];
        panelHt = yPts[2] - yPts[1];
        // System.out.println("New Width: " + panelWt + " Height: " + panelHt);

        int x = xPts[0] + (int) (panelWt * xfactor);
        int y = yPts[0] + (int) (panelHt * yfactor);
        // System.out.println("xFact: " + xfactor + " yFact: " + yfactor);
        // System.out.println("Poly Wt: " + panelWt + " Ht: " + panelHt + " x: "
        // + x + " y: " + y);
        g2d.drawImage(img, x, y, null);
        Rectangle imgRect = new Rectangle(x, y, img.getWidth(), img.getHeight());
        // System.out.println("Image: " + img.toString());
        // System.out.println("Image Rect: " + imgRect.toString());
        return imgRect;
    }

    public void drawImage(BufferedImage srcImg, BufferedImage img2Draw, int w, int h)
    {
        if (w == -1)
            w = (int) (srcImg.getWidth() / 2);
        if (h == -1)
            h = (int) (srcImg.getHeight() / 2);
        System.out.println("AWT Image Wt: " + w + " And Ht: " + h);
        Graphics2D g2 = srcImg.createGraphics();
        g2.drawImage(img2Draw, w, h, null);
        g2.dispose();
    }

    public boolean hasResources()
    {
        return resourceCount != 0;
    }

    /**
     * try to harvest <strength> amount of resources from the tile, if there are less than strength resources on the tile then it returns all of them.
     *
     * @param strength the amount of resources to try to take from the tile
     * @return the amount of resources harvested from the tile
     */
    public int harvestResources(int strength)
    {
        if (resourceCount >= strength)
        {
            resourceCount -= strength;
            return strength;
        }
        else
        {
            int out = resourceCount;
            resourceCount = 0;
            return out;
        }
    }

    public String getResourceType()
    {
        return resourceType;
    }
}
