/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.AddShape;
import Command.Command;
import Command.Invoker;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
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
 * @author cassd
 */
public class InvokerTest {
    
    private Invoker testInvoker;
    private Paper testDrawingPaper;
    private Line testLine;
    private Rectangle testRectangle;

    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Inizializza JavaFX per evitare problemi con il toolkit
        new JFXPanel();
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await();
    }

    @Before
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Invoker.resetInvoker();
            testDrawingPaper = new Paper(new AnchorPane(), new BorderPane(), null);
            testLine = new Line(0, 0, 50, 50);
            testRectangle = new Rectangle(10, 10, 100, 50);
            testInvoker = Invoker.getInvoker();

            // Assicura che gli stack di undo e redo siano vuoti
            while (testInvoker.canUndo()) {
                testInvoker.undo();
            }
            while (testInvoker.canRedo()) {
                testInvoker.redo();
            }
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        Platform.runLater(() -> {
            while (testInvoker.canUndo()) {
                testInvoker.undo();
            }
            while (testInvoker.canRedo()) {
                testInvoker.redo();
            }
            testDrawingPaper.getAnchorPanePaper().getChildren().clear();
        });
    }


    /**
     * Test of getInvoker method, of class Invoker.
     */
    @Test
    public void testGetInvoker() {
        System.out.println("Testing getInvoker...");
        Invoker invokerInstance = Invoker.getInvoker();
        assertNotNull("L'istanza di Invoker non dovrebbe essere null.", invokerInstance);
        assertEquals("L'istanza di Invoker dovrebbe essere un singleton.", testInvoker, invokerInstance);
    }

    @Test
    public void testExecuteCommand() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing executeCommand...");
            AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
            testInvoker.executeCommand(addLineCommand);
            assertTrue("La linea dovrebbe essere presente nel Paper dopo execute.",
                    testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");
            AddShape addRectangleCommand = new AddShape(testDrawingPaper, testRectangle);
            testInvoker.executeCommand(addRectangleCommand);
            assertTrue("Il rettangolo dovrebbe essere presente nel Paper dopo execute.",
                    testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

            testInvoker.undo();
            assertFalse("Il rettangolo dovrebbe essere stato rimosso dal Paper dopo undo.",
                    testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");
            AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
            testInvoker.executeCommand(addLineCommand);
            testInvoker.undo();

            assertFalse("La linea dovrebbe essere stata rimossa dal Paper dopo undo.",
                    testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));

            testInvoker.redo();
            assertTrue("La linea dovrebbe essere stata riaggiunta al Paper dopo redo.",
                    testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @Test
    public void testCanUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing canUndo...");
            assertFalse("canUndo dovrebbe essere false all'inizio.", testInvoker.canUndo());

            AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
            testInvoker.executeCommand(addLineCommand);
            assertTrue("canUndo dovrebbe essere true dopo executeCommand.", testInvoker.canUndo());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testCanRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing canRedo...");
            assertFalse("canRedo dovrebbe essere false all'inizio.", testInvoker.canRedo());

            AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
            testInvoker.executeCommand(addLineCommand);
            testInvoker.undo();
            assertTrue("canRedo dovrebbe essere true dopo undo.", testInvoker.canRedo());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testUndoRedoStateIntegration() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo/redo integration...");
            AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
            AddShape addRectangleCommand = new AddShape(testDrawingPaper, testRectangle);
            testInvoker.executeCommand(addLineCommand);
            testInvoker.executeCommand(addRectangleCommand);

            assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
            assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

            testInvoker.undo();
            testInvoker.undo();

            assertFalse(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
            assertFalse(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

            testInvoker.redo();
            testInvoker.redo();

            assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
            assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
