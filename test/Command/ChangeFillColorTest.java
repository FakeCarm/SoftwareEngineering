/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafxapplication1.EllipseShapeEditor;
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
public class ChangeFillColorTest {
    
    private Ellipse ellipseTest;
    private ShapeEditor editorTest;
    private Color colorTest;
    private Color lastColor;
    private ChangeFillColor changeColorFill;
    
    public ChangeFillColorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ellipseTest = new Ellipse(10,10,10,10);
        Pane pane = new Pane();
        this.editorTest = new EllipseShapeEditor(ellipseTest,pane,2,2);
        this.lastColor = (Color) ellipseTest.getFill();
        this.colorTest = Color.RED;
        Paper drawingPaper = new Paper(new AnchorPane(), new BorderPane());
        this.changeColorFill = new ChangeFillColor(drawingPaper,ellipseTest,this.editorTest,this.colorTest);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ChangeFillColor.
     */
    @Test
    public void testExecute() {
        System.out.println("Colore TEST" + colorTest);
        this.changeColorFill.execute();
        assertEquals(this.colorTest,this.editorTest.getShape().getFill());
    }

    /**
     * Test of undo method, of class ChangeFillColor.
     */
    @Test
    public void testUndo() {
        System.out.println("TEST: undo()");
        changeColorFill.execute();
        assertEquals(this.colorTest,this.editorTest.getShape().getFill());
        changeColorFill.undo();
        assertEquals(this.lastColor,this.editorTest.getShape().getFill());
    }

    /**
     * Test of redo method, of class ChangeFillColor.
     */
    @Test
    public void testRedo() {
        System.out.println("TEST: undo()");
        changeColorFill.execute();
        assertEquals(this.colorTest,this.editorTest.getShape().getFill());
        changeColorFill.undo();
        assertEquals(this.lastColor,this.editorTest.getShape().getFill());
        changeColorFill.redo();
        assertEquals(this.colorTest,this.editorTest.getShape().getFill());
    }
    
}
