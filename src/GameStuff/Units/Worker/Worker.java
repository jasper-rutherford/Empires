package GameStuff.Units.Worker;

import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Menus.Menu;
import GameStuff.Units.Unit;
import GameStuff.Units.Worker.WorkerMenu.WorkerMenu;

import java.awt.*;


public class Worker extends Unit
{
    private Handler h;

    private Menu workerMenu;

    public Worker(Handler h, Tile locTile, int id, int playerNumber)
    {
        super(h, locTile, id, playerNumber, "worker");

        this.h = h;

        //creates a menu for the worker and adds it to the menumanager
        workerMenu = new WorkerMenu(h, true, this);
        h.getMenuManager().add(workerMenu);

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
        workerMenu.enable();
    }

    public void deselect()
    {
        workerMenu.disable();
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

