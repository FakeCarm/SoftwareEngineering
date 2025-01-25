/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
public class BringToFrontTest {
    
    private Paper paper;
    private AnchorPane anchorPane;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private BringToFront bringToFrontCommand;
    

    @Before
    public void setUp() {
        anchorPane = new AnchorPane();
        paper = new Paper(anchorPane, null);
        rectangle1 = new Rectangle(50, 50, 100, 100);
        rectangle1.setFill(Color.RED);
        rectangle2 = new Rectangle(70, 70, 100, 100);
        rectangle2.setFill(Color.BLUE);
        paper.addOnPaper(rectangle1);
        paper.addOnPaper(rectangle2);
        bringToFrontCommand = new BringToFront(paper, rectangle1);
    }

    /**
     * Test of execute method, of class BringToFront.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        bringToFrontCommand.execute();
        assertTrue(anchorPane.getChildren().indexOf(rectangle1) > anchorPane.getChildren().indexOf(rectangle2));
    }
    /**
     * Test of undo method, of class BringToFront.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        bringToFrontCommand.execute();
        bringToFrontCommand.undo();
        assertTrue(anchorPane.getChildren().indexOf(rectangle1) < anchorPane.getChildren().indexOf(rectangle2));
    }

    /**
     * Test of redo method, of class BringToFront.
     */
    @Test
    public void testRedo() {
        System.out.println("redo");
        bringToFrontCommand.execute();
        bringToFrontCommand.undo();
        bringToFrontCommand.redo();
        assertTrue(anchorPane.getChildren().indexOf(rectangle1) > anchorPane.getChildren().indexOf(rectangle2));
    }
    
}
