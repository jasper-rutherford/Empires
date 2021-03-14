package Buttons;

import Framework.Handler;
import GameStuff.Tile;
import GameStuff.Units.Worker;

import java.awt.*;

public class WorkerHarvestButton extends Button
{
    private Handler h;
    private Worker worker;

    public WorkerHarvestButton(Handler h, Color color, Worker worker)
    {
        super(h, new Rectangle(-1, -1, -1, -1), true);

        int x = 10;
        int y = h.getScreenHeight() - 420;
        int width = 20;
        int height = 20;
        setSpace(new Rectangle(x, y, width, height));

        setColor(color);
        setBorderColor(Color.BLACK);

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

    public String toString()
    {
        return "Worker Harvest Button";
    }
}
