package Buttons;

import Framework.Handler;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private boolean hasColor;
    private Color color;

    //bordercolor stuff
    private boolean hasBorderColor;
    private Color borderColor;

    //image stuff
    private boolean hasImage;
    private BufferedImage image;

    public Button(Handler h, Rectangle rect, boolean enabled)
    {
        this.h = h;
        setSpace(rect);
        this.enabled = enabled;

        //default to no color/bordercolor/image
        hasColor = false;
        hasBorderColor = false;
        hasImage = false;
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
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
        }

        //draw an image if one is assigned
        if (hasImage)
        {
            g.drawImage(image, x, y, width, height, null);
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

    public void setImage(BufferedImage image)
    {
        this.image = image;
        hasImage = true;
    }

    public void setSpace(Rectangle rect)
    {
        space = rect;
        x = rect.x;
        y = rect.y;
        width = rect.width;
        height = rect.height;
    }
}
