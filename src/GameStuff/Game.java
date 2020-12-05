package GameStuff;

import Framework.Handler;
import GameStuff.Units.Unit;

import java.awt.*;

/**
 * Class that manages the actual game elements
 */
public class Game
{
    private Handler h;

    private Board board;
    private BoardManager boardManager;

    private Player[] players;
    private Player currentPlayer;
    private int currentPlayerIndex;

    private int turnCount;


    public Game(Handler h, int numPlayers)
    {
        this.h = h;

        board = new Board(h, 75, 50);
        boardManager = new BoardManager(h, board);

        initializePlayers(numPlayers);

        turnCount = 0;
    }

    private void initializePlayers(int numPlayers)
    {
        players = new Player[numPlayers];

        for (int lcv = 0; lcv < players.length; lcv++)
        {
            players[lcv] = new Player(h, lcv + 1);
        }

        currentPlayer = players[0];

        currentPlayerIndex = 0;
    }

    public void render(Graphics g)
    {
        board.render(g);

        if (currentPlayer.getSelectedTile() != null && currentPlayer.getChosenUnit() != null)
        {
            renderSelectedUnitInfo(g);
        }

        int screenWidth = h.getScreenWidth();
        g.setColor(Color.BLACK);
        g.drawString("Player: " + (currentPlayerIndex + 1), screenWidth / 2, 300);

        g.setColor(Color.gray);
        g.drawString(turnCount + "", 50, 50);

        currentPlayer.render(g);
    }

    public Board getBoard()
    {
        return board;
    }

    public void renderSelectedUnitInfo(Graphics g)
    {
//        int screenWidth = h.getScreenWidth();
//        int screenHeight = h.getScreenHeight();
//
//        Unit selectedUnit = currentPlayer.getSelectedUnit();
//
//        g.setColor(new Color(91, 238, 74));
//        g.fillRect(10, screenHeight - 110, 200, 100);
//
//        g.setColor(Color.black);
//        g.drawRect(10, screenHeight - 110, 200, 100);
//
//        g.drawString("ID: " + selectedUnit.getID(), 60, screenHeight - 60);
//        g.drawString("Moves: " + selectedUnit.getMoveEnergy() + "/" + selectedUnit.getMaxMoveEnergy(), 110, screenHeight - 60);
//        g.drawString("Health: " + selectedUnit.getHealth() + "/" + selectedUnit.getMaxHealth(), 85, screenHeight - 90);
        if (currentPlayer.getChosenUnit() != null)
        {
            currentPlayer.getChosenUnit().renderInfo(g);
        }
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void nextPlayer()
    {
        //deselect the current selected tile
        if (currentPlayer.getSelectedTile() != null)
        {
            boardManager.deselectTile(currentPlayer.getSelectedTile());
        }

        //advance to the next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        currentPlayer = players[currentPlayerIndex];

        //if all players have taken their turn then advance the turn count
        if (currentPlayerIndex == 0)
        {
            turnCount++;
        }

        //select the new player's selected unit if it still exists
        if (currentPlayer.getSelectedTile().hasUnit(currentPlayer.getChosenUnit()))
        {
            boardManager.selectTile(currentPlayer.getSelectedTile(), currentPlayer.getChosenUnit());
        }
        else
        {
            currentPlayer.setSelectedTile(null);
            currentPlayer.setChosenUnit(null);
        }
    }

    public BoardManager getBoardManager()
    {
        return boardManager;
    }

    public int getCurrentPlayerIndex()
    {
        return currentPlayerIndex;
    }

    public Player getPlayer(int playerNumber)
    {
        return players[playerNumber - 1];
    }
}
