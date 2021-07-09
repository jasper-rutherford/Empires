package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.OptionsMenu;

import Framework.Handler;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import java.awt.*;

public class OptionsMenu extends Menu
{
    private MenuButton playersButton;

    public OptionsMenu(Handler h, boolean isEnabled)
    {
        super(h, isEnabled, true);

        //Players Button (it gets properly linked up in the hostlobby menu)
        playersButton = new MenuButton(
                h,
                isEnabled,
                true,
                new Rectangle(400, 200, 50, 50),
                null,
                null,
                new Color(165, 20, 20),
                new Color(1, 1, 1),
                null,
                "Players");
        addButton(playersButton);
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

    public MenuButton getPlayersButton()
    {
        return playersButton;
    }
}
