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
 * @author vinjs
 */
public class DragShapeTest {
    
    private Paper paper;
    private AnchorPane pane;
    private Rectangle testRectangle;
    private ShapeEditor shapeEditor;
    private DragShape dragShapeCommand;

    private double initialX = 50.0;
    private double initialY = 50.0;
    private double finalX = 150.0;
    private double finalY = 150.0;
    
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
            pane = new AnchorPane();
            paper = new Paper(pane, new BorderPane());

            testRectangle = new Rectangle(50, 50, 100, 100);
            testRectangle.setFill(Color.BLUE);
            testRectangle.setStroke(Color.BLACK);
            paper.addOnPaper(testRectangle);
            
            shapeEditor = new RectangleShapeEditor(testRectangle, initialX, initialY);
            dragShapeCommand = new DragShape(paper, testRectangle, shapeEditor, initialX, initialY, finalX, finalY);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test of execute method, of class DragShape.
     */
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");
            dragShapeCommand.execute();
            assertEquals(finalX, testRectangle.getTranslateX(), 0.01);
            assertEquals(finalY, testRectangle.getTranslateY(), 0.01);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo undo di DragShape.
     */
    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");
            dragShapeCommand.execute();
            dragShapeCommand.undo();
            assertEquals(initialX, testRectangle.getTranslateX(), 0.01);
            assertEquals(initialY, testRectangle.getTranslateY(), 0.01);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo redo di DragShape.
     */
    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");
            dragShapeCommand.execute();
            dragShapeCommand.undo();
            dragShapeCommand.redo();
            assertEquals(finalX, testRectangle.getTranslateX(), 0.01);
            assertEquals(finalY, testRectangle.getTranslateY(), 0.01);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
}
