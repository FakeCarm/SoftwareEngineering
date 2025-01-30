/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.PasteShape;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafxapplication1.Clipboard;
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
public class PasteShapeCommandTest {
    
    private Clipboard clipboard;
    private Paper paper;
    private Shape rectangle;
    private PasteShape pasteCommand;
    

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
            clipboard = Clipboard.getInstance();
            clipboard.clear();
            paper = new Paper(new AnchorPane(), new BorderPane());
            rectangle = new Rectangle(50, 50, 100, 100);
            rectangle.setFill(Color.RED);
            rectangle.setStroke(Color.BLACK);
            clipboard.copy(rectangle);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class PasteShape.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");

            assertNotNull("La clipboard dovrebbe contenere una figura.", clipboard.getCopiedShape());

            double pasteX = 200;
            double pasteY = 300;
            pasteCommand = new PasteShape(paper, pasteX, pasteY);
            pasteCommand.execute();

            assertEquals("Il Paper dovrebbe contenere una figura incollata.", 1, paper.getAnchorPanePaper().getChildren().size());

            Shape pastedShape = (Shape) paper.getAnchorPanePaper().getChildren().get(0);
            assertTrue("La figura incollata dovrebbe essere un Rectangle.", pastedShape instanceof Rectangle);
            assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteX, ((Rectangle) pastedShape).getX(), 0.01);
            assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteY, ((Rectangle) pastedShape).getY(), 0.01);

            assertEquals("Il colore di riempimento della figura incollata dovrebbe essere lo stesso dell'originale.", Color.RED, ((Rectangle) pastedShape).getFill());
            assertEquals("Il colore del bordo della figura incollata dovrebbe essere lo stesso dell'originale.", Color.BLACK, ((Rectangle) pastedShape).getStroke());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo undo di PasteShape.
     */
    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");

            double pasteX = 200;
            double pasteY = 300;
            pasteCommand = new PasteShape(paper, pasteX, pasteY);
            pasteCommand.execute();

            assertEquals("La figura dovrebbe essere presente nel Paper dopo execute.", 1, paper.getAnchorPanePaper().getChildren().size());

            pasteCommand.undo();
            assertEquals("La figura dovrebbe essere stata rimossa dal Paper dopo undo.", 0, paper.getAnchorPanePaper().getChildren().size());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo redo di PasteShape.
     */
    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");

            double pasteX = 200;
            double pasteY = 300;
            pasteCommand = new PasteShape(paper, pasteX, pasteY);
            pasteCommand.execute();
            pasteCommand.undo();
            assertEquals("La figura dovrebbe essere stata rimossa dal Paper dopo undo.", 0, paper.getAnchorPanePaper().getChildren().size());

            pasteCommand.redo();
            assertEquals("La figura dovrebbe essere stata riaggiunta al Paper dopo redo.", 1, paper.getAnchorPanePaper().getChildren().size());

            Shape pastedShape = (Shape) paper.getAnchorPanePaper().getChildren().get(0);
            assertTrue("La figura incollata dovrebbe essere un Rectangle.", pastedShape instanceof Rectangle);
            assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteX, ((Rectangle) pastedShape).getX(), 0.01);
            assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteY, ((Rectangle) pastedShape).getY(), 0.01);

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    
}
