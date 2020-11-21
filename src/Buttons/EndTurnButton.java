package Buttons;

import Framework.Handler;
import GameStuff.Unit;

import java.awt.*;
import java.util.ArrayList;

public class EndTurnButton extends Button
{
    private Handler h;

    public EndTurnButton(Handler h, Polygon space, boolean enabled, Color color)
    {
        super(h, space, enabled, color);

        this.h = h;
    }

    public void activate()
    {
        restUnits();

        h.getGame().getBoard().increaseTurnCount();
    }

    private void restUnits()
    {
        ArrayList<Unit> tiredUnits = h.getGame().getBoard().getTiredUnits();

        for (Unit unit : tiredUnits)
        {
            unit.refillMoves();
        }

        tiredUnits.clear();
    }
}
