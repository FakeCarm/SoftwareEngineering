/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
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
            anchorPane = new AnchorPane();
            paper = new Paper(anchorPane, null,null);

            rectangle1 = new Rectangle(50, 50, 100, 100);
            rectangle1.setFill(Color.RED);

            rectangle2 = new Rectangle(70, 70, 100, 100);
            rectangle2.setFill(Color.BLUE);

            paper.addOnPaper(rectangle1);
            paper.addOnPaper(rectangle2);

            bringToFrontCommand = new BringToFront(paper, rectangle1);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test of execute method, of class BringToFront.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("execute");
            bringToFrontCommand.execute();
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
        assertTrue(anchorPane.getChildren().indexOf(rectangle1) > anchorPane.getChildren().indexOf(rectangle2));
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("undo");
            bringToFrontCommand.execute();
            bringToFrontCommand.undo();
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
        assertTrue(anchorPane.getChildren().indexOf(rectangle1) < anchorPane.getChildren().indexOf(rectangle2));
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("redo");
            bringToFrontCommand.execute();
            bringToFrontCommand.undo();
            bringToFrontCommand.redo();
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
        assertTrue(anchorPane.getChildren().indexOf(rectangle1) > anchorPane.getChildren().indexOf(rectangle2));
    }
    
}
