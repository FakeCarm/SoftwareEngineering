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
public class RotateShapeTest {
    
    private Rectangle shape;
    private RotateShape rotateCommand;
    private double initialRotation;
    private double newRotation;
    

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
     
            shape = new Rectangle(100, 50);
            initialRotation = 0;
            newRotation = 45;
            shape.setRotate(initialRotation);
            rotateCommand = new RotateShape( shape, initialRotation, newRotation);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @Test
    public void testExecute() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing execute...");
            rotateCommand.execute();
            assertEquals(newRotation, shape.getRotate(), 0.01);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo undo di RotateShape.
     */
    @Test
    public void testUndo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing undo...");
            rotateCommand.execute();
            rotateCommand.undo();
            assertEquals(initialRotation, shape.getRotate(), 0.01);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }

    /**
     * Test del metodo redo di RotateShape.
     */
    @Test
    public void testRedo() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            System.out.println("Testing redo...");
            rotateCommand.execute();
            rotateCommand.undo();
            rotateCommand.redo();
            assertEquals(newRotation, shape.getRotate(), 0.01);
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
}
