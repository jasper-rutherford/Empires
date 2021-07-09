package GameStuff.Menus.Buttons;

import Framework.Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//The generic Button
public class Button
{
    //the handler
    private Handler h;

    //the space of the button
    private Rectangle space;
    private int x;
    private int y;
    private int width;
    private int height;

    //whether the button is active
    private boolean enabled;

    //color stuff
    private Color color;
    private boolean hasColor;

    //bordercolor stuff
    private Color borderColor;
    private boolean hasBorderColor;

    //image stuff
    private BufferedImage image;
    private boolean hasImage;

    //text stuff
    private String text;
    private boolean hasText;

    //Whether or not the button should be enabled when its menu is enabled
    private boolean defaults;

    public Button(Handler h, boolean enabled, boolean defaults, Rectangle rect)
    {
        this.h = h;
        setSpace(rect);
        this.enabled = enabled;

        //default to no color/bordercolor/image
        hasColor = false;
        hasBorderColor = false;
        hasImage = false;

        //defaults is set to true by default (duh)
        this.defaults = defaults;
    }

    public void enable()
    {
        enabled = true;
    }

    public void disable()
    {
        enabled = false;
    }

    public boolean contains(Point p)
    {
        return space.contains(p);
    }

    public void render(Graphics g)
    {
        //fill with color if a color is assigned
        if (hasColor)
        {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

        //draw a border if a color is assigned
        if (hasBorderColor)
        {
            g.setColor(borderColor);
            g.drawRect(x, y, width, height);
        }

        //draw an image if one is assigned
        if (hasImage)
        {
            g.drawImage(image, x, y, width, height, null);
        }

        //draw text if text has been assigned
        if (hasText)
        {
            g.setColor(Color.black);
            g.drawString(text, space.x + space.width / 10, space.y + space.height / 2);
        }
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * override this
     */
    public void activate()
    {

    }

    public Rectangle getSpace()
    {
        return space;
    }

    public void setColor(Color color)
    {
        this.color = color;
        hasColor = true;
    }

    public void setBorderColor(Color color)
    {
        borderColor = color;
        hasBorderColor = true;
    }

    public void setImage(String path)
    {
        //read in texture
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
            hasImage = true;
        }
        catch (IOException e)
        {
            h.println("vvv Button failed to load image: [" + path + "] vvv");
            e.printStackTrace();
            h.println("^^^ Button failed to load image: [" + path + "] ^^^");
        }

    }

    public void setSpace(Rectangle rect)
    {
        space = rect;
        x = rect.x;
        y = rect.y;
        width = rect.width;
        height = rect.height;
    }

    public void setText(String text)
    {
        this.text = text;
        hasText = true;
    }

    public Handler getHandler()
    {
        return h;
    }

    //gets the value of defaults
    public boolean isDefault()
    {
        return  defaults;
    }
}
