package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestHexagonShape {

    private MockPainter _painter;

    @Before
    public void setUp() {
        _painter = new MockPainter();
    }

    /**
     * Test to perform a simple (non-bouncing) movement, and to ensure that a small
     * Shape's position after the movement is correct.
     */
    @Test
    public void testSimpleMoveBigHexagon() {
        HexagonShape shape = new HexagonShape(100, 20, 12, 15,100,100);
        shape.doPaint(_painter);
        shape.move(500, 500);
        shape.doPaint(_painter);
        assertEquals("(line 100,70,120,20)(line 120,20,180,20)(line 180,20,200,70)(line 200,70,180,120)" +
                        "(line 180,120,120,120)(line 120,120,100,70)(line 112,85,132,35)(line 132,35,192,35)" +
                        "(line 192,35,212,85)(line 212,85,192,135)(line 192,135,132,135)(line 132,135,112,85)",
                _painter.toString());
    }

    /**
     * Test to perform a simple (non-bouncing) movement, and to ensure that a big
     * Shape's position after the movement is correct.
     */
    @Test
    public void testSimpleMoveSmallHexagon() {
        HexagonShape shape = new HexagonShape(100, 20, 12, 15,30,50);
        shape.doPaint(_painter);
        shape.move(500, 500);
        shape.doPaint(_painter);
        assertEquals("(line 100,45,115,20)(line 115,20,115,20)(line 115,20,130,45)(line 130,45,115,70)" +
                        "(line 115,70,115,70)(line 115,70,100,45)(line 112,60,127,35)(line 127,35,127,35)" +
                        "(line 127,35,142,60)(line 142,60,127,85)(line 127,85,127,85)(line 127,85,112,60)",
                _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the right-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffRight() {
        HexagonShape shape = new HexagonShape(80, 20, 12, 15,50,100);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        assertEquals("(line 80,70,100,20)(line 100,20,110,20)(line 110,20,130,70)(line 130,70,110,120)" +
                "(line 110,120,100,120)(line 100,120,80,70)(line 85,85,105,35)(line 105,35,115,35)" +
                "(line 115,35,135,85)(line 135,85,115,135)(line 115,135,105,135)(line 105,135,85,85)" +
                "(line 73,100,93,50)(line 93,50,103,50)(line 103,50,123,100)(line 123,100,103,150)" +
                "(line 103,150,93,150)(line 93,150,73,100)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the left-most boundary and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffLeft() {
        HexagonShape shape = new HexagonShape(80, 20, -12, 15,50,100);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        shape.move(135, 10000);
        shape.doPaint(_painter);
        assertEquals("(line 80,70,100,20)(line 100,20,110,20)(line 110,20,130,70)(line 130,70,110,120)" +
                "(line 110,120,100,120)(line 100,120,80,70)(line 68,85,88,35)(line 88,35,98,35)" +
                "(line 98,35,118,85)(line 118,85,98,135)(line 98,135,88,135)(line 88,135,68,85)" +
                "(line 56,100,76,50)(line 76,50,86,50)(line 86,50,106,100)(line 106,100,86,150)" +
                "(line 86,150,76,150)(line 76,150,56,100)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom left corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottomAndLeft() {
        HexagonShape shape = new HexagonShape(10, 80, -12, 15,50,50);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals("(line 10,105,30,80)(line 30,80,40,80)(line 40,80,60,105)(line 60,105,40,130)" +
                "(line 40,130,30,130)(line 30,130,10,105)(line 0,110,20,85)(line 20,85,30,85)" +
                "(line 30,85,50,110)(line 50,110,30,135)(line 30,135,20,135)(line 20,135,0,110)" +
                "(line 12,95,32,70)(line 32,70,42,70)(line 42,70,62,95)(line 62,95,42,120)" +
                "(line 42,120,32,120)(line 32,120,12,95)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the bottom right corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffBottomAndRight() {
        HexagonShape shape = new HexagonShape(70, 80, 12, 15,50,50);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals("(line 70,105,90,80)(line 90,80,100,80)(line 100,80,120,105)(line 120,105,100,130)" +
                "(line 100,130,90,130)(line 90,130,70,105)(line 75,110,95,85)(line 95,85,105,85)" +
                "(line 105,85,125,110)(line 125,110,105,135)(line 105,135,95,135)(line 95,135,75,110)" +
                "(line 63,95,83,70)(line 83,70,93,70)(line 93,70,113,95)(line 113,95,93,120)" +
                "(line 93,120,83,120)(line 83,120,63,95)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top left corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTopAndLeft() {
        HexagonShape shape = new HexagonShape(10, 10, -12, -15);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "(line 10,27,22,10)(line 22,10,22,10)(line 22,10,35,27)(line 35,27,22,45)" +
                        "(line 22,45,22,45)(line 22,45,10,27)(line 0,17,12,0)(line 12,0,12,0)" +
                        "(line 12,0,25,17)(line 25,17,12,35)(line 12,35,12,35)(line 12,35,0,17)" +
                        "(line 12,32,24,15)(line 24,15,24,15)(line 24,15,37,32)(line 37,32,24,50)" +
                        "(line 24,50,24,50)(line 24,50,12,32)", _painter.toString());
    }

    /**
     * Test to perform a bounce movement off the top right corner and to
     * ensure that the Shape's position after the movement is correct.
     */
    @Test
    public void testShapeMoveWithBounceOffTopAndRight() {
        HexagonShape shape = new HexagonShape(90, 10, 12, -15);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        shape.move(125, 135);
        shape.doPaint(_painter);
        assertEquals(
                "(line 90,27,102,10)(line 102,10,102,10)(line 102,10,115,27)(line 115,27,102,45)" +
                        "(line 102,45,102,45)(line 102,45,90,27)(line 100,17,112,0)(line 112,0,112,0)" +
                        "(line 112,0,125,17)(line 125,17,112,35)(line 112,35,112,35)(line 112,35,100,17)" +
                        "(line 88,32,100,15)(line 100,15,100,15)(line 100,15,113,32)(line 113,32,100,50)" +
                        "(line 100,50,100,50)(line 100,50,88,32)", _painter.toString());
    }

    /**
     * Test to add text to a shape and make sure that it prints it in the middle.
     * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
     */
    @Test
    public void testShapeWithTextPrinting() {
        HexagonShape shape = new HexagonShape(100, 20, 12, 15,30,30);
        shape.addText("test");
        shape.doPaint(_painter);
        assertEquals("Drawing text: test at x:115 y:35" +
                        "(line 100,35,115,20)(line 115,20,115,20)(line 115,20,130,35)(line 130,35,115,50)" +
                        "(line 115,50,115,50)(line 115,50,100,35)",
                _painter.toString());
    }

    /**
     * Test to add text to a shape and make sure the text moves with the shape and stays in the middle.
     * This is done through the use of paintTechnique method which uses the template pattern (see more in the Shape class)
     */
    @Test
    public void testShapeWithTextMoving() {
        HexagonShape shape = new HexagonShape(100, 20, 12, 15,30,30);
        shape.addText("test");
        shape.doPaint(_painter);
        shape.move(300,300);
        shape.doPaint(_painter);
        assertEquals("Drawing text: test at x:115 y:35" +
                        "(line 100,35,115,20)(line 115,20,115,20)(line 115,20,130,35)(line 130,35,115,50)" +
                        "(line 115,50,115,50)(line 115,50,100,35)" +
                        "Drawing text: test at x:127 y:50" +
                        "(line 112,50,127,35)(line 127,35,127,35)(line 127,35,142,50)(line 142,50,127,65)" +
                        "(line 127,65,127,65)(line 127,65,112,50)",
                _painter.toString());
    }

}
