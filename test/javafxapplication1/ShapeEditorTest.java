/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cassd
 */
public class ShapeEditorTest {
    
    private ShapeEditor shapeEditorTest;
    private Rectangle rectangleTest;
    private Pane paneEditorTest;
    private double startX;
    private double startY;
    private double offsetX;
    private double offsetY;
    
    public ShapeEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.startX = 2;
        this.startY = 2;
        this.offsetX = 2.5;
        this.offsetY = 5;
        this.paneEditorTest = new Pane();
        this.rectangleTest = new Rectangle(1,1,1,1);
        this.shapeEditorTest = new RectangleShapeEditor(this.rectangleTest,this.paneEditorTest,2,2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStartX method, of class ShapeEditor.
     */
    @Test
    public void testGetStartX() {
        System.out.println("TEST: getStartX()");
        assertEquals(this.startX,this.shapeEditorTest.getStartX(),1);        
    }

    /**
     * Test of getStartY method, of class ShapeEditor.
     */
    @Test
    public void testGetStartY() {
        System.out.println("TEST: getStartY()");
        assertEquals(this.startY,this.shapeEditorTest.getStartY(),1);
    }

    /**
     * Test of setStartX method, of class ShapeEditor.
     */
    @Test
    public void testSetStartX() {
        System.out.println("TEST: setStartX()");
        double startXtest = 3;
        this.shapeEditorTest.setStartX(startXtest);
        assertEquals(startXtest,this.shapeEditorTest.getStartX(),0.5);
        
    }

    /**
     * Test of setStartY method, of class ShapeEditor.
     */
    @Test
    public void testSetStartY() {
        System.out.println("TEST: setStartX()");
        double startYtest = 4;
        this.shapeEditorTest.setStartY(startYtest);
        assertEquals(startYtest,this.shapeEditorTest.getStartY(),0.5);
    }

    /**
     * Test of getShape method, of class ShapeEditor.
     */
    @Test
    public void testGetShape() {
        System.out.println("TEST: getShape()");
        Rectangle rect = new Rectangle(1,1,1,1);
        this.shapeEditorTest.setShape(rect);
        assertEquals(rect,this.shapeEditorTest.getShape());
           
    }

    /**
     * Test of setShape method, of class ShapeEditor.
     */
    @Test
    public void testSetShape() {
        System.out.println("TEST: setShape()");
        Rectangle rect = new Rectangle(1,1,1,1);
        this.shapeEditorTest.setShape(rect);
        assertNotNull(this.shapeEditorTest.getShape());
        assertEquals(rect,this.shapeEditorTest.getShape());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    
    /**
     * Test of changeStrokeColor method, of class ShapeEditor.
     */
    @Test
    public void testChangeStrokeColor() {
        System.out.println("TEST: changeStrokeColor");
        Color colorTest = Color.BLUE;
        this.shapeEditorTest.changeStrokeColor(colorTest);
        assertEquals(shapeEditorTest.getShape().getStroke(), colorTest);
        
        
    }

    /**
     * Test of changeFillColor method, of class ShapeEditor.
     */
    @Test
    public void testChangeFillColor() {
        System.out.println("TEST: changeFillColor");
        Line line = new Line(1,1,1,1);
        this.shapeEditorTest.setShape(line);
        Color colorTest = Color.BLACK;
        this.shapeEditorTest.changeFillColor(colorTest);    
        assertEquals(shapeEditorTest.getShape().getFill(), colorTest);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of changeSize method, of class ShapeEditor.
     */
    @Test
    public void testChangeSize() {
        System.out.println("FUNCTION NOT IMPLEMENTED: changeSize");
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of dragShape method, of class ShapeEditor.
     */
    @Test
    public void testDragShape() {
        
        
    }
    
}
