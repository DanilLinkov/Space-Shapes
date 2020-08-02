package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and RectangleShape.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class TestShape {
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}

	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.doPaint(_painter);
		shape.move(500, 500);
		shape.doPaint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)", 
				_painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.doPaint(_painter);
		shape.move(135, 10000);
		shape.doPaint(_painter);
		shape.move(135, 10000);
		shape.doPaint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 110,35,25,35)"
				+ "(rectangle 98,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		RectangleShape shape = new RectangleShape(10, 20, -12, 15);
		shape.doPaint(_painter);
		shape.move(10000, 10000);
		shape.doPaint(_painter);
		shape.move(10000, 10000);
		shape.doPaint(_painter);
		assertEquals("(rectangle 10,20,25,35)(rectangle 0,35,25,35)"
				+ "(rectangle 12,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom left corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeft() {
		RectangleShape shape = new RectangleShape(10, 90, -12, 15);
		shape.doPaint(_painter);
		shape.move(125, 135);
		shape.doPaint(_painter);
		shape.move(125, 135);
		shape.doPaint(_painter);
		assertEquals("(rectangle 10,90,25,35)(rectangle 0,100,25,35)"
				+ "(rectangle 12,85,25,35)", _painter.toString());
	}

	/**
	 * Test to add text to a shape and make sure that it prints it in the middle.
	 * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
	 */
	@Test
	public void testShapeWithTextPrinting() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15,30,30);
		shape.addText("test");
		shape.doPaint(_painter);
		assertEquals("Drawing text: test at x:115 y:35" +
						"(rectangle 100,20,30,30)",
				_painter.toString());
	}

	/**
	 * Test to add text to a shape and make sure the text moves with the shape and stays in the middle.
	 * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
	 */
	@Test
	public void testShapeWithTextMoving() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15,30,30);
		shape.addText("test");
		shape.doPaint(_painter);
		shape.move(300,300);
		shape.doPaint(_painter);
		assertEquals("Drawing text: test at x:115 y:35" +
						"(rectangle 100,20,30,30)" +
						"Drawing text: test at x:127 y:50" +
						"(rectangle 112,35,30,30)",
				_painter.toString());
	}
}
