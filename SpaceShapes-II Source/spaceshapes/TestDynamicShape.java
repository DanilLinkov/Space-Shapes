package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TestDynamicShape {
    /** <IMPORTANT>
     * These tests are performed assuming that if a corner is hit by this shape
     * then the unfilled shape with the default outline color of RGB: [212 212 212]
     * will be painted for all four corners.
     *
     * [212 212 212] is the RGB color that is set in GraphicsPainter therefore this is the
     * color used as the default outline color
     *
     * The general structure of the output should be:
     *
     * Querying old color - finds and saves the old color to set it back to it later
     * Set color to RGB - sets the color to the default white or to another color associated with the dynamic shape
     * Rectangle - Paints a solid or an outlined rectangle
     * Set color to RGB- - sets the color back to the old color
     */

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
    public void testShapePaintingOnSimpleMove() {
        DynamicShape shape = new DynamicShape(10,10,12,15,30,30, Color.blue);
        shape.doPaint(_painter);
        shape.move(500, 500);
        shape.doPaint(_painter);
        assertEquals("" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 10,10,30,30)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 22,25,30,30)" +
                "Set color to RGB: [212:212:212]",_painter.toString());
    }

    /**
     * Test to perform a bounce movement off the right-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffRight() {
        DynamicShape shape = new DynamicShape(100, 20, 12, 15);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 100,20,25,35)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [255:255:255]" +
                "(solid rectangle 110,35,25,35)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [255:255:255]" +
                "(solid rectangle 98,50,25,35)" +
                "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the left-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffLeft() {
        DynamicShape shape = new DynamicShape(10, 20, -12, 15,Color.blue);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 10,20,25,35)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [0:0:255]" +
                "(solid rectangle 0,35,25,35)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [0:0:255]" +
                "(solid rectangle 12,50,25,35)" +
                "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTop() {
        DynamicShape shape = new DynamicShape(50, 10, 3, -15,Color.blue);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 50,10,25,35)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 53,0,25,35)" +
                "Set color to RGB: [212:212:212]" +
                "Querying old color RGB: [212:212:212]" +
                "Set color to RGB: [212:212:212]" +
                "(rectangle 56,15,25,35)" +
                "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottom() {
        DynamicShape shape = new DynamicShape(50, 60, 3, 15,Color.blue);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        shape.move(135, 100);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 50,60,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 53,65,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 56,50,25,35)" +
                        "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom left corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottomAndLeft() {
        DynamicShape shape = new DynamicShape(10, 90, -12, 15,Color.blue);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 10,90,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 0,100,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 12,85,25,35)" +
                        "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom right corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottomAndRight() {
        DynamicShape shape = new DynamicShape(90, 90, 12, 15,Color.blue);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 90,90,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 100,100,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 88,85,25,35)" +
                        "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top left corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTopAndLeft() {
        DynamicShape shape = new DynamicShape(10, 10, -12, -15,Color.blue);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 10,10,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 0,0,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 12,15,25,35)" +
                        "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top right corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTopAndRight() {
        DynamicShape shape = new DynamicShape(90, 10, 12, -15,Color.blue);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 90,10,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 100,0,25,35)" +
                        "Set color to RGB: [212:212:212]" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 88,15,25,35)" +
                        "Set color to RGB: [212:212:212]", _painter.toString());
    }

    /**
     * Test to add text to a shape and make sure that it prints it in the middle.
     * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
     */
    @Test
    public void testShapeWithTextPrinting() {
        DynamicShape shape = new DynamicShape(100, 20, 12, 15,30,30);
        shape.addText("test");
        shape.doPaint(_painter);
        assertEquals("Drawing text: test at x:115 y:35" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 100,20,30,30)" +
                        "Set color to RGB: [212:212:212]",
                _painter.toString());
    }

    /**
     * Test to add text to a shape and make sure the text moves with the shape and stays in the middle.
     * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
     */
    @Test
    public void testShapeWithTextMoving() {
        DynamicShape shape = new DynamicShape(100, 20, 12, 15,30,30);
        shape.addText("test");
        shape.doPaint(_painter);
        shape.move(300,300);
        shape.doPaint(_painter);
        assertEquals("Drawing text: test at x:115 y:35" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 100,20,30,30)" +
                        "Set color to RGB: [212:212:212]" +
                        "Drawing text: test at x:127 y:50" +
                        "Querying old color RGB: [212:212:212]" +
                        "Set color to RGB: [212:212:212]" +
                        "(rectangle 112,35,30,30)" +
                        "Set color to RGB: [212:212:212]",
                _painter.toString());
    }
}
