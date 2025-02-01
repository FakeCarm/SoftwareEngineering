/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.shape.Rectangle;
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
public class MirrorShapeTest {
    
    private Rectangle shape;
    private MirrorShape mirrorCommandVertical;
    private MirrorShape mirrorCommandHorizontal;
    private double originalScaleX;
    private double originalScaleY;
    
    @Before
    public void setUp() {
        new JFXPanel(); // Inizializza JavaFX
        Platform.runLater(() -> {
            shape = new Rectangle(100, 50);
            originalScaleX = shape.getScaleX();
            originalScaleY = shape.getScaleY();
            mirrorCommandVertical = new MirrorShape(shape, true);  // Specchiatura verticale
            mirrorCommandHorizontal = new MirrorShape(shape, false);  // Specchiatura orizzontale
        });
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testExecuteVertical() {
        Platform.runLater(() -> {
            mirrorCommandVertical.execute();
            assertEquals(-originalScaleY, shape.getScaleY(), 0.1);
        });
    }

    @Test
    public void testUndoVertical() {
        Platform.runLater(() -> {
            mirrorCommandVertical.execute();
            mirrorCommandVertical.undo();
            assertEquals(originalScaleY, shape.getScaleY(), 0.1);
        });
    }

    @Test
    public void testRedoVertical() {
        Platform.runLater(() -> {
            mirrorCommandVertical.execute();
            mirrorCommandVertical.undo();
            mirrorCommandVertical.redo();
            assertEquals(-originalScaleY, shape.getScaleY(), 0.1);
        });
    }
    
        @Test
    public void testExecuteHorizontal() {
        Platform.runLater(() -> {
            mirrorCommandHorizontal.execute();
            assertEquals(-originalScaleX, shape.getScaleX(), 0.1);
        });
    }

    @Test
    public void testUndoHorizontal() {
        Platform.runLater(() -> {
            mirrorCommandHorizontal.execute();
            mirrorCommandHorizontal.undo();
            assertEquals(originalScaleX, shape.getScaleX(), 0.1);
        });
    }

    @Test
    public void testRedoHorizontal() {
        Platform.runLater(() -> {
            mirrorCommandHorizontal.execute();
            mirrorCommandHorizontal.undo();
            mirrorCommandHorizontal.redo();
            assertEquals(-originalScaleX, shape.getScaleX(), 0.1);
        });
    }
    
}
