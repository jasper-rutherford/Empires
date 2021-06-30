package GameStuff.Menus.MultiplayerMenu.HostLobbyMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;

import java.awt.*;

public class CodeBT extends Button
{
    private String code;

    public CodeBT(Handler h, Rectangle space, boolean isActive)
    {
        super(h, space, isActive);

        //generate a code
        code = generateCode();

        //set the color
        setColor(new Color(69, 176, 23));
        setBorderColor(new Color(1, 2, 9));
    }

    public void render(Graphics g)
    {
        super.render(g);

        g.setColor(Color.black);
        g.drawString(code, getSpace().x + 20, getSpace().y + 40);
    }

    //TODO
    public String generateCode()
    {
        return "5";
    }
}
