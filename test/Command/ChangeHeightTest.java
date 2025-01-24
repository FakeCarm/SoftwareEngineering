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
public class ChangeHeightTest {
    
    private Rectangle rectTest;
    private ShapeEditor editorTest;
    private ChangeHeight changeHeight;
    private double testSize = 5;
    private double startHeight;
    
    public ChangeHeightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.rectTest = new Rectangle(10,10,10,10);
        this.editorTest = new RectangleShapeEditor(rectTest,2,2);
        startHeight = editorTest.getHeight();
        Paper drawingPaper = new Paper(new AnchorPane(), new BorderPane());
        this.changeHeight = new ChangeHeight(drawingPaper,rectTest,this.editorTest,this.testSize);
     
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ChangeHeight.
     */
    @Test
    public void testExecute() {
        System.out.println("TEST: execute()");
        changeHeight.execute();
        assertEquals(testSize,this.rectTest.getHeight(),0.5);
    }

    /**
     * Test of undo method, of class ChangeHeight.
     */
    @Test
    public void testUndo() {
        changeHeight.execute();
        assertEquals(testSize,this.rectTest.getHeight(),0.5);
        changeHeight.undo();
        assertEquals(startHeight,this.rectTest.getHeight(),0.5);  
    }

    /**
     * Test of redo method, of class ChangeHeight.
     */
    @Test
    public void testRedo() {
        fail("The test case non implementato");
    }
    
    
    
}
