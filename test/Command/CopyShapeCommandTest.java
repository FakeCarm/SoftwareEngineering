/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.CopyShape;
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
public class CopyShapeCommandTest {
    
    private Clipboard clipboard;
    private Paper paper;
    private Shape rectangle;
    private Shape previouslyCopiedShape;
    private CopyShape copyCommand;
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Inizializza JavaFX
        new JFXPanel(); // Necessario per avviare JavaFX
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await(); // Aspetta che JavaFX sia pronto
    }

    @Before
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            clipboard = Clipboard.getInstance();
            clipboard.clear();
            paper = new Paper(new AnchorPane(), new BorderPane(), null);
            rectangle = new Rectangle(50, 50, 100, 100);
            previouslyCopiedShape = new Rectangle(20, 20, 50, 50);
            clipboard.copy(previouslyCopiedShape);
            copyCommand = new CopyShape( rectangle);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test of execute method, of class CopyShape.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");

            // Verifica che la clipboard contenga inizialmente la figura precedente
            assertEquals("La clipboard dovrebbe contenere la figura precedentemente copiata.",
                    previouslyCopiedShape, clipboard.getCopiedShape());

            // Esegui il comando
            copyCommand.execute();

            // Verifica che la nuova figura sia stata copiata nella clipboard
            assertEquals("La figura corrente dovrebbe essere copiata nella clipboard.",
                    rectangle, clipboard.getCopiedShape());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");

            // Esegui il comando e poi annullalo
            copyCommand.execute();
            copyCommand.undo();

            // Verifica che la clipboard sia stata ripristinata alla figura precedente
            assertEquals("La clipboard dovrebbe contenere la figura precedentemente copiata.",
                    previouslyCopiedShape, clipboard.getCopiedShape());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");

            // Esegui il comando, annullalo e poi ripristinalo
            copyCommand.execute();
            copyCommand.undo();
            copyCommand.redo();

            // Verifica che la figura corrente sia nuovamente copiata nella clipboard
            assertEquals("La figura corrente dovrebbe essere di nuovo copiata nella clipboard.",
                    rectangle, clipboard.getCopiedShape());

            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
