package spaceshapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public abstract class Shape {
	/** <IMPORTANT>
	 * Template pattern used in the abstract paintTechnique method which is overridden in the
	 * child classes and paint is called which performs some text displaying logic and then calls
	 * the paintTechnique method to paint the shape
	 */


	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	
	protected static final int DEFAULT_Y_POS = 0;
	
	protected static final int DEFAULT_DELTA_X = 5;
	
	protected static final int DEFAULT_DELTA_Y = 5;
	
	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;

	// Boolean array used to find the last border hit by the shape
	private boolean[] lastBorderHit = new boolean[4];

	// Reference to the parent CarrierShape if one exists
	private CarrierShape parent;

	// Text if text is associated with the shape
	private String text;
	// ===

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
	}

	/**
	 * Creates a Shape instance with specified x,y,deltaX,deltaY,width,height and text values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height,String text) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		this.text = text;
	}

	/**
	 * Returns an ordered list of Shape objects. The first item within
	 * the list is the root CarrierShape of the containment hierarchy.
	 * The last item within the list is the callee object. Any
	 * intermediate items are CarrierShapes that connect the root CarrierShape
	 * to the callee Shape.
	 */
	public List<Shape> path()
	{
		// Output path list
		List<Shape> path = new ArrayList<>();

		// Add the callee to the list
		path.add(this);

		// Go up the full hierarchy and add the shapes to the
		// path
		Shape currentShape = parent;

		while(currentShape!=null)
		{
			path.add(currentShape);
			currentShape = currentShape.parent;
		}

		// Reverse the list to put it into the required order
		Collections.reverse(path);

		return path;
	}
	
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;

			// Set the left wall as the last wall hit
			setLastBorderHit(0);
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;

			// Set the right wall as the last wall hit
			setLastBorderHit(2);
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;

			// Set the top wall as the last wall hit
			setLastBorderHit(1);
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;

			// Set the bottom wall as the last wall hit
			setLastBorderHit(3);
		}

		_x = nextX;
		_y = nextY;
	}

	/**
	 * Sets the index of the wall hit in the lastBorderHit array
	 * to true and the rest to false in order to keep track of the last
	 * wall hit
	 * @param borderHit the index of the last boarder hit e.g. left = 0 top = 1
	 * right = 2 and bottom = 3
	 */
	private void setLastBorderHit(int borderHit)
	{
		// Set every border to false
		for(int i = 0;i<4;i++)
		{
			lastBorderHit[i] = false;
		}

		// Set the borderHit index to true
		lastBorderHit[borderHit] = true;
	}

	/**
	 * These methods return true if the respective wall was
	 * the last wall to be hit by the shape and false if it
	 * was not
	 */
	public boolean lastHitLeft()
	{
		return lastBorderHit[0];
	}

	public boolean lastHitTop()
	{
		return lastBorderHit[1];
	}

	public boolean lastHitRight()
	{
		return lastBorderHit[2];
	}

	public boolean lastHitBot()
	{
		return lastBorderHit[3];
	}

	/**
	 * Returns the text of the shape
	 */
	public String getText() {
		return text;
	}

	/**
	 * Adds text to the shape
	 */
	public void addText(String text) {
		this.text = text;
	}

	/**
	 * Sets the CarrierShape parent for this shape
	 */
	public void setParent(CarrierShape parent)
	{
		this.parent = parent;
	}

	/**
	 * Returns the current CarrierShape parent of the shape
	 */
	public CarrierShape parent()
	{
		return parent;
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * @param painter the Painter object used for drawing.
	 */
	protected abstract void paint(Painter painter);

	/**
	 * Checks if there is any text associated with the shape object
	 * and draws it in the middle of the shape. This makes use of the template
	 * pattern in order to not have the developer implement this themselves for every shape
	 * @param painter the Painter object used for drawing.
	 */
	public void doPaint(Painter painter)
	{
		// Checks if there is text associated with the Shape
		if(text!=null)
		{
			// If there is then it paints the text in the middle of the shape
			painter.drawCentredText(_x,_y,_width,_height,text);
		}

		paint(painter);
	}

	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}
	
	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}
	
	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}
	
	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}
	
	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a RectangleShape instance, this method 
	 * will return "spaceshapes.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}
}
