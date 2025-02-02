/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.CutShape;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
public class CutShapeCommandTest {
    
    private Clipboard clipboard;
    private Paper paper;
    private Shape rectangle;
    private CutShape cutCommand;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Inizializza JavaFX per evitare problemi di toolkit
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
            paper = new Paper(new AnchorPane(), new BorderPane(), null);
            rectangle = new Rectangle(50, 50, 100, 100);
            paper.addOnPaper(rectangle);
            cutCommand = new CutShape(paper, rectangle);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class CutShape.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");

            // Assicuriamoci che la figura sia inizialmente presente nel Paper
            assertTrue("La figura dovrebbe essere inizialmente presente nel Paper.", 
                       paper.getAnchorPanePaper().getChildren().contains(rectangle));

            // Esegui il comando
            cutCommand.execute();

            // Verifica che la figura sia stata rimossa dal Paper
            assertFalse("La figura dovrebbe essere stata rimossa dal Paper.", 
                        paper.getAnchorPanePaper().getChildren().contains(rectangle));

            // Verifica che la figura sia stata copiata nella clipboard
            assertEquals("La figura dovrebbe essere stata copiata nella clipboard.", 
                         rectangle, clipboard.getCopiedShape());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo undo di CutShape.
     */
    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");

            cutCommand.execute();
            cutCommand.undo();

            // Verifica che la figura sia stata riaggiunta al Paper
            assertTrue("La figura dovrebbe essere stata riaggiunta al Paper.", 
                       paper.getAnchorPanePaper().getChildren().contains(rectangle));

            // Verifica che la clipboard sia stata ripristinata allo stato precedente
            assertNull("La clipboard dovrebbe essere vuota se la figura non era originariamente presente nella clipboard.", 
                       clipboard.getCopiedShape());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    /**
     * Test del metodo redo di CutShape.
     */
    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");

            cutCommand.execute();
            cutCommand.undo();
            cutCommand.redo();

            // Verifica che la figura sia stata rimossa nuovamente dal Paper
            assertFalse("La figura dovrebbe essere stata rimossa nuovamente dal Paper.", 
                        paper.getAnchorPanePaper().getChildren().contains(rectangle));

            // Verifica che la figura sia ancora presente nella clipboard
            assertEquals("La figura dovrebbe essere ancora presente nella clipboard.", 
                         rectangle, clipboard.getCopiedShape());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
