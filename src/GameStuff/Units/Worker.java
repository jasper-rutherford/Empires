package GameStuff.Units;

import Buttons.WorkerHarvestButton;
import Framework.Handler;
import GameStuff.Tile;

import java.awt.*;

public class Worker extends Unit
{
    private Handler h;
    private WorkerHarvestButton button;

    public Worker(Handler h, Tile locTile, int id, int playerNumber)
    {
        super(h, locTile, id, playerNumber, "worker");

        this.h = h;

        button = new WorkerHarvestButton(h, getTeamColor(), this);
        h.getButtonManager().addButton(button);

        setIcon(h.getGame().getBoard().getTexture("worker"));
    }

    public void renderInfo(Graphics g)
    {
        int screenWidth = h.getScreenWidth();
        int screenHeight = h.getScreenHeight();

        Unit selectedUnit = h.getGame().getCurrentPlayer().getSelectedUnit();

        g.setColor(new Color(91, 238, 74));
        g.fillRect(10, screenHeight - 110, 200, 100);

        g.setColor(Color.black);
        g.drawRect(10, screenHeight - 110, 200, 100);

        g.drawString("ID: " + selectedUnit.getID(), 60, screenHeight - 60);
        g.drawString("Moves: " + selectedUnit.getMoveEnergy() + "/" + selectedUnit.getMaxMoveEnergy(), 110, screenHeight - 60);
        g.drawString("Health: " + selectedUnit.getHealth() + "/" + selectedUnit.getMaxHealth(), 85, screenHeight - 90);
    }

    public void select()
    {
        button.enable();
    }

    public void deselect()
    {
        button.disable();
    }

    public void render(Graphics g)
    {
        super.render(g);
    }

    public void attack(Unit defender, Tile aTile)
    {
        //do nothing, workers cannot attack
    }
}

