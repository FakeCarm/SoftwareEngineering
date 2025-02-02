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
public class SendToBackTest {
    
    private Paper paper;
    private AnchorPane anchorPane;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private SendToBack sendToBackCommand;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Inizializza JavaFX per evitare errori di toolkit
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
            paper = new Paper(anchorPane, null, null);

            rectangle1 = new Rectangle(50, 50, 100, 100);
            rectangle1.setFill(Color.RED);
            rectangle2 = new Rectangle(70, 70, 100, 100);
            rectangle2.setFill(Color.BLUE);

            paper.addOnPaper(rectangle1);
            paper.addOnPaper(rectangle2);
            sendToBackCommand = new SendToBack( rectangle2);

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test of execute method, of class SendToBack.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");
            sendToBackCommand.execute();
            assertTrue(anchorPane.getChildren().indexOf(rectangle2) < anchorPane.getChildren().indexOf(rectangle1));
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo undo di SendToBack.
     */
    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");
            sendToBackCommand.execute();
            sendToBackCommand.undo();
            assertTrue(anchorPane.getChildren().indexOf(rectangle2) > anchorPane.getChildren().indexOf(rectangle1));
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo redo di SendToBack.
     */
    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");
            sendToBackCommand.execute();
            sendToBackCommand.undo();
            sendToBackCommand.redo();
            assertTrue(anchorPane.getChildren().indexOf(rectangle2) < anchorPane.getChildren().indexOf(rectangle1));
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
