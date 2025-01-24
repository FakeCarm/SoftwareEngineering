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
 * @author vinjs
 */
public class DragShapeTest {
    
    private Paper paper;
    private AnchorPane pane;
    private Rectangle testRectangle;
    private ShapeEditor shapeEditor;
    private DragShape dragShapeCommand;

    private double initialX = 50.0;
    private double initialY = 50.0;
    private double finalX = 150.0;
    private double finalY = 150.0;
    
    public DragShapeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pane = new AnchorPane();
        paper = new Paper(pane, new BorderPane());

        testRectangle = new Rectangle(50, 50, 100, 100);
        testRectangle.setFill(Color.BLUE);
        testRectangle.setStroke(Color.BLACK);
        paper.addOnPaper(testRectangle);
        shapeEditor = new RectangleShapeEditor(testRectangle, initialX, initialY);
        dragShapeCommand = new DragShape(paper, testRectangle, shapeEditor, initialX, initialY, finalX, finalY);
    }

    /**
     * Test of execute method, of class DragShape.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        dragShapeCommand.execute();
        assertEquals(finalX, testRectangle.getTranslateX(), 0.01);
        assertEquals(finalY, testRectangle.getTranslateY(), 0.01);
    }

    /**
     * Test of undo method, of class DragShape.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        dragShapeCommand.execute();
        dragShapeCommand.undo();
        assertEquals(initialX, testRectangle.getTranslateX(), 0.01);
        assertEquals(initialY, testRectangle.getTranslateY(), 0.01);
    }

    /**
     * Test of redo method, of class DragShape.
     */
    @Test
    public void testRedo() {
        System.out.println("redo");
        dragShapeCommand.execute();
        dragShapeCommand.undo();
        dragShapeCommand.redo();
        assertEquals(finalX, testRectangle.getTranslateX(), 0.01);
        assertEquals(finalY, testRectangle.getTranslateY(), 0.01);
    }
    
}
