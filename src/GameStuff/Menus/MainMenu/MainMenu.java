package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.MainMenu.MultiplayerMenu.MultiplayerMenu;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends Menu
{
    private boolean hasLogo;

    private BufferedImage logo;

    private Rectangle logoSpace;

    public MainMenu(Handler h, boolean isEnabled)
    {
        super(h, isEnabled, true);

        //create logoSpace
        int width = h.getScreenWidth() * 3 / 5;
        int height = width / 4;

        int x = h.getScreenWidth() / 5;
        int y = height / 3;

        logoSpace = new Rectangle(x, y, width, height);

        //read in logo
        try
        {
            logo = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
            hasLogo = true;
        }
        catch (IOException e)
        {
            h.println("vvv Failed to load Logo vvv");
            e.printStackTrace();
            h.println("^^^ Failed to load Logo ^^^");

            hasLogo = false;
        }

        //create useful values for calculating button spaces
        x = h.getScreenWidth() / 5;
        int bHeight = height / 2;
        int bWidth = (width - y) / 2;

        //*****create/add the four buttons*******//

        //Singleplayer Button TODO
        Rectangle single = new Rectangle(x, height + 2 * y, bWidth, bHeight);
        Button singleplayerBT = new SingleplayerBT(h, single, isEnabled, this);
        addButton(singleplayerBT);


        //Multiplayer Button
        MenuButton.create(
                h,
                isEnabled,
                true,
                new Rectangle(x + (y + width) / 2, height + 2 * y, bWidth, bHeight),
                this,
                new MultiplayerMenu(h, false, this),
                new Color(23, 143, 176),
                new Color(8, 49, 174),
                "/menus/mainmenu/multiplayer.png",
                null);

        //Settings Button TODO
        Rectangle settings = new Rectangle(x, 3 * height / 2 + 3 * y, bWidth, bHeight);
        Button settingsBT = new SettingsBT(h, settings, isEnabled);
        addButton(settingsBT);

        //Exit Button
        Rectangle exit = new Rectangle(x + (width + y) / 2, 3 * height / 2 + 3 * y, bWidth, bHeight);
        Button exitBT = new ExitBT(h, exit, isEnabled);
        addButton(exitBT);
    }

    public void render(Graphics g)
    {
        //draw logo
        g.drawImage(logo, logoSpace.x, logoSpace.y, logoSpace.width, logoSpace.height, null);

        //render the buttons
        super.render(g);
    }
}
