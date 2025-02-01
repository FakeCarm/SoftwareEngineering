package javafxapplication1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Classe per i test di ShapeEditor.
 */
public class ShapeEditorTest {

    private ShapeEditorImpl shapeEditor;
    private Shape shape;
    private double testX;
    private double testY;
    private double testOffsetX;
    private double testOffsetY;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        shape = new Rectangle(100, 50, Color.RED); // Rettangolo per test
        testX = 2;
        testY = 2;
        testOffsetX = 5;
        testOffsetY = 10;
        shapeEditor = new ShapeEditorImpl(shape,testX,testY);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testGetStartX() {
        assertEquals(testX, shapeEditor.getStartX(), 0.01);
    }

    @Test
    public void testGetStartY() {
        assertEquals(testY, shapeEditor.getStartY(), 0.01);
    }

    @Test
    public void testSetStartX() {
        shapeEditor.setStartX(15.0);
        assertEquals(15.0, shapeEditor.getStartX(), 0.01);
    }

    @Test
    public void testSetStartY() {
        shapeEditor.setStartY(25.0);
        assertEquals(25.0, shapeEditor.getStartY(), 0.01);
    }

    @Test
    public void testGetShape() {
        assertEquals(shape, shapeEditor.getShape());
    }

    @Test
    public void testSetShape() {
        Shape newShape = new Rectangle(30, 30, Color.BLUE);
        shapeEditor.setShape(newShape);
        assertEquals(newShape, shapeEditor.getShape());
    }

    @Test
    public void testGetOffsetX() {
        shapeEditor.setOffsetX(testOffsetX); // Imposta il valore corretto prima di testare
        assertEquals(testOffsetX, shapeEditor.getOffsetX(), 0.01);
    }

    @Test
    public void testGetOffsetY() {
        shapeEditor.setOffsetY(testOffsetY); // Imposta il valore corretto prima di testare
        assertEquals(testOffsetY, shapeEditor.getOffsetY(), 0.01);
    }

    
    
    @Test
    public void testChangeStrokeColor() {
        shapeEditor.changeStrokeColor(Color.BLUE);
        assertEquals(Color.BLUE, shape.getStroke());
    }

    @Test
    public void testChangeFillColor() {
        shapeEditor.changeFillColor(Color.GREEN);
        assertEquals(Color.GREEN, shape.getFill());
    }

    @Test
    public void testDragShape() {
        shapeEditor.dragShape(10, 20);
        assertEquals(10, shape.getTranslateX(), 0.01);
        assertEquals(20, shape.getTranslateY(), 0.01);
    }

    @Test
    public void testMoveShapeTo() {
        shapeEditor.moveShapeTo( 30, 40);
        assertEquals(30, shape.getTranslateX(), 0.01);
        assertEquals(40, shape.getTranslateY(), 0.01);
    }

    @Test
    public void testApplyDropShadow() {
        shapeEditor.applyDropShadow();
        assertNotNull("Effect should be set", shape.getEffect());
        assertTrue(shape.getEffect() instanceof javafx.scene.effect.DropShadow);
    }

 

    @Test
    public void testOverlap() {
        shapeEditor.overlap();
        // Non c'è un getter diretto per z-index, ma verifichiamo che non ci siano errori.
        // La validità si verifica se il metodo viene chiamato senza eccezioni.
    }

   

    public class ShapeEditorImpl extends ShapeEditor {

        public ShapeEditorImpl(Shape s, double x, double y) {
            super(s, x, y);
        }

        public double getWidth() {
            return 0.0;
        }

        public double getHeight() {
            return 0.0;
        }

        public void changeHeightSize(double size) {
        }

        public void changeWidthSize(double size) {
        }
    }
}
