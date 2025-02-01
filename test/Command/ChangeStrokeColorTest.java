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
import javafx.scene.paint.Color;
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
public class ChangeStrokeColorTest {
    
    private Rectangle rectTest;
    private ShapeEditor editorTest;
    private Color colorTest;
    private Color lastColor;
    private ChangeStrokeColor changeColorStroke;
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
            lastColor = (Color) rectTest.getStroke();
            colorTest = Color.CYAN;
            drawingPaper = new Paper(new AnchorPane(), new BorderPane());
            changeColorStroke = new ChangeStrokeColor(editorTest, colorTest);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: execute()");
            changeColorStroke.execute();
            assertEquals(colorTest, editorTest.getShape().getStroke());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: undo()");
            changeColorStroke.execute();
            assertEquals(colorTest, editorTest.getShape().getStroke());
            changeColorStroke.undo();
            assertEquals(lastColor, editorTest.getShape().getStroke());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: redo()");
            changeColorStroke.execute();
            assertEquals(colorTest, editorTest.getShape().getStroke());
            changeColorStroke.undo();
            assertEquals(lastColor, editorTest.getShape().getStroke());
            changeColorStroke.redo();
            assertEquals(colorTest, editorTest.getShape().getStroke());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
