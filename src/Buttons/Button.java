package Buttons;

import Framework.Handler;

import java.awt.*;

public class Button
{
    private Handler h;

    private Polygon space;

    private boolean enabled;

    private Color color;

    public Button(Handler h, Polygon space, boolean enabled, Color color)
    {
        this.h = h;

        this.space = space;

        this.enabled = enabled;

        this.color = color;
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
        g.setColor(color);
        g.fillPolygon(space);

        g.setColor(Color.black);
        g.drawPolygon(space);
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
}
