package spaceshapes;

import java.awt.*;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class MockPainter implements Painter {

	// Internal log.
	private StringBuffer _log = new StringBuffer();

	private Color color = new Color(212, 212, 212);

	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
	}
	
	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}

	/**
	 * Draws a solid rectangle of the painters color
	 */
	public void fillRect(int x, int y, int width, int height) {
		_log.append("(solid rectangle " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Gives the current color of the painter
	 */
	public Color getColor()
	{
		_log.append("Querying old color RGB: " + "[" + color.getRed() + ":" + color.getGreen() + ":" + color.getBlue() + "]");
		return color;
	}

	/**
	 * Sets a new color for the painter
	 */
	public void setColor(Color color) {
		_log.append("Set color to " + "RGB: " + "[" + color.getRed() + ":" + color.getGreen() + ":" + color.getBlue() + "]");
	}

	/**
	 * Translates the origin of the canvas
	 */
	public void translate(int x, int y) {

	}

	/**
	 * Draws text in the middle of the shape
	 */
	public void drawCentredText(int x,int y,int width, int height,String text){
		_log.append("Drawing text: " + text + " at x:" + (x+width/2) + " y:" + (y+height/2));
	}

	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {

	}
}