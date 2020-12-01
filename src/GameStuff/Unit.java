package GameStuff;

import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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


    public Unit(Handler h, Tile locTile, int id, int playerNumber)
    {
        this.h = h;
        this.locTile = locTile;
        this.id = id;
        this.playerNumber = playerNumber;

        maxMoveEnergy = 2;
        moveEnergy = maxMoveEnergy;

        try
        {
            icon = ImageIO.read(getClass().getResourceAsStream("/unit.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        teamColor = h.getGame().getCurrentPlayer().getTeamColor();

        maxHealth = 10;
        health = maxHealth;

        strength = 1;
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
        moveEnergy -= take;
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
            die(aTile);
        }
    }

    public void die(Tile aTile)
    {
        Player defendingPlayer = h.getGame().getPlayer(playerNumber);
        defendingPlayer.removeUnit(this);

        aTile.removeUnit(this);
    }
}
