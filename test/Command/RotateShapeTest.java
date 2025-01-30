/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafxapplication1.Paper;
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
public class RotateShapeTest {
    
    private Paper paper;
    private Rectangle shape;
    private RotateShape rotateCommand;
    private double initialRotation;
    private double newRotation;
    

    @Before
    public void setUp() {
        BorderPane borderPane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        paper = new Paper(anchorPane, borderPane);
        shape = new Rectangle(100, 50);
        initialRotation = 0;
        newRotation = 45;
        shape.setRotate(initialRotation);
        rotateCommand = new RotateShape(paper, shape, initialRotation, newRotation);
    }
    
    @Test
    public void testExecute() {
        System.out.println("Test: execute()");
        rotateCommand.execute();
        assertEquals(newRotation, shape.getRotate(), 0.01);
    }

    /**
     * Test of undo method, of class RotateShape.
     */
    @Test
    public void testUndo() {
        System.out.println("Test: undo()");
        rotateCommand.execute();
        assertEquals(initialRotation, shape.getRotate(), 0.01);
    }

    /**
     * Test of redo method, of class RotateShape.
     */
    @Test
    public void testRedo() {
        System.out.println("Test: redo()");
        rotateCommand.execute();
        rotateCommand.undo();
        rotateCommand.redo();
        assertEquals(newRotation, shape.getRotate(), 0.01);
    }
    
}
