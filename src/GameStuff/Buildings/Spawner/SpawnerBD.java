package GameStuff.Buildings.Spawner;

import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Buildings.Building;

public class SpawnerBD extends Building
{
    Handler h;

    public SpawnerBD(Handler h, Tile locTile)
    {
        super(h, locTile);

        readSprite("/box.png");

    }
}
