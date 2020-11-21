package GameStuff;

import Framework.Handler;

import java.awt.*;
import java.util.ArrayList;

/**
 * class used to represent a game board
 */
public class Board
{
    private final Handler h;

    /**
     * the width of the board (number of tiles)
     */
    private int numTilesWide;

    /**
     * the height of the board (number of tiles)
     */
    private int numTilesHigh;

    /**
     * the 2d array representation of the hex grid
     */
    private Tile[][] tiles;

    /**
     * when zooming or moving the screen around this tile is the tile everything is generated around.
     * This means when zooming this tile stays in place.
     */
    private Tile anchorTile;

    private int sideLength;

    private Tile selectedTile;

    private ArrayList<Unit> tiredUnits;

    private int turnCount;

    public Board(Handler handler, int width, int height)
    {
        h = handler;

        this.numTilesWide = width;
        this.numTilesHigh = height;
        this.sideLength = 50;

        tiles = new Tile[width][height];

        generateTiles();

        tiredUnits = new ArrayList<>();

        turnCount = 0;
    }

    /**
     * Renders the board
     *
     * @param g a graphics object to draw with
     */
    public void render(Graphics g)
    {
        for (int x = 0; x < numTilesWide; x++)
        {
            for (int y = 0; y < numTilesHigh; y++)
            {
                tiles[x][y].render(g);
            }
        }

        if (selectedTile != null)
        {
            selectedTile.render(g);
        }

        g.setColor(Color.gray);
        g.drawString(turnCount + "", 50, 50);
    }

    /**
     * Used for initial generation of the tiles
     * (not for repeated use)
     */
    public void generateTiles()
    {
        //generate the center tile, to be used as the first anchor tile
        Tile center = new Tile(h, this, numTilesWide / 2, numTilesHigh / 2);
        center.setXCoord(h.getScreenWidth() / 2);
        center.setYCoord(h.getScreenHeight() / 2);

        tiles[numTilesWide / 2][numTilesHigh / 2] = center;

        setAnchorTile(center);

        //initialize all the tiles
        for (int x = 0; x < numTilesWide; x++)
        {
            for (int y = 0; y < numTilesHigh; y++)
            {
                tiles[x][y] = new Tile(h, this, x, y);
                tiles[x][y].updateHex();
            }
        }

        this.setAnchorTile(tiles[numTilesWide / 2][numTilesHigh / 2]);
    }

