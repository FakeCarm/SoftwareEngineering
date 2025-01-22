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
    double offsetXTest = 5;
    double offsetYTest = 5;
    
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
        this.lineTest = new Line(1,1,1,1);
        Pane pane = new Pane();
        this.editorTest = new LineShapeEditor(lineTest,pane,2,2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dragShape method, of class LineShapeEditor.
     */
    @Test
    public void testDragShape() {
        System.out.println("TEST: dragShape()");
        double offsetX = 5.0;
        double offsetY = 5.0;
        double resultX = this.lineTest.getStartX()+ this.offsetXTest;
        double resultY = this.lineTest.getStartY() + this.offsetYTest;
        double resultXend = this.lineTest.getEndX()+ this.offsetXTest;
        double resultYend = this.lineTest.getEndY() + this.offsetYTest;
        editorTest.dragShape(offsetX, offsetY);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(this.lineTest.getStartX(), resultX,0.5);
        assertEquals(this.lineTest.getStartY(), resultY,0.5);
        assertEquals(this.lineTest.getEndX(), resultX,0.5);
        assertEquals(this.lineTest.getEndY(), resultY,0.5);
    }
    
    
    
}
