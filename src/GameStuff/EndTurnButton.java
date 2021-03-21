package GameStuff;

import GameStuff.Buttons.Button;
import Framework.Handler;
import GameStuff.Units.Unit;

import java.awt.*;
import java.util.ArrayList;

public class EndTurnButton extends Button
{
    private Handler h;

    public EndTurnButton(Handler h, Color color)
    {
        super(h, new Rectangle(-1, -1, -1, -1), true);

        this.h = h;

        int x = h.getScreenWidth() - 100;
        int y = h.getScreenHeight() - 100;
        int width = 90;
        int height = 90;
        setSpace(new Rectangle(x, y, width, height));

        setColor(color);
        setBorderColor(Color.BLACK);
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
        setColor(h.getGame().getCurrentPlayer().getTeamColor());
        super.render(g);
    }

    public String toString()
    {
        return "End Turn Button";
    }
}
