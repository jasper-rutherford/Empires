package GameStuff.Board;

import Framework.Handler;
import Framework.MouseStuff.Mouse;
import GameStuff.Player;
import GameStuff.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        //if the tile in question is not null
        if (aTile != null)
        {
            //if the tile has a building that belongs to the current player, select it
            if (aTile.hasBuilding() && aTile.getBuilding().getPlayerNumber() == currentPlayer.getPlayerNumber())
            {
                h.println("building!");
            }
            //if the tile has units that belong to the player
            else if (aTile.hasUnits() && aTile.firstUnit().getPlayerNumber() == currentPlayer.getPlayerNumber())
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
            //if there are no buildings and no units
            else
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

//    /**
//     * attempts to find a path to the tile the mouse is above
//     * <p>
//     * im not explaining this
//     * https://en.wikipedia.org/wiki/Pathfinding
//     * scroll down to 'sample algorithm'
//     */
//    public void pathTo(Tile goal)
//    {
//        Tile selectedTile = h.getGame().getCurrentPlayer().getSelectedTile();
//
//        boolean foundPath = false;
//        boolean donePathing = selectedTile.getSelectedUnit() == null;
//
//
//        ArrayList<Tile> bank = new ArrayList<>();
//        ArrayList<Tile> now = new ArrayList<>();
//        ArrayList<Tile> next = new ArrayList<>();
//        ArrayList<Integer> dists = new ArrayList<>();
//
//        now.add(goal);
//        dists.add(0);
//
//
//        while (!donePathing)
//        {
//            //gives each tile a value that is essentially the distance to the goal
//            for (Tile tile : now)
//            {
//                for (int lcv = 0; lcv < 6; lcv++)
//                {
//                    Tile adjTile = tile.adjTile(lcv);
//
//                    if (adjTile != null && !bank.contains(adjTile) && !now.contains(adjTile) && !next.contains(adjTile) && selectedTile.getSelectedUnit().canStep(adjTile))
//                    {
//                        next.add(adjTile);
//
//                        if (adjTile.equals(selectedTile))
//                        {
//                            foundPath = true;
//                            donePathing = true;
//                        }
//                    }
//                }
//            }
//
//            if (next.size() == 0)
//            {
//                bank.addAll(now);
//                now.clear();
//
//                donePathing = true;
//            }
//            else
//            {
//                int count = dists.get(dists.size() - 1) + 1;
//                for (Tile tile : next)
//                {
//                    dists.add(count);
//                }
//
//                bank.addAll(now);
//                now.clear();
//                now.addAll(next);
//                next.clear();
//            }
//        }
//
//        if (foundPath)
//        {
//            //choose a tile adjacent to the selected tile with the lowest distance (choose randomly between ties)
//
//            //find the lowest distance
//            int lowDist = -1;
//            for (int lcv = 0; lcv < 6; lcv++)
//            {
//                Tile adjTile = selectedTile.adjTile(lcv);
//
//                if (bank.contains(adjTile))
//                {
//                    int adjDist = dists.get(bank.indexOf(adjTile));
//
//                    if (lowDist == -1)
//                    {
//                        lowDist = adjDist;
//                    }
//                    else if (adjDist < lowDist)
//                    {
//                        lowDist = adjDist;
//                    }
//                }
//            }
//
//            //find all the tiles with that distance
//            ArrayList<Tile> ties = new ArrayList<>();
//
//            for (int lcv = 0; lcv < 6; lcv++)
//            {
//                Tile adjTile = selectedTile.adjTile(lcv);
//
//                if (bank.contains(adjTile) && dists.get(bank.indexOf(adjTile)) == lowDist)
//                {
//                    ties.add(adjTile);
//                }
//            }
//
//            //randomly select one of those tiles
//            int randomIndex = (int) (Math.random() * ties.size());
//
//            //try to move the unit there
//            moveSelected(ties.get(randomIndex));
//        }
//    }

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

//    /**
//     * attempt to find a path from the selectedTile to the tile the mouse is above, then move the selected unit closer to that tile
//     */
//    public void pathToMouse()
//    {
//        Mouse mouse = h.getMouse();
//
//        Tile mouseTile = board.getTileAt(mouse.getCoords());
//
//        pathTo(mouseTile);
//    }

    public void considerMoveToMouse()
    {
        Mouse mouse = h.getMouse();

        Tile mouseTile = board.getTileAt(mouse.getCoords());

        considerMoveToTile(mouseTile);
    }

    public void considerMoveToTile(Tile aTile)
    {
        Player currentPlayer = h.getGame().getCurrentPlayer();

        //if there is a selected tile and unit
        if (currentPlayer.getSelectedTile() != null && currentPlayer.getSelectedTile().getSelectedUnit() != null)
        {
            Tile selectedTile = currentPlayer.getSelectedTile();
            Unit selectedUnit = currentPlayer.getSelectedUnit();

            //if the given tile is adjacent and contains an enemy, attack it
            if (selectedTile.isAdjacent(aTile) && aTile.firstUnit() != null && aTile.firstUnit().getPlayerNumber() != currentPlayer.getPlayerNumber())
            {
                Unit defender = aTile.firstUnit();
                selectedUnit.attack(defender, aTile);
            }
            //otherwise move to the tile/move towards it if the given tile isn't the selected tile (i.e. does not try to move from a to b if a == b)
            else if (!selectedTile.equals(aTile))
            {
                //generate a path from the selected tile to the tile in question
                PathNode path = generatePath(selectedTile, aTile, selectedUnit);

                if (path != null)
                {
                    //selects a random valid option
                    int optionIndex = h.getRandom().nextInt(path.numOptions());

                    //moves the unit to that random option
                    moveSelected(path.getTile(optionIndex));
                }
                else
                {
                    h.println("Generated Path is Null (Couldn't find one?");
                }
            }
        }
    }

    //ensure that they arent the same tile, this doesnt handle that
    public PathNode generatePath(Tile fromTile, Tile toTile, Unit selectedUnit)
    {
        /*
         *  A Map that simultaneously tracks which Tiles have been searched, and also maps each tile to a PathNode
         *
         *  Example:
         *      searched.containsKey(exampleTile)   -> whether or not exampleTile has been searched
         *      searched.get(exampleTile)           -> PathNode representation of exampleTile
         */
        Map<Tile, PathNode> searched = new HashMap<Tile, PathNode>();

        //add a PathNode for the toTile to searched
        searched.put(toTile, new PathNode(toTile, 0));

        /*
         *  A Map that stores all the searched tiles in lists based on how many moves must be made to get from them to toTile
         *
         *  Example:
         *      levels.get(0)   -> Level 0 is an arraylist of all the tiles that are 0 tiles away from toTile (aka just toTile)
         *      levels.get(1)   -> Level 1 is an arraylist of all the tiles that are 1 tile away from toTile (aka valid adjacent tiles)
         *      etc.
         */
        Map<Integer, ArrayList<Tile>> levels = new HashMap<Integer, ArrayList<Tile>>();

        //set up the 0th level
        ArrayList<Tile> levelZero = new ArrayList<>();
        levelZero.add(toTile);
        levels.put(0, levelZero);

        //tracks what level the loop is on (each pass of the while loop is one level)
        int level = 0;

        //if a valid path is found, this is what will be returned
        PathNode outPath = null;

        //used to check if all possible tiles have been searched
        int oldKeyCount = 1;

        //tracks if the loop is finished
        boolean containsFromTile = false;   //true when fromTile is found and added to the searched map
        boolean searchedAllTiles = false;   //true when the while loop does a full pass without adding any new tiles to the searched map
        while (!containsFromTile && !searchedAllTiles)
        {
            //gets the current level
            ArrayList<Tile> currentLevel = levels.get(level);

            //generates a list for the next level
            level++;
            ArrayList<Tile> nextLevel = new ArrayList<>();
            levels.put(level, nextLevel);

            //loop through each tile in the current level
            for (Tile currTile : currentLevel)
            {
                //loop through each Tile adjacent to currTile
                for (Tile adjTile : currTile.getAdjTiles())
                {
                    //considers the tile if the selectedUnit can step on the tile
                    if (selectedUnit.canStep(adjTile))
                    {
                        //if the adjacent tile has not yet been searched
                        if (!searched.containsKey(adjTile))
                        {
                            //generate a new PathNode for it
                            PathNode adjNode = new PathNode(adjTile, level);    // creates the actual node
                            searched.put(adjTile, adjNode);                     // maps the adjTile to the adjNode
                            adjNode.addOption(searched.get(currTile));          // adds the currNode to the adjNode's options

                            //add the tile to the next level
                            nextLevel.add(adjTile);

                            //checks if the tile is the fromTile
                            if (adjTile.equals(fromTile))
                            {
                                containsFromTile = true;
                                outPath = adjNode;
                            }
                        }
                        //if the adjTile has been searched
                        else
                        {
                            PathNode adjNode = searched.get(adjTile);
                            PathNode currNode = searched.get(currTile);

                            //if the adjNode is a level higher than currNode, currNode is added to adjNode's options
                            if (adjNode.getLevel() == currNode.getLevel() + 1)
                            {
                                adjNode.addOption(currNode);
                            }
                        }
                    }
                }
            }

            //if no new tiles are searched in a given level, then all possible tiles have been searched
            searchedAllTiles = oldKeyCount == searched.keySet().size();

            //update the old count for use in next pass of the loop
            oldKeyCount = searched.keySet().size();
        }

        return outPath;
    }
}
