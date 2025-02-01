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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafxapplication1.Paper;
import javafxapplication1.RectangleShapeEditor;
import javafxapplication1.ShapeEditor;
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
public class ChangeHeightTest {
    
    private Rectangle rectTest;
    private ShapeEditor editorTest;
    private ChangeHeight changeHeight;
    private double testSize = 5;
    private double startHeight;
    private Paper drawingPaper;
    
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
            rectTest = new Rectangle(10, 10, 10, 10);
            editorTest = new RectangleShapeEditor(rectTest, 2, 2);
            startHeight = editorTest.getHeight();
            drawingPaper = new Paper(new AnchorPane(), new BorderPane());
            changeHeight = new ChangeHeight(editorTest, testSize);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
   @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: execute()");
            changeHeight.execute();
            assertEquals(testSize, rectTest.getHeight(), 0.5);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            changeHeight.execute();
            assertEquals(testSize, rectTest.getHeight(), 0.5);
            changeHeight.undo();
            assertEquals(startHeight, rectTest.getHeight(), 0.5);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: redo()");
            changeHeight.execute();
            assertEquals(testSize, rectTest.getHeight(), 0.5);
            changeHeight.undo();
            assertEquals(startHeight, rectTest.getHeight(), 0.5);
            changeHeight.redo();
            assertEquals(testSize, rectTest.getHeight(), 0.5);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    
    
}
