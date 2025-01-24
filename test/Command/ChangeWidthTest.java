/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafxapplication1.Paper;
import javafxapplication1.RectangleShapeEditor;
import javafxapplication1.ShapeEditor;
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
public class ChangeWidthTest {
    
    private Rectangle rectTest;
    private ShapeEditor editorTest;
    private ChangeWidth changeWidth;
    private double testSize = 5;
    private double startWidth;
    
    public ChangeWidthTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.rectTest = new Rectangle(15,15,15,15);
        this.editorTest = new RectangleShapeEditor(rectTest,2,2);
        startWidth = editorTest.getHeight();
        Paper drawingPaper = new Paper(new AnchorPane(), new BorderPane());
        this.changeWidth = new ChangeWidth(drawingPaper,rectTest,this.editorTest,this.testSize);
     
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ChangeWidth.
     */
    @Test
    public void testExecute() {
        System.out.println("TEST: execute()");
        changeWidth.execute();
        assertEquals(testSize,this.rectTest.getWidth(),0.5);
    }

    /**
     * Test of undo method, of class ChangeWidth.
     */
    @Test
    public void testUndo() {
        
        changeWidth.execute();
        assertEquals(testSize,this.rectTest.getWidth(),0.5);
        changeWidth.undo();
        assertEquals(startWidth,this.rectTest.getWidth(),0.5); 
    }

    /**
     * Test of redo method, of class ChangeWidth.
     */
    @Test
    public void testRedo() {
        
        fail("The test case is a prototype.");
    }
    
}
