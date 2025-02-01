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
public class ChangeWidthTest {
    
    private Rectangle rectTest;
    private ShapeEditor editorTest;
    private ChangeWidth changeWidth;
    private double testSize = 5;
    private double startWidth;
    private Paper drawingPaper;
    
    public ChangeWidthTest() {
    }
    
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
            rectTest = new Rectangle(15, 15, 15, 15);
            editorTest = new RectangleShapeEditor(rectTest, 2, 2);
            startWidth = editorTest.getWidth();
            drawingPaper = new Paper(new AnchorPane(), new BorderPane());
            changeWidth = new ChangeWidth(editorTest, testSize);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: execute()");
            changeWidth.execute();
            assertEquals(testSize, rectTest.getWidth(), 0.5);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: undo()");
            changeWidth.execute();
            assertEquals(testSize, rectTest.getWidth(), 0.5);
            changeWidth.undo();
            assertEquals(startWidth, rectTest.getWidth(), 0.5);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: redo()");
            changeWidth.execute();
            assertEquals(testSize, rectTest.getWidth(), 0.5);
            changeWidth.undo();
            assertEquals(startWidth, rectTest.getWidth(), 0.5);
            changeWidth.redo();
            assertEquals(testSize, rectTest.getWidth(), 0.5);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
