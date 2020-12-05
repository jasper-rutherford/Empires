package GameStuff;

import Framework.Handler;
import GameStuff.Units.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Player
{
    private Handler h;
    private int playerNumber;


    private ArrayList<Unit> units;
    private ArrayList<Unit> tiredUnits;

    private Tile selectedTile;
    private Unit chosenUnit;

    private Color teamColor;

    private HashMap<String, Integer> resources;

    public Player(Handler h, int playerNumber)
    {
        this.h = h;
        this.playerNumber = playerNumber;

        units = new ArrayList<>();
        tiredUnits = new ArrayList<>();

        Random rand = new Random();
        teamColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

        resources = new HashMap<>();
    }

    public void restUnits()
    {
        for (Unit unit : tiredUnits)
        {
            unit.refillMoves();
        }

        tiredUnits.clear();
    }

    public void addUnit(Unit unit)
    {
        units.add(unit);
    }

    public Tile getSelectedTile()
    {
        return selectedTile;
    }

//    public void select(Tile aTile)
//    {
//        if (aTile != null)
//        {
//            if (selectedTile != null && !selectedTile.equals(aTile))
//            {
//                selectedTile.deselect();
//            }
//
//            selectedTile = aTile;
//            selectedTile.select();
//        }
//    }

    public void setChosenUnit(Unit unit)
    {
        chosenUnit = unit;
    }

    public Unit getChosenUnit()
    {
        return chosenUnit;
    }

    /**
     * returns if the player controls the given unit
     *
     * @param unit the unit to check ownership of
     * @return if the player controls the given unit
     */
    public boolean hasUnit(Unit unit)
    {
        return units.contains(unit);
    }

    public ArrayList<Unit> getTiredUnits()
    {
        return tiredUnits;
    }

    public void setSelectedTile(Tile aTile)
    {
        selectedTile = aTile;
    }

    public Color getTeamColor()
    {
        return teamColor;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public void removeUnit(Unit aUnit)
    {
        if (units.contains(aUnit))
        {
            units.remove(aUnit);
        }
        if (tiredUnits.contains(aUnit))
        {
            tiredUnits.remove(aUnit);
        }
    }

    public void addResources(String type, int amount)
    {
        if (resources.keySet().contains(type))
        {
            resources.put(type, resources.get(type) + amount);
        }
        else
        {
            resources.put(type, amount);
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);

        Iterator<String> types = resources.keySet().iterator();
        int lcv = 0;
        while (types.hasNext())
        {
            String type = types.next();
            int amount = resources.get(type);

            g.drawString(type + ": " + amount, 20 + lcv * 50, 20);
            lcv++;
        }
    }

    public void addTiredUnit(Unit aUnit)
    {
        if (!tiredUnits.contains(aUnit))
        {
            tiredUnits.add(aUnit);
        }
    }

    public void clearSelectedUnit()
    {
        chosenUnit = null;
    }

//    /**
//     * Clears the selected unit from this player.
//     * <p>
//     * IT DOES NOTHING ELSE
//     * <p>Use this with Tile.deselectUnit();
//     */
//    public void deselectUnit()
//    {
//        selectedUnit = null;
//    }
}