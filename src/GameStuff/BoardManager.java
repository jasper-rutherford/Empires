package GameStuff;

import Framework.Handler;
import Framework.MouseStuff.Mouse;
import GameStuff.Units.Unit;

import java.util.ArrayList;

/**
 * interacts with the board
 */
public class BoardManager
{
    private Handler h;

    private Board board;

    public BoardManager(Handler h, Board board)
    {
        this.h = h;

        this.board = board;
    }

    /**
     * tries to select the given tile, selects the next unit in line on the tile
     *
     * @param aTile the tile to try to select
     */
    public void selectTile(Tile aTile)
    {
        Player currentPlayer = h.getGame().getCurrentPlayer();
        Tile selectedTile = currentPlayer.getSelectedTile();

        //if the tile in question isn't null and has a unit controlled by the current player
        if (aTile != null && aTile.hasUnits() && currentPlayer.hasUnit(aTile.firstUnit()))
        {
            //if the previous selected tile wasn't null and is not the tile being clicked
            if (selectedTile != null && !selectedTile.equals(aTile))
            {
                //deselect it
                selectedTile.deselect();
            }

            //set the new selectedtile
            currentPlayer.setSelectedTile(aTile);

            //select the new selected tile
            aTile.select();

            //set the current player's selected unit
            currentPlayer.setSelectedUnit(aTile.getSelectedUnit());
        }
        //if the tile in question is not null but has no valid units
        else if (aTile != null)
        {
            //if the old selectedtile is not null
            if (selectedTile != null)
            {
                //deselect it
                selectedTile.deselect();

                //set the new selected tile to null
                currentPlayer.setSelectedTile(null);
            }
        }
    }

    /**
     * tries to select the given tile, and tries to select the specified unit
     *
     * @param aTile the tile to try to select
     * @param aUnit the unit to select
     */
    public void selectTile(Tile aTile, Unit aUnit)
    {
        Player currentPlayer = h.getGame().getCurrentPlayer();
        Tile selectedTile = currentPlayer.getSelectedTile();

        //if the tile in question isn't null and has the specified unit
        if (aTile != null && aTile.hasUnits() && currentPlayer.hasUnit(aUnit))
        {
            //if the previous selected tile wasn't null
            if (selectedTile != null)
            {
                //deselect it
                selectedTile.deselect();
            }

            //set the new selectedtile
            currentPlayer.setSelectedTile(aTile);

            //select the new selected tile
            aTile.select(aUnit);

            //set the current player's selected unit
            currentPlayer.setSelectedUnit(aTile.getSelectedUnit());
        }
    }

    /**
     * tries to select the tile the mouse is above
     */
    public void selectMouseTile()
    {
        //the mouse
        Mouse mouse = h.getMouse();

        //get the tile the mouse is above
        Tile mouseTile = board.getTileAt(mouse.getCoords());

        //try to select it
        selectTile(mouseTile);
    }

    /**
     * attempts to find a path to the tile the mouse is above
     * <p>
     * im not explaining this
     * https://en.wikipedia.org/wiki/Pathfinding
     * scroll down to 'sample algorithm'
     */
    public void pathTo(Tile goal)
    {
        Tile selectedTile = h.getGame().getCurrentPlayer().getSelectedTile();

        boolean foundPath = false;
        boolean donePathing = selectedTile.getSelectedUnit() == null;


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

    /**
     * moves the selected unit to the given tile
     *
     * @param aTile
     */
    public void moveSelected(Tile aTile)
    {
        Player currentPlayer = h.getGame().getCurrentPlayer();
        Tile selectedTile = currentPlayer.getSelectedTile();
        Unit selectedUnit = currentPlayer.getSelectedUnit();
        ArrayList<Unit> tiredUnits = currentPlayer.getTiredUnits();

        //if everything is valid and the selected unit has energy
        if (selectedTile != null && selectedUnit != null && aTile != null && selectedUnit.hasEnergy())
        {
            //remove the selected unit from its current tile
            selectedTile.removeUnit(selectedUnit);

            //add the unit to its new tile
            aTile.addUnit(selectedUnit);

            //take energy from the unit equivalent to the tile's move cost
            selectedUnit.takeEnergy(aTile.getMoveCost());

            //add the unit to the list of tired units if it wasn't already on there
            if (!tiredUnits.contains(selectedUnit))
            {
                tiredUnits.add(selectedUnit);
            }


            //update current player's selected tile
            h.getGame().getCurrentPlayer().setSelectedTile(aTile);

            selectedTile.deselect();

            aTile.select(selectedUnit);
        }
    }

    /**
     * attempt to find a path from the selectedTile to the tile the mouse is above, then move the selected unit closer to that tile
     */
    public void pathToMouse()
    {
        Mouse mouse = h.getMouse();

        Tile mouseTile = board.getTileAt(mouse.getCoords());

        pathTo(mouseTile);
    }

    public void considerMoveToMouse()
    {
        Mouse mouse = h.getMouse();

        Tile mouseTile = board.getTileAt(mouse.getCoords());

        considerMoveToTile(mouseTile);
    }

    public void considerMoveToTile(Tile aTile)
    {
        Player currentPlayer = h.getGame().getCurrentPlayer();

        //if there is a selected unit (and tile)
        if(currentPlayer.getSelectedTile() != null && currentPlayer.getSelectedTile().getSelectedUnit() != null)
        {
            Tile selectedTile = currentPlayer.getSelectedTile();
            Unit selectedUnit = currentPlayer.getSelectedUnit();

            //if the given tile is adjacent and contains an enemy, attack it
            if (selectedTile.isAdjacent(aTile) && aTile.firstUnit() != null && aTile.firstUnit().getPlayerNumber() != currentPlayer.getPlayerNumber())
            {
                Unit defender = aTile.firstUnit();
                selectedUnit.attack(defender, aTile);
            }
            //otherwise move to the tile/move towards it
            else
            {
                pathTo(aTile);
            }
        }
    }

    public void killUnit(Unit aUnit)
    {
        Player currentPlayer = h.getGame().getPlayer(aUnit.getPlayerNumber());
        Tile locTile = aUnit.getLocTile();

        locTile.deselectUnit(aUnit);
        locTile.removeUnit(aUnit);

        currentPlayer.deselectUnit();
        currentPlayer.removeUnit(aUnit);

        //if it cannot find a new unit to select
        if (!locTile.selectUnit())
        {
            //deselect the tile
            currentPlayer.setSelectedTile(null);
        }
    }
}
