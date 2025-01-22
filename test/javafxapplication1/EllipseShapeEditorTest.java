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
    double offsetXTest = 5;
    double offsetYTest = 5;
    
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
    public void testDragShape() {
        System.out.println("TEST: dragShape()");
        double offsetX = 5.0;
        double offsetY = 5.0;
        double resultX = this.ellipseTest.getCenterX() + this.offsetXTest;
        double resultY = this.ellipseTest.getCenterY() + this.offsetYTest;
        editorTest.dragShape(offsetX, offsetY);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(ellipseTest.getCenterX(), resultX,0.5);
        assertEquals(ellipseTest.getCenterY(), resultY,0.5);
    }
    
}
