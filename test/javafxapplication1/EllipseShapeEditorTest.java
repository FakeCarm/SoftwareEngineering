/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
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
public class EllipseShapeEditorTest {
    
    private EllipseShapeEditor editorTest;
    private Ellipse ellipseTest;
    private double widthTest = 5;
    private double heightTest = 10;
    
    public EllipseShapeEditorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ellipseTest = new Ellipse(1,1,1,1);
        Pane pane = new Pane();
        this.editorTest = new EllipseShapeEditor(ellipseTest,pane,2,2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dragShape method, of class EllipseShapeEditor.
     */
    @Test
    public void changeHeightSize() {
        System.out.println("TEST: changeHeight()");
        editorTest.changeHeightSize(this.heightTest);
        assertEquals(heightTest,editorTest.getHeight(),0.5);
        
    }
    
    @Test
    public void changeWidthSize() {
        System.out.println("TEST: changeWidth()");
        editorTest.changeWidthSize(this.widthTest);
        assertEquals(widthTest,editorTest.getWidth(),0.5);
    }
    
    @Test
    public void getWidth() {
        System.out.println("TEST: getWidth()");
        this.editorTest.changeWidthSize(widthTest);
        assertEquals(widthTest,editorTest.getWidth(),0.5);
    }
    
    @Test
    public void getHeight() {
        System.out.println("TEST: getHeight()");
        this.editorTest.changeHeightSize(heightTest);
        assertEquals(heightTest,editorTest.getHeight(),0.5);
        
    }
    
}
