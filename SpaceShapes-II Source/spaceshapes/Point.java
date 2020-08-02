package spaceshapes;

public class Point {
    /**
     * This class is used to represent a point on the canvas with
     * co-ordinates of x and y
     */
    private int _x;
    private int _y;

    public Point(int x, int y)
    {
        _x = x;
        _y = y;
    }

    public int get_x()
    {
        return _x;
    }

    public int get_y()
    {
        return _y;
    }
}
