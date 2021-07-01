package GameStuff.Menus;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;

import java.awt.*;

/**
 * Buttons that simply direct between menus are menu buttons
 */
public class MenuButton extends Button
{
    private Menu fromMenu;
    private Menu toMenu;

    public MenuButton(Handler h, Rectangle space, boolean isActive, Menu fromMenu, Menu toMenu, Color color, Color borderColor, String imagePath, String text)
    {
        super(h, space, isActive);
        this.fromMenu = fromMenu;
        this.toMenu = toMenu;

        if (color != null)
        {
            setColor(color);
        }
        if (borderColor != null)
        {
            setBorderColor(borderColor);
        }
        if (imagePath != null)
        {
            setImage(imagePath);
        }
        if (text != null)
        {
            setText(text);
        }
    }

    public void activate()
    {
        //disable the from menu
        fromMenu.disable();

        //enable the to menu
        toMenu.enable();
    }
}