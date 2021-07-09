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

    public MenuButton(Handler h, boolean isActive, boolean defaults, Rectangle space, Menu fromMenu, Menu toMenu, Color color, Color borderColor, String imagePath, String text)
    {
        super(h, isActive, defaults, space);
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
        //disable the from menu and this button
        fromMenu.disable();
        this.disable();

        //enable the to menu
        toMenu.enable();
    }

    //creates a menu button and adds it to its from menu
    public static void create(Handler h, boolean isEnabled, boolean defaults, Rectangle space, Menu fromMenu, Menu toMenu, Color color, Color borderColor, String imagePath, String text)
    {
        fromMenu.addButton(new MenuButton(h, isEnabled, defaults, space, fromMenu, toMenu, color, borderColor, imagePath, text));
    }

    public void setFromMenu(Menu from)
    {
        fromMenu = from;
    }

    public void setToMenu(Menu to)
    {
        toMenu = to;
    }
}