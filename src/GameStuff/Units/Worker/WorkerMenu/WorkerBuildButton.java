package GameStuff.Units.Worker.WorkerMenu;

import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Buildings.Building;
import GameStuff.Buildings.Spawner.SpawnerBD;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Units.Worker.Worker;

import java.awt.*;

public class WorkerBuildButton extends Button
{
    private Handler h;
    private Worker worker;

    public WorkerBuildButton(Handler h, Worker worker)
    {
        super(h, new Rectangle(-1, -1, -1, -1), true);

        int x = 10;
        int y = h.getScreenHeight() - 390;
        int width = 20;
        int height = 20;
        setSpace(new Rectangle(x, y, width, height));

        setColor(worker.getTeamColor());
        setBorderColor(Color.BLACK);

        this.h = h;
        this.worker = worker;
    }

    public void render(Graphics g)
    {
        super.render(g);

        //draw an x to distinguish from the other button
        g.setColor(Color.BLACK);
        g.drawLine(10, getSpace().y + 20, 30, getSpace().y);
        g.drawLine(10, getSpace().y, 30, getSpace().y + 20);
    }

    public void activate()
    {
        h.println("build");

        //how much stone/wood the current player has
        int numStone = h.getGame().getCurrentPlayer().getResourceCount("stone");
        int numWood = h.getGame().getCurrentPlayer().getResourceCount("wood");

        //if the worker has energy and the player has resources, build a building
        if (!worker.getLocTile().hasResources() && worker.hasEnergy() && numStone > 0 && numWood > 0)
        {
            //spend resources
            h.getGame().getCurrentPlayer().takeResources("stone", 1);
            h.getGame().getCurrentPlayer().takeResources("wood", 1);

            //spend energy
            worker.takeEnergy(1);
            h.getGame().getCurrentPlayer().addTiredUnit(worker);

            //build the building
            Building building = new SpawnerBD(h, worker.getLocTile(), worker.getPlayerNumber());
            worker.getLocTile().setBuilding(building);

            worker.getLocTile().removeUnit(worker);
            building.addUnit(worker);

            worker.getLocTile().deselect();
            h.getGame().getCurrentPlayer().setSelectedTile(null);

        }
    }
}
