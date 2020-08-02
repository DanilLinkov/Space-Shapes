package spaceshapes;

import java.awt.*;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 *
 * @author Paramvir Singh (Original Author - Ian Warren)
 *
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * see spaeshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Draws a solid rectangle of the painters color
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x,y,width,height);
	}

	/**
	 * Gives the current color of the painter
	 */
	public Color getColor() {
		return _g.getColor();
	}

	/**
	 * Sets a new color for the painter
	 */
	public void setColor(Color color) {
		_g.setColor(color);
	}

	/**
	 * Translates the origin of the canvas
	 */
	public void translate(int x, int y) {
		_g.translate(x,y);
	}

	/**
	 * Draws text in the middle of the shape
	 */
	public void drawCentredText(int x,int y,int width, int height,String text) {
		// Finding the required metrics for the centre calculation
		int fontWidth = _g.getFontMetrics().stringWidth(text);
		int ascent = _g.getFontMetrics().getAscent();
		int descent = _g.getFontMetrics().getDescent();

		// Centering the x and y points depending on the font metrics
		y = y+height/2;
		x = x+width/2 - fontWidth/2;

		if (ascent>descent)
		{
			y+=(ascent-descent)/2;
		}
		else if (descent>ascent)
		{
			y-=(ascent-descent)/2;
		}

		// Drawing the text at the centre point
		_g.drawString(text,x,y);
	}

	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img,x,y,width,height,null);
	}
}
