package GameStuff;

import Framework.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player
{
    private Handler h;
    private int playerNumber;


    private ArrayList<Unit> units;
    private ArrayList<Unit> tiredUnits;

    private Tile selectedTile;
    private Unit selectedUnit;

    private Color teamColor;

    public Player(Handler h, int playerNumber)
    {
        this.h = h;
        this.playerNumber = playerNumber;

        units = new ArrayList<>();
        tiredUnits = new ArrayList<>();

        Random rand = new Random();
        teamColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
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

    public void setSelectedUnit(Unit unit)
    {
        selectedUnit = unit;
    }

    public Unit getSelectedUnit()
    {
        return selectedUnit;
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
}