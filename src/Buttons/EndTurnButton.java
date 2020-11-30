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
        h.getGame().getCurrentPlayer().restUnits();

        h.getGame().nextPlayer();
    }

    private void restUnits()
    {
        ArrayList<Unit> tiredUnits = h.getGame().getCurrentPlayer().getTiredUnits();

        for (Unit unit : tiredUnits)
        {
            unit.refillMoves();
        }

        tiredUnits.clear();
    }

    public void render(Graphics g)
    {
        Polygon space = getSpace();

        g.setColor(h.getGame().getCurrentPlayer().getTeamColor());
        g.fillPolygon(space);

        g.setColor(Color.black);
        g.drawPolygon(space);
    }
}
