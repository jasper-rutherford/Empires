package GameStuff;

import Framework.Handler;

import java.util.ArrayList;

public class Player
{
    private Handler h;

    private int turnCount;
    private ArrayList<Unit> units;
    private ArrayList<Unit> tiredUnits;

    private Tile selectedTile;
    private Unit selectedUnit;

    public Player(Handler h)
    {
        this.h = h;
    }

}
