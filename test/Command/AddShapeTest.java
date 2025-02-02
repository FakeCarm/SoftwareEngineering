/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.AddShape;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
public class AddShapeTest {
    
    private AnchorPane pane;
    private Paper paper;
    private Rectangle testRectangle;
    private AddShape addShapeCommand;
    

    @BeforeClass
    public static void setUpClass() throws Exception {
        new JFXPanel(); 
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await(); 
    }

    @Before
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            pane = new AnchorPane();
            paper = new Paper(pane, new BorderPane(),null);

            testRectangle = new Rectangle(50, 50, 100, 100);
            testRectangle.setFill(Color.BLUE);
            testRectangle.setStroke(Color.BLACK);

            addShapeCommand = new AddShape(paper, testRectangle);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
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
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");
            addShapeCommand.execute();
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
        assertTrue("La forma dovrebbe essere presente nell'AnchorPane.", pane.getChildren().contains(testRectangle));
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");
            addShapeCommand.execute();
            addShapeCommand.undo();
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
        assertFalse("La forma dovrebbe essere stata rimossa dall'AnchorPane.", pane.getChildren().contains(testRectangle));
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");
            addShapeCommand.execute();
            addShapeCommand.undo();
            addShapeCommand.redo();
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
        assertTrue("La forma dovrebbe essere di nuovo presente nell'AnchorPane.", pane.getChildren().contains(testRectangle));
    }
    
}
