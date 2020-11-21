package Framework;

import GameStuff.Board;
import GameStuff.Unit;

import java.awt.*;

public class Game
{
    private Handler h;

    private Board board;

    public Game(Handler h)
    {
        this.h = h;
        board = new Board(h, 75, 50);
    }

    public void render(Graphics g)
    {
        board.render(g);

        if (board.getSelectedTile() != null && board.getSelectedTile().getSelectedUnit() != null)
        {
            renderSelectedUnitInfo(g);
        }
    }

    public Board getBoard()
    {
        return board;
    }

    public void renderSelectedUnitInfo(Graphics g)
    {
        int screenWidth = h.getScreenWidth();
        int screenHeight = h.getScreenHeight();

        Unit selectedUnit = board.getSelectedTile().getSelectedUnit();

        g.setColor(new Color(91, 238, 74));
        g.fillRect(10, screenHeight - 110, 200, 100);

        g.setColor(Color.black);
        g.drawRect(10, screenHeight - 110, 200, 100);

        g.drawString("ID: " + selectedUnit.getID(), 60, screenHeight - 60);
        g.drawString("Moves: " + selectedUnit.getMoveEnergy() + "/" + selectedUnit.getMaxMoveEnergy(), 110, screenHeight - 60);
    }
}
