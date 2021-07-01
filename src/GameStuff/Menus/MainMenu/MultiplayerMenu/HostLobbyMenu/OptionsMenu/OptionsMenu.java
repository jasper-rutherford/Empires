package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.OptionsMenu;

import Framework.Handler;
import GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.PlayersMenu.PlayersMenu;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import java.awt.*;

public class OptionsMenu extends Menu
{
    public OptionsMenu(Handler h, boolean isEnabled)
    {
        super(h, isEnabled);

        //Players Button
        MenuButton.create(
                h,
                new Rectangle(400, 200, 50, 50),
                isEnabled,
                this,
                new PlayersMenu(h, false, this),
                new Color(165, 20, 20),
                new Color(1, 1, 1),
                null,
                "Players");
    }

    //TODO
    public void render(Graphics g)
    {
        g.setColor(new Color(171, 22, 163));
        g.fillRect(100, 300, 100, 200);

        g.setColor(Color.black);
        g.drawString("[options]", 125, 325);

        super.render(g);
    }
}
