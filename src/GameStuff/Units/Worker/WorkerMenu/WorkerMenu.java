package GameStuff.Units.Worker.WorkerMenu;

import GameStuff.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;
import GameStuff.Units.Worker.Worker;


public class WorkerMenu extends Menu
{
    private Handler h;

    public WorkerMenu(Handler h, boolean isActive, Worker worker)
    {
        super(h, isActive);

        this.h = h;

        //create/add the worker button
        Button workerHarvestButton = new WorkerHarvestButton(h, worker);
        addButton(workerHarvestButton);
    }
}
