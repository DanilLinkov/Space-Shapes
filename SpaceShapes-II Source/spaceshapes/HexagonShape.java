package spaceshapes;

public class HexagonShape extends Shape{
    /**
     * Default constructor that creates a HexagonShape instance whose instance
     * variables are set to default values.
     */
    public HexagonShape() {
        super();
    }

    /**
     * Creates a HexagonShape instance with specified values for instance
     * variables.
     * @param x x position.
     * @param y y position.
     * @param deltaX speed and direction for horizontal axis.
     * @param deltaY speed and direction for vertical axis.
     */
    public HexagonShape(int x, int y, int deltaX, int deltaY) {
        super(x,y,deltaX,deltaY);
    }

    /**
     * Creates a HexagonShape instance with specified values for instance
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
    public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x,y,deltaX,deltaY,width,height);
    }

    public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height,String text)
    {
        super(x,y,deltaX,deltaY,width,height,text);
    }

    /**
     * Paints this HexagonShape object using the supplied Painter object.
     */
    @Override
    public void paint(Painter painter) {
        // Creating a Point for all 6 edges in the hexagon
        Point mostLeft = new Point(_x,_y+_height/2);
        Point topLeft = new Point(_x+20,_y);
        Point topRight = new Point(_x+_width-20,_y);
        Point mostRight = new Point(_x+_width,_y+_height/2);
        Point bottomRight = new Point(_x+_width-20,_y+_height);
        Point bottomLeft = new Point(_x+20,_y+_height);

        // If the width of the hexagon is less then 40 pixels then the hexagon is a 4 sided
        // figure therefore top left = top right and bottom left = bottom right
        if(_width<40)
        {
            topLeft = new Point(_x+_width/2,_y);
            topRight = topLeft;

            bottomLeft = new Point(_x+_width/2,_y+_height);
            bottomRight = bottomLeft;
        }

        // Drawing the hexagon starting from the most left edge going clock wise
        painter.drawLine(mostLeft.get_x(), mostLeft.get_y(), topLeft.get_x(), topLeft.get_y());
        painter.drawLine(topLeft.get_x(), topLeft.get_y(), topRight.get_x(), topRight.get_y());
        painter.drawLine(topRight.get_x(), topRight.get_y(), mostRight.get_x(), mostRight.get_y());
        painter.drawLine(mostRight.get_x(), mostRight.get_y(), bottomRight.get_x(), bottomRight.get_y());
        painter.drawLine(bottomRight.get_x(), bottomRight.get_y(), bottomLeft.get_x(), bottomLeft.get_y());
        painter.drawLine(bottomLeft.get_x(), bottomLeft.get_y(), mostLeft.get_x(), mostLeft.get_y());
    }
}
