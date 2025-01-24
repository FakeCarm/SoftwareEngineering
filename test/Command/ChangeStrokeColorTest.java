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
import javafx.scene.paint.Color;
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
public class ChangeStrokeColorTest {
    
    private Rectangle rectTest;
    private ShapeEditor editorTest;
    private Color colorTest;
    private Color lastColor;
    private ChangeStrokeColor changeColorStroke;
    
    
    public ChangeStrokeColorTest() {
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
        this.lastColor = (Color) rectTest.getStroke();
        this.colorTest = Color.CYAN;
        Paper drawingPaper = new Paper(new AnchorPane(), new BorderPane());
        this.changeColorStroke = new ChangeStrokeColor(drawingPaper,rectTest,this.editorTest,this.colorTest);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ChangeStrokeColor.
     */
    @Test
    public void testExecute() {
        System.out.println("Colore TEST" + colorTest);
        this.changeColorStroke.execute();
        assertEquals(this.colorTest,this.editorTest.getShape().getStroke());
    }

    /**
     * Test of undo method, of class ChangeStrokeColor.
     */
    @Test
    public void testUndo() {
        System.out.println("TEST: undo()");
        changeColorStroke.execute();
        assertEquals(this.colorTest,this.editorTest.getShape().getStroke());
        changeColorStroke.undo();
        assertEquals(this.lastColor,this.editorTest.getShape().getStroke()); 
    }

    /**
     * Test of redo method, of class ChangeStrokeColor.
     */
    @Test
    public void testRedo() {
        System.out.println("TEST: undo()");
        changeColorStroke.execute();
        assertEquals(this.colorTest,this.editorTest.getShape().getStroke());
        changeColorStroke.undo();
        assertEquals(this.lastColor,this.editorTest.getShape().getStroke());
        changeColorStroke.redo();
        assertEquals(this.colorTest,this.editorTest.getShape().getStroke());
        
        
    }
    
}
