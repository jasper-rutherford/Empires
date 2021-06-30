package GameStuff.Menus.MainMenu;

import GameStuff.Menus.Buttons.Button;
import Framework.Handler;
import GameStuff.Menus.Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends Menu
{
    //handler
    private Handler h;

    private boolean hasLogo;

    private BufferedImage logo;

    private Rectangle logoSpace;

    public MainMenu(Handler h, boolean isActive)
    {
        super(h, isActive);

        this.h = h;

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

        //create/add the four buttons
        Rectangle single = new Rectangle(x, height + 2 * y, bWidth, bHeight);
        Button singleplayerBT = new SingleplayerBT(h, single, isActive, this);
        addButton(singleplayerBT);

        Rectangle multi = new Rectangle(x + (y + width) / 2, height + 2 * y, bWidth, bHeight);
        Button multiplayerBT = new MultiplayerBT(h, multi, isActive, this);
        addButton(multiplayerBT);

        Rectangle settings = new Rectangle(x, 3 * height / 2 + 3 * y, bWidth, bHeight);
        Button settingsBT = new SettingsBT(h, settings, isActive);
        addButton(settingsBT);

        Rectangle exit = new Rectangle(x + (width + y) / 2, 3 * height / 2 + 3 * y, bWidth, bHeight);
        Button exitBT = new ExitBT(h, exit, isActive);
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
