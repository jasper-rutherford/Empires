package GameStuff.Menus.MultiplayerMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;

import java.awt.*;

public class JoinLobbyBT extends Button
{

    public JoinLobbyBT(Handler h, Rectangle space, boolean enabled)
    {
        super(h, space, enabled);

        //set the color
        setColor(new Color(23, 143, 176));
        setBorderColor(new Color(0, 0, 0));
    }

    public void render(Graphics g)
    {
        super.render(g);

        g.setColor(Color.black);
        g.drawString("Join", getSpace().x + 20, getSpace().y + 40);
    }
}
