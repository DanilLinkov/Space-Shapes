package spaceshapes;

import java.awt.*;

public class DynamicShape extends Shape{
    private Color _color = Color.white;

    /**
     * Default constructor that creates a DynamicShape instance whose instance
     * variables are set to default values.
     */
    public DynamicShape() {
        super();
    }

    /**
     * Creates a DynamicShape instance with specified values for instance
     * variables.
     * @param x x position.
     * @param y y position.
     * @param deltaX speed and direction for horizontal axis.
     * @param deltaY speed and direction for vertical axis.
     */
    public DynamicShape(int x, int y, int deltaX, int deltaY) {
        super(x,y,deltaX,deltaY);
    }

    /**
     * Creates a DynamicShape instance with specified values for instance
     * variables.
     * @param x x position.
     * @param y y position.
     * @param deltaX speed and direction for horizontal axis.
     * @param deltaY speed and direction for vertical axis.
     * @param color the color of the object up on hitting the right or the left wall
     */
    public DynamicShape(int x, int y, int deltaX, int deltaY,Color color) {
        super(x,y,deltaX,deltaY);
        _color = color;
    }

    public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height,String text)
    {
        super(x,y,deltaX,deltaY,width,height,text);
    }

    public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height,String text,Color color)
    {
        super(x,y,deltaX,deltaY,width,height,text);
        _color = color;
    }

    /**
     * Creates an OvalShape instance with specified values for instance
     * variables.
     * @param x x position.
     * @param y y position.
     * @param deltaX speed (pixels per move call) and direction for horizontal
     *        axis.
     * @param deltaY speed (pixels per move call) and direction for vertical
     *        axis.
     * @param width width in pixels.
     * @param height height in pixels.
     */
    public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x,y,deltaX,deltaY,width,height);
    }

    /**
     * Creates a DynamicShape instance with specified values for instance
     * variables.
     * @param x x position.
     * @param y y position.
     * @param deltaX speed (pixels per move call) and direction for horizontal
     *        axis.
     * @param deltaY speed (pixels per move call) and direction for vertical
     *        axis.
     * @param width width in pixels.
     * @param height height in pixels.
     * @param color the color of the object up on hitting the right or the left wall
     */
    public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height,Color color) {
        super(x,y,deltaX,deltaY,width,height);
        _color = color;
    }

    @Override
    public void paint(Painter painter) {
        // Saving the current color to then set it back so that other shapes are not colored
        Color oldColor = painter.getColor();
        if (lastHitLeft() || lastHitRight())
        {
            // If the left or the right wall was the last wall hit then set the color of the shape
            // to the given color and paint it as a solid shape
            painter.setColor(_color);
            painter.fillRect(_x,_y,_width,_height);

        }
        else
        {
            // If the top or the bottom wall was the last wall hit then set the outline to the default
            // outline and paint the shape with just an outline
            painter.setColor(new Color(212, 212, 212));
            painter.drawRect(_x,_y,_width,_height);
        }

        // Set the color back to the original
        painter.setColor(oldColor);
    }
}
