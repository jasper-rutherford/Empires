package Buttons;

import Framework.Handler;
import GameStuff.Tile;
import GameStuff.Units.Worker;

import java.awt.*;

public class WorkerHarvestButton extends Button
{
    private Handler h;
    private Worker worker;

    public WorkerHarvestButton(Handler h, Polygon space, boolean enabled, Color color, Worker worker)
    {
        super(h, space, enabled, color);

        this.h = h;
        this.worker = worker;
    }

    public void activate()
    {
        //check loctile for resources
        Tile locTile = worker.getLocTile();

        if (worker.hasEnergy() && locTile.hasResources())
        {
            String resourceType = locTile.getResourceType();
            int amountHarvested = locTile.harvestResources(worker.getStrength());

            h.getGame().getCurrentPlayer().addResources(resourceType, amountHarvested);

            worker.takeEnergy(1);

            h.getGame().getCurrentPlayer().addTiredUnit(worker);
        }
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
