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
public class SendToBackTest {
    
    private Paper paper;
    private AnchorPane anchorPane;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private SendToBack sendToBackCommand;
    
    public SendToBackTest() {
    }
    
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
        sendToBackCommand = new SendToBack(paper, rectangle2);
    }

    /**
     * Test of execute method, of class SendToBack.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        sendToBackCommand.execute();
        assertTrue(anchorPane.getChildren().indexOf(rectangle2) < anchorPane.getChildren().indexOf(rectangle1));
    }

    /**
     * Test of undo method, of class SendToBack.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        sendToBackCommand.execute();
        sendToBackCommand.undo();
        assertTrue(anchorPane.getChildren().indexOf(rectangle2) > anchorPane.getChildren().indexOf(rectangle1));
    }

    /**
     * Test of redo method, of class SendToBack.
     */
    @Test
    public void testRedo() {
        System.out.println("redo");
        sendToBackCommand.execute();
        sendToBackCommand.undo();
        sendToBackCommand.redo();
        assertTrue(anchorPane.getChildren().indexOf(rectangle2) < anchorPane.getChildren().indexOf(rectangle1));
    }
    
}
