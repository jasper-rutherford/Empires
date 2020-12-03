package GameStuff.Units;

import Framework.Handler;
import GameStuff.Player;
import GameStuff.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Unit
{
    private Handler h;
    private Tile locTile;
    private int id;

    private int playerNumber;

    private int maxMoveEnergy;
    private int moveEnergy;

    private BufferedImage icon;

    private Color teamColor;

    private int maxHealth;
    private int health;

    private int strength;

    private String type;


    public Unit(Handler h, Tile locTile, int id, int playerNumber, String type)
    {
        this.h = h;
        this.locTile = locTile;
        this.id = id;
        this.playerNumber = playerNumber;

        maxMoveEnergy = 5;
        moveEnergy = maxMoveEnergy;


        icon = h.getGame().getBoard().getTexture("unit");

        teamColor = h.getGame().getCurrentPlayer().getTeamColor();

        maxHealth = 10;
        health = maxHealth;

        strength = 1;

        this.type = type;
    }

    public void render(Graphics g)
    {
        int width = h.getGame().getBoard().getSideLength() / 2 * health / maxHealth;

        g.drawImage(icon, locTile.getXCoord() - width, locTile.getYCoord() - width, 2 * width, 2 * width, null);
    }

    public void setLocTile(Tile locTile)
    {
        this.locTile = locTile;
    }

    public boolean canStep(Tile aTile)
    {
        return aTile.firstUnit() == null || aTile.firstUnit().getPlayerNumber() == playerNumber;
    }

    public void refillMoves()
    {
        moveEnergy = maxMoveEnergy;
    }

    public boolean hasEnergy()
    {
        return moveEnergy > 0;
    }

    public void takeEnergy(int take)
    {
        if (moveEnergy >= take)
        {
            moveEnergy -= take;
        }
        else
        {
            moveEnergy = 0;
        }
    }

    public int getID()
    {
        return id;
    }

    public int getMoveEnergy()
    {
        return moveEnergy;
    }

    public int getMaxMoveEnergy()
    {
        return maxMoveEnergy;
    }

    public Color getTeamColor()
    {
        return teamColor;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public void attack(Unit defender, Tile aTile)
    {
        if (moveEnergy > 0)
        {
            defender.decreaseHealth(strength, aTile);
            moveEnergy--;

            ArrayList<Unit> tiredUnits = h.getGame().getCurrentPlayer().getTiredUnits();
            //add to the current player's tired units if not already on the list
            if (!tiredUnits.contains(this))
            {
                tiredUnits.add(this);
            }
        }
    }

    public void decreaseHealth(int damage, Tile aTile)
    {
        health -= damage;

        if (health <= 0)
        {
            h.getGame().getBoardManager().killUnit(this);
        }
    }

    public void die(Tile aTile)
    {
        Player defendingPlayer = h.getGame().getPlayer(playerNumber);
        defendingPlayer.removeUnit(this);

        aTile.removeUnit(this);
    }

    public void renderInfo(Graphics g)
    {
        int screenWidth = h.getScreenWidth();
        int screenHeight = h.getScreenHeight();

        Unit selectedUnit = h.getGame().getCurrentPlayer().getSelectedUnit();

        g.setColor(new Color(91, 238, 74));
        g.fillRect(10, screenHeight - 110, 200, 100);

        g.setColor(Color.black);
        g.drawRect(10, screenHeight - 110, 200, 100);

        g.drawString("ID: " + selectedUnit.getID(), 60, screenHeight - 60);
        g.drawString("Moves: " + selectedUnit.getMoveEnergy() + "/" + selectedUnit.getMaxMoveEnergy(), 110, screenHeight - 60);
        g.drawString("Health: " + selectedUnit.getHealth() + "/" + selectedUnit.getMaxHealth(), 85, screenHeight - 90);
    }

    public void select()
    {

    }

    public void deselect()
    {

    }

    public Tile getLocTile()
    {
        return locTile;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setIcon(BufferedImage icon)
    {
        this.icon = icon;
    }
}
