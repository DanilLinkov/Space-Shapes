package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestOvalShape {

    private MockPainter _painter;

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
        OvalShape shape = new OvalShape(100, 20, 12, 15);
        shape.doPaint(_painter);
        shape.move(500, 500);
        shape.doPaint(_painter);
        assertEquals("(oval 100,20,25,35)(oval 112,35,25,35)",
                _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the right-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffRight() {
        OvalShape shape = new OvalShape(100, 20, 12, 15);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        assertEquals("(oval 100,20,25,35)(oval 110,35,25,35)"
                + "(oval 98,50,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the left-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffLeft() {
        OvalShape shape = new OvalShape(10, 20, -12, 15);
        shape.doPaint(_painter);
        shape.move(10000, 10000);
        shape.doPaint(_painter);
        shape.move(10000, 10000);
        shape.doPaint(_painter);
        assertEquals("(oval 10,20,25,35)(oval 0,35,25,35)"
                + "(oval 12,50,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTop() {
        OvalShape shape = new OvalShape(50, 10, 3, -15);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        assertEquals(
                "(oval 50,10,25,35)(oval 53,0,25,35)(oval 56,15,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottom() {
        OvalShape shape = new OvalShape(50, 60, 3, 15);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        assertEquals(
                "(oval 50,60,25,35)(oval 53,65,25,35)(oval 56,50,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom left corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottomAndLeft() {
        OvalShape shape = new OvalShape(10, 90, -12, 15);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals("(oval 10,90,25,35)(oval 0,100,25,35)"
                + "(oval 12,85,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom right corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottomAndRight() {
        OvalShape shape = new OvalShape(90, 90, 12, 15);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals("(oval 90,90,25,35)(oval 100,100,25,35)"
                + "(oval 88,85,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top left corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTopAndLeft() {
        OvalShape shape = new OvalShape(10, 10, -12, -15);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "(oval 10,10,25,35)(oval 0,0,25,35)(oval 12,15,25,35)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top right corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTopAndRight() {
        OvalShape shape = new OvalShape(90, 10, 12, -15);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "(oval 90,10,25,35)(oval 100,0,25,35)(oval 88,15,25,35)", _painter.toString());
    }

    /**
     * Test to add text to a shape and make sure that it prints it in the middle.
     * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
     */
    @Test
    public void testShapeWithTextPrinting() {
        OvalShape shape = new OvalShape(100, 20, 12, 15,30,30);
        shape.addText("test");
        shape.doPaint(_painter);
        assertEquals("Drawing text: test at x:115 y:35(oval 100,20,30,30)",
                _painter.toString());
    }

    /**
     * Test to add text to a shape and make sure the text moves with the shape and stays in the middle.
     * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
     */
    @Test
    public void testShapeWithTextMoving() {
        OvalShape shape = new OvalShape(100, 20, 12, 15,30,30);
        shape.addText("test");
        shape.doPaint(_painter);
        shape.move(300,300);
        shape.doPaint(_painter);
        assertEquals("Drawing text: test at x:115 y:35(oval 100,20,30,30)Drawing text: test at x:127 y:50(oval 112,35,30,30)",
                _painter.toString());
    }
}
