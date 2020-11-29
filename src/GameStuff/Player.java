package GameStuff;

import Framework.Handler;

import java.util.ArrayList;

public class Player
{
    private Handler h;

    private ArrayList<Unit> units;
    private ArrayList<Unit> tiredUnits;

    private Tile selectedTile;
    private Unit selectedUnit;

    public Player(Handler h)
    {
        this.h = h;

        units = new ArrayList<>();
        tiredUnits = new ArrayList<>();
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
}