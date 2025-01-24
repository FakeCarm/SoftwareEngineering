/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
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
public class LineShapeEditorTest {
    
    private LineShapeEditor editorTest;
    private Line lineTest;
    private double widthTest = 15;
    private double heightTest = 10;
    
    public LineShapeEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.lineTest = new Line(5,5,5,5);
        Pane pane = new Pane();
        this.editorTest = new LineShapeEditor(lineTest,2,2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changeHeightSize method, of class LineShapeEditor.
     */
    @Test
    public void testChangeHeightSize() {
        System.out.println("TEST: changeHeightSize()");
        editorTest.changeHeightSize(this.heightTest);
        assertEquals(heightTest,editorTest.getHeight(),0.5);
    }

    /**
     * Test of changeWidthSize method, of class LineShapeEditor.
     */
    @Test
    public void testChangeWidthSize() {
        System.out.println("TEST: changeWidthSize()");
        editorTest.changeWidthSize(this.widthTest);
        assertEquals(widthTest,editorTest.getWidth(),0.5);
    }

    /**
     * Test of getWidth method, of class LineShapeEditor.
     */
    @Test
    public void testGetWidth() {
        System.out.println("TEST: getWidth()");
        this.editorTest.changeWidthSize(widthTest);
        assertEquals(widthTest,editorTest.getWidth(),0.5);
        
    }

    /**
     * Test of getHeight method, of class LineShapeEditor.
     */
    @Test
    public void testGetHeight() {
        System.out.println("TEST: getHeight()");
        this.editorTest.changeHeightSize(heightTest);
        assertEquals(heightTest,editorTest.getHeight(),0.5);
       
        
    }

    
    
    
    
    
}
