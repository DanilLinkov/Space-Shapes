package spaceshapes;

import java.util.ArrayList;
import java.util.List;

public class CarrierShape extends Shape{

    /** <IMPORTANT>
     * This list is used to create a two-way connection between the children and this CarrierShape
     * this shows use of general hierarchy pattern (composite pattern)
     */
    List<Shape> children = new ArrayList<>();

    /**
     * Creates a CarrierShape object with default values for state.
     */
    public CarrierShape()
    {
        super();
    }

    /**
     * Creates a CarrierShape object with specified location values,
     * default values for other state items.
     */
    public CarrierShape(int x,int y)
    {
        super(x,y);
    }

    /**
     * Creates a CarrierShape with specified values for location, velocity and direction.
     * Non-specified state items take on default values.
     */
    public CarrierShape(int x,int y, int deltaX, int deltaY)
    {
        super(x,y,deltaX,deltaY);
    }

    /**
     * Creates a CarrierShape with specified values for location, velocity,
     * direction, width and height.
     */
    public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height)
    {
        super(x,y,deltaX,deltaY,width,height);
    }

    public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height,String text)
    {
        super(x,y,deltaX,deltaY,width,height,text);
    }

    /**
     * Moves a CarrierShape object (including its children) within the bounds
     * specified by arguments width and height
     */
    @Override
    public void move(int width, int height)
    {
        // First move the CarrierShape itself then its children
        super.move(width,height);

        // Moving each child of the CarrierShape
        for(Shape child : children)
        {
            child.move(_width,_height);
        }
    }

    /**
     * Paints a CarrierShape object by drawing a rectangle around the edge of
     * its bounding box. The carrierShape object's children are then painted
     */
    @Override
    public void paint(Painter painter)
    {
        // Drawing the outline of the CarrierShape
        painter.drawRect(_x,_y,_width,_height);
        // Translating the origin to x and y point to the top left corner
        // of the CarrierShape to be able to paint its children relative to that point
        painter.translate(_x,_y);

        // Painting each child relative to the top left corner of the CarrierShape
        for(Shape child : children)
        {
            child.doPaint(painter);
        }

        // Translating the origin back to x = 0 y = 0
        painter.translate(-_x,-_y);
    }

    /**
     * Attempts to add a shape to a CarrierShape object. if successful, a
     * two-way link is established between the CarrierShape and the newly
     * added Shape.
     */
    void add(Shape shape) throws IllegalArgumentException
    {
        // Checking if the shape is already part of the children list or if the
        // shape already has a parent
        if(contains(shape) || shape.parent()!=null)
        {
            // Then throw an error
            throw new IllegalArgumentException();
        }

        // If the shape to add is outside of the CarrierShape bounds
        // then throw an error
        if(shape._width+shape._x>_width || shape._height+shape._x>_height)
        {
            throw new IllegalArgumentException();
        }

        // Otherwise the shape is added to the children list
        children.add(shape);

        // The parent of the shape is set to this CarrierShape
        // to establish a two-way connection
        shape.setParent(this);
    }

    /**
     * Removes a particular Shape from a CarrierShape instance. Once removed,
     * the two-way link between the CarrierShape and its former child is destroyed.
     */
    void remove(Shape shape)
    {
        // If the children list contains the shape
        // then remove it and set its parent to null
        // breaking the two-way connection
        if(contains(shape))
        {
            children.remove(shape);
            shape.setParent(null);
        }
    }

    /**
     * Returns the Shape at a specified position within a CarrierShape. If
     * the position specified is less than zero or greater than the number of
     * children stored in the CarrierShape less one this method throws an IndexOutOfBoundsException.
     */
    public Shape shapeAt(int index) throws IndexOutOfBoundsException
    {
        // Return the child at index
        return children.get(index);
    }

    /**
     * Returns the number of children contained within a CarrierShape object.
     */
    public int shapeCount()
    {
        // return the number of children in the list
        return children.size();
    }

    /**
     * Returns the index of a specified child within a CarrierShape object.
     * If the Shape specified is not actually a child of the CarrierShape
     * this method returns -1.
     */
    public int indexOf(Shape shape)
    {
        // returns the index of the child
        return children.indexOf(shape);
    }

    /**
     * Returns true if the Shape argument is a child of the CarrierShape object
     * on which this method is called, false otherwise.
     */
    public boolean contains(Shape shape)
    {
        // checks if the child is in the children list
        return children.contains(shape);
    }

}
