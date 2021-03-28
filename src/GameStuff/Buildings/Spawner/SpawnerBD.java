package GameStuff.Buildings.Spawner;

import Framework.Handler;
import GameStuff.Board.Tile;
import GameStuff.Buildings.Building;

public class SpawnerBD extends Building
{
    Handler h;

    public SpawnerBD(Handler h, Tile locTile, int playerNumber)
    {
        super(h, locTile, playerNumber);

        readSprite("/box.png");

    }
}
