package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu.PlayersMenu;

import Framework.Handler;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import java.awt.*;

public class PlayersMenu extends Menu
{
    private MenuButton optionsButton;

    public PlayersMenu(Handler h, boolean isEnabled, boolean defaults)
    {
        super(h, isEnabled, defaults);

        //Options Button (it gets properly linked up in the hostlobby menu)
        optionsButton = new MenuButton(
                h,
                isEnabled,
                true,
                new Rectangle(400, 200, 50, 50),
                null,
                null,
                new Color(171, 22, 163),
                new Color(1, 1, 1),
                null,
                "Options");
        addButton(optionsButton);
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

    public MenuButton getOptionsButton()
    {
        return optionsButton;
    }
}
