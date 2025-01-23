/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import Command.AddShape;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
public class AddShapeTest {
    
    private AnchorPane pane;
    private BorderPane borderPane;
    private Paper paper;
    private Rectangle testRectangle;
    private AddShape addShapeCommand;
    
    public AddShapeTest() {
    }
    
    /*
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    */
    
     @Before
    public void setUp() {
        
        pane = new AnchorPane();
        paper = new Paper(pane, new BorderPane());

        
        testRectangle = new Rectangle(50, 50, 100, 100);
        testRectangle.setFill(Color.BLUE);
        testRectangle.setStroke(Color.BLACK);

        
        addShapeCommand = new AddShape(paper, testRectangle);
    }
    /*
    @After
    public void tearDown() {
    }
    */
    /**
     * Test of execute method, of class AddShape.
     */
    @Test
    public void testExecute() {
        System.out.println("Testing execute...");
        addShapeCommand.execute();
        assertTrue("La forma dovrebbe essere presente nell'AnchorPane.", pane.getChildren().contains(testRectangle));
    }

    /**
     * Test of undo method, of class AddShape.
     */
    @Test
    public void testUndo() {
        System.out.println("Testing undo...");
        addShapeCommand.execute();
        addShapeCommand.undo();
        assertFalse("La forma dovrebbe essere stata rimossa dall'AnchorPane.", pane.getChildren().contains(testRectangle));
    }
    @Test
    public void testRedo() {
        System.out.println("Testing redo...");
        addShapeCommand.execute();
        addShapeCommand.undo();
        addShapeCommand.redo();
        assertTrue("La forma dovrebbe essere di nuovo presente nell'AnchorPane.", pane.getChildren().contains(testRectangle));
    }
    
}