    /**
     * Do not use for initial generation. Use generateTiles() for that.
     * used to generate new tile locations
     * (if the screen is zoomed or moved)
     */
    public void reload()
    {
        //update all the tiles
        for (int x = 0; x < numTilesWide; x++)
        {
            for (int y = 0; y < numTilesHigh; y++)
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
     * if unable to find a tile with the given coordinates, returns null
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return
     */
    public Tile getTileAt(int x, int y)
    {
        for (int xIndex = 0; xIndex < numTilesWide; xIndex++)
        {
            for (int yIndex = 0; yIndex < numTilesHigh; yIndex++)
            {
                if (tiles[xIndex][yIndex].getHex().contains(x, y))
                {
                    return tiles[xIndex][yIndex];
                }
            }
        }

        return null;
    }

    public Tile getTileAt(Point point)
    {
        return getTileAt(point.x, point.y);
    }

    public Tile getTileAtIndex(int xIndex, int yIndex)
    {
        return tiles[xIndex][yIndex];
    }

    public int getNumTilesWide()
    {
        return numTilesWide;
    }

    public int getNumTilesHigh()
    {
        return numTilesHigh;
    }

    public void setAnchorTile(Tile tile)
    {
        if (tile != null)
        {
            anchorTile = tile;
        }
    }

    public Tile getAnchorTile()
    {
        return anchorTile;
    }

    public void setSideLength(int sideLength)
    {
        this.sideLength = sideLength;
    }

    public int getSideLength()
    {
        return sideLength;
    }

    public void select(Tile aTile)
    {
        if (aTile != null)
        {
            if (selectedTile != null && !selectedTile.equals(aTile))
            {
                selectedTile.deselect();
            }

            selectedTile = aTile;
            selectedTile.select();
        }
    }

    /**
     * moves the selected unit to the given tile
     *
     * @param aTile
     */
    public void moveSelected(Tile aTile)
    {
        if (selectedTile != null && selectedTile.getSelectedUnit() != null && aTile != null)
        {
            Unit selectedUnit = selectedTile.getSelectedUnit();

            if (selectedUnit.hasEnergy())
            {
                selectedTile.removeUnit(selectedTile.getSelectedUnit());

                aTile.addUnit(selectedUnit);

                selectedUnit.takeEnergy(aTile.getMoveCost());

                if (!tiredUnits.contains(selectedUnit))
                {
                    tiredUnits.add(selectedUnit);
                }
            }
        }
    }

    /**
     * attempts to find a path to the tile the mouse is above
     * <p>
     * im not explaining this
     * https://en.wikipedia.org/wiki/Pathfinding
     * scroll down to 'sample algorithm'
     */
    public void path()
    {
        boolean foundPath = false;
        boolean donePathing = selectedTile.getSelectedUnit() == null;

        //the tile to path to
        Tile goal = getTileAt(h.getMouse().getCoords());

        ArrayList<Tile> bank = new ArrayList<>();
        ArrayList<Tile> now = new ArrayList<>();
        ArrayList<Tile> next = new ArrayList<>();
        ArrayList<Integer> dists = new ArrayList<>();

        now.add(goal);
        dists.add(0);


        while (!donePathing)
        {
            //gives each tile a value that is essentially the distance to the goal
            for (Tile tile : now)
            {
                for (int lcv = 0; lcv < 6; lcv++)
                {
                    Tile adjTile = tile.adjTile(lcv);

                    if (adjTile != null && !bank.contains(adjTile) && !now.contains(adjTile) && !next.contains(adjTile) && selectedTile.getSelectedUnit().canStep(adjTile))
                    {
                        next.add(adjTile);

                        if (adjTile.equals(selectedTile))
                        {
                            foundPath = true;
                            donePathing = true;
                        }
                    }
                }
            }

            if (next.size() == 0)
            {
                bank.addAll(now);
                now.clear();

                donePathing = true;
            }
            else
            {
                int count = dists.get(dists.size() - 1) + 1;
                for (Tile tile : next)
                {
                    dists.add(count);
                }

                bank.addAll(now);
                now.clear();
                now.addAll(next);
                next.clear();
            }
        }

        if (foundPath)
        {
            //choose a tile adjacent to the selected tile with the lowest distance (choose randomly between ties)

            //find the lowest distance
            int lowDist = -1;
            for (int lcv = 0; lcv < 6; lcv++)
            {
                Tile adjTile = selectedTile.adjTile(lcv);

                if (bank.contains(adjTile))
                {
                    int adjDist = dists.get(bank.indexOf(adjTile));

                    if (lowDist == -1)
                    {
                        lowDist = adjDist;
                    }
                    else if (adjDist < lowDist)
                    {
                        lowDist = adjDist;
                    }
                }
            }

            //find all the tiles with that distance
            ArrayList<Tile> ties = new ArrayList<>();

            for (int lcv = 0; lcv < 6; lcv++)
            {
                Tile adjTile = selectedTile.adjTile(lcv);

                if (bank.contains(adjTile) && dists.get(bank.indexOf(adjTile)) == lowDist)
                {
                    ties.add(adjTile);
                }
            }

            //randomly select one of those tiles
            int randomIndex = (int) (Math.random() * ties.size());

            //try to move the unit there
            moveSelected(ties.get(randomIndex));
        }
    }

    public ArrayList<Unit> getTiredUnits()
    {
        return tiredUnits;
    }

    public void increaseTurnCount()
    {
        turnCount++;
    }

    public Tile getSelectedTile()
    {
        return selectedTile;
    }
}
