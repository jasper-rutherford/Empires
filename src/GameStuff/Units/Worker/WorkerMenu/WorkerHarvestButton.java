package GameStuff.Units.Worker.WorkerMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Units.Worker.Worker;

import java.awt.*;

public class WorkerHarvestButton extends Button
{
    private Handler h;
    private Worker worker;

    public WorkerHarvestButton(Handler h, Worker worker)
    {
        super(h, true, true, new Rectangle(-1, -1, -1, -1));

        int x = 10;
        int y = h.getScreenHeight() - 420;
        int width = 20;
        int height = 20;
        setSpace(new Rectangle(x, y, width, height));

        setColor(worker.getTeamColor());
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
