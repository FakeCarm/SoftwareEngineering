/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
public class RectangleShapeEditorTest {
    private RectangleShapeEditor editorTest;
    private Rectangle rectTest;
    private double widthTest = 5;
    private double heightTest = 10;
   
    
    public RectangleShapeEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.rectTest = new Rectangle(5,5,5,5);
        Pane pane = new Pane();
        this.editorTest = new RectangleShapeEditor(rectTest,2,2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changeHeightSize method, of class RectangleShapeEditor.
     */
    @Test
    public void testChangeHeightSize() {
        System.out.println("TEST: changeHeight()");
        editorTest.changeHeightSize(this.heightTest);
        assertEquals(heightTest,editorTest.getHeight(),0.5);
    }

    /**
     * Test of changeWidthSize method, of class RectangleShapeEditor.
     */
    @Test
    public void testChangeWidthSize() {
         System.out.println("TEST: changeWidth()");
        editorTest.changeWidthSize(this.widthTest);
        assertEquals(widthTest,editorTest.getWidth(),0.5);
    }

    /**
     * Test of getWidth method, of class RectangleShapeEditor.
     */
    @Test
    public void testGetWidth() {
        System.out.println("TEST: getWidth()");
        this.editorTest.changeWidthSize(widthTest);
        assertEquals(widthTest,editorTest.getWidth(),0.5);
    }

    /**
     * Test of getHeight method, of class RectangleShapeEditor.
     */
    @Test
    public void testGetHeight() {
        System.out.println("TEST: getHeight()");
        this.editorTest.changeHeightSize(heightTest);
        assertEquals(heightTest,editorTest.getHeight(),0.5);
    }
    
    
    
}
