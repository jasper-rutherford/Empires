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
     * KEEP
     *
     * @param aTile
     */
    public void considerTile(Tile aTile)
    {
        if (aTile != null)
        {
            Player player = h.getGame().getCurrentPlayer();

            //tile is already selected
            if (aTile.equals(player.getSelectedTile()))
            {
                //advance to the next unit
                aTile.chooseUnit();

                //update the player
                player.setChosenUnit(aTile.getChosenUnit());
            }
            //not already selected
            else
            {
                //the tile has a friendly unit
                if (player.hasUnit(aTile.firstUnit()))
                {
                    //deselect the old
                    deselectTile(player.getSelectedTile());

                    //select the new
                    selectTile(aTile);

                    //update the player
                    player.setSelectedTile(aTile);
                    player.setChosenUnit(aTile.getChosenUnit());
                }
                //does not have friendly units
                else
                {
                    //deselect the old
                    deselectTile(player.getSelectedTile());

                    //update the player
                    player.setSelectedTile(null);
                    player.setChosenUnit(null);
                }
            }
        }
    }

    /**
     * KEEP
     *
     * @param aTile
     */
    public void selectTile(Tile aTile)
    {
        if (aTile != null)
        {
            aTile.glow();
            aTile.chooseUnit();
        }
    }

    /**
     * KEEP
     *
     * @param aTile
     * @param aUnit
     */
    public void selectTile(Tile aTile, Unit aUnit)
    {
        if (aTile != null)
        {
            aTile.glow();
            aTile.chooseUnit(aUnit);
        }
    }

    /**
     * KEEP
     *
     * @param aTile
     */
    public void deselectTile(Tile aTile)
    {
        if (aTile != null)
        {
            aTile.stopGlowing();
            aTile.rejectUnit();
        }
    }

    public void deselectTile(Tile aTile, Unit aUnit)
    {
        if (aTile.getChosenUnit() != null && aTile.getChosenUnit().equals(aUnit))
        {
            aTile.stopGlowing();
            aTile.rejectUnit(aUnit);
        }
    }

//    /**
//     * tries to select the given tile, and tries to select the specified unit
//     *
//     * @param aTile the tile to try to select
//     * @param aUnit the unit to select
//     */
//    public void selectTile(Tile aTile, Unit aUnit)
//    {
//        Player currentPlayer = h.getGame().getCurrentPlayer();
//        Tile selectedTile = currentPlayer.getSelectedTile();
//
//        //if the tile in question isn't null and has the specified unit
//        if (aTile != null && aTile.hasUnits() && currentPlayer.hasUnit(aUnit))
//        {
//            //deselect the old
//            if (selectedTile != null)
//            {
//                selectedTile.deselect();
//            }
//
//            //set the new
//            currentPlayer.setSelectedTile(aTile);
//
//            //select the new
//            aTile.select(aUnit);
//
//            //set the current player's selected unit
//            currentPlayer.setSelectedUnit(aTile.getSelectedUnit());
//        }
//    }

//    /**
//     * tries to select the tile the mouse is above
//     */
//    public void selectMouseTile()
//    {
//        //the mouse
//        Mouse mouse = h.getMouse();
//
//        //get the tile the mouse is above
//        Tile mouseTile = board.getTileAt(mouse.getCoords());
//
//        //try to select it
//        selectTile(mouseTile);
//    }

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
        boolean donePathing = selectedTile.getChosenUnit() == null;


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

                    if (adjTile != null && !bank.contains(adjTile) && !now.contains(adjTile) && !next.contains(adjTile) && selectedTile.getChosenUnit().canStep(adjTile))
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
        Unit chosenUnit = currentPlayer.getChosenUnit();
        ArrayList<Unit> tiredUnits = currentPlayer.getTiredUnits();

        //if everything is valid and the selected unit has energy
        if (selectedTile != null && chosenUnit != null && aTile != null && chosenUnit.hasEnergy())
        {
            //remove the selected unit from its current tile
            selectedTile.removeUnit(chosenUnit);

            //add the unit to its new tile
            aTile.addUnit(chosenUnit);

            //take energy from the unit equivalent to the tile's move cost
            chosenUnit.takeEnergy(aTile.getMoveCost());

            //add the unit to the list of tired units if it wasn't already on there
            if (!tiredUnits.contains(chosenUnit))
            {
                tiredUnits.add(chosenUnit);
            }

            //deselect the old
            deselectTile(selectedTile);

            //update current player
            h.getGame().getCurrentPlayer().setSelectedTile(aTile);

            //select the new
            selectTile(aTile, chosenUnit);
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
        if (currentPlayer.getSelectedTile() != null && currentPlayer.getSelectedTile().getChosenUnit() != null)
        {
            Tile selectedTile = currentPlayer.getSelectedTile();
            Unit selectedUnit = currentPlayer.getChosenUnit();

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
        Player player = h.getGame().getPlayer(aUnit.getPlayerNumber());
        Tile locTile = aUnit.getLocTile();

        deselectTile(locTile, aUnit);
        locTile.removeUnit(aUnit);

        player.setChosenUnit(null);
        player.removeUnit(aUnit);
    }
}
