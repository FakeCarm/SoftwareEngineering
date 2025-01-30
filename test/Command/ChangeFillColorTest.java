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
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafxapplication1.EllipseShapeEditor;
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
public class ChangeFillColorTest {
    
    private Ellipse ellipseTest;
    private ShapeEditor editorTest;
    private Color colorTest;
    private Color lastColor;
    private ChangeFillColor changeColorFill;
    private Paper drawingPaper;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Avvia JavaFX in modo sicuro
        new JFXPanel(); // Inizializza JavaFX
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await(); // Attende l'inizializzazione di JavaFX
    }

    @Before
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            this.ellipseTest = new Ellipse(10, 10, 10, 10);
            this.editorTest = new EllipseShapeEditor(ellipseTest, 2, 2);
            this.lastColor = (Color) ellipseTest.getFill();
            this.colorTest = Color.RED;
            this.drawingPaper = new Paper(new AnchorPane(), new BorderPane());
            this.changeColorFill = new ChangeFillColor(drawingPaper, ellipseTest, this.editorTest, this.colorTest);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ChangeFillColor.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Colore TEST: " + colorTest);
            changeColorFill.execute();
            assertEquals(colorTest, editorTest.getShape().getFill());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: undo()");
            changeColorFill.execute();
            assertEquals(colorTest, editorTest.getShape().getFill());
            changeColorFill.undo();
            assertEquals(lastColor, editorTest.getShape().getFill());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("TEST: redo()");
            changeColorFill.execute();
            assertEquals(colorTest, editorTest.getShape().getFill());
            changeColorFill.undo();
            assertEquals(lastColor, editorTest.getShape().getFill());
            changeColorFill.redo();
            assertEquals(colorTest, editorTest.getShape().getFill());
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
