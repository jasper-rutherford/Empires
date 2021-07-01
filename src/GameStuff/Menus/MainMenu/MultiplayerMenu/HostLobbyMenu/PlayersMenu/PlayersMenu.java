package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.PlayersMenu;

import Framework.Handler;
import GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.OptionsMenu.OptionsMenu;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import java.awt.*;

public class PlayersMenu extends Menu
{
    public PlayersMenu(Handler h, boolean isEnabled, OptionsMenu optionsMenu)
    {
        super(h, isEnabled);

        //Options Button
        MenuButton.create(
                h,
                new Rectangle(400, 200, 50, 50),
                isEnabled,
                this,
                optionsMenu,
                new Color(171, 22, 163),
                new Color(1, 1, 1),
                null,
                "Options");
    }

    //TODO
    public void render(Graphics g)
    {
        g.setColor(new Color(165, 20, 20));
        g.fillRect(100, 300, 100, 200);

        g.setColor(Color.black);
        g.drawString("[players]", 125, 325);

        super.render(g);
    }
}
