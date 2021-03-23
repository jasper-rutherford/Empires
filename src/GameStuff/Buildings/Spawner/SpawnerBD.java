package GameStuff.Buildings.Spawner;

import Framework.Handler;
import GameStuff.Buildings.Building;

public class SpawnerBD extends Building
{
    Handler h;

    public SpawnerBD(Handler h)
    {
        super(h);

        readSprite("/box.png");

    }
}
