package Framework;

import GameStuff.*;
import GameStuff.Board.Board;
import GameStuff.Board.BoardManager;
import GameStuff.Board.Tile;
import GameStuff.Buttons.ButtonManager;
import GameStuff.Menus.MenuManager;
import GameStuff.Units.Unit;

import java.awt.*;

/**
 * Class that manages the actual game elements
 */
public class Game
{
    private Handler h;

    private boolean hasBoard;
    private Board board;
    private BoardManager boardManager;

    private Player[] players;
    private Player currentPlayer;
    private int currentPlayerIndex;

    private int turnCount;

    private ButtonManager buttonManager;
    private MenuManager menuManager;

    public Game(Handler h)
    {
        this.h = h;

        //create general button manager and add misc buttons
        buttonManager = new ButtonManager(h);
        menuManager = new MenuManager(h);

        hasBoard = false;
//        board = new Board(h, 75, 50);
//        boardManager = new BoardManager(h, board);

//        initializePlayers(numPlayers);

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
        if (hasBoard)
        {
            board.render(g);

            if (currentPlayer.getSelectedTile() != null && currentPlayer.getSelectedUnit() != null)
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

        buttonManager.render(g);
        menuManager.render(g);
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
        if (currentPlayer.getSelectedUnit() != null)
        {
            currentPlayer.getSelectedUnit().renderInfo(g);
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
            currentPlayer.getSelectedTile().deselect();
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
        Tile selectedTile = currentPlayer.getSelectedTile();
        Unit selectedUnit = currentPlayer.getSelectedUnit();
        if (selectedTile != null && selectedTile.hasUnit(selectedUnit))
        {
            currentPlayer.getSelectedTile().select(currentPlayer.getSelectedUnit());
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

    public MenuManager getMenuManager()
    {
        return menuManager;
    }

    public ButtonManager getButtonManager()
    {
        return buttonManager;
    }

    public void makeBoard(int numPlayers)
    {
        board = new Board(h, 75, 50);
        boardManager = new BoardManager(h, board);

        buttonManager.addButton(new EndTurnButton(h, new Color(99, 28, 215))); //end turn button

        initializePlayers(numPlayers);
    }

    public boolean hasBoard()
    {
        return hasBoard;
    }
}
