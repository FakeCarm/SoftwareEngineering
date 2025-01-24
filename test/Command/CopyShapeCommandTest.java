/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.CopyShape;
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
    
    
    @Before
    public void setUp() {
        clipboard = Clipboard.getInstance();
        clipboard.clear();
        paper = new Paper(new AnchorPane(), new BorderPane());
        rectangle = new Rectangle(50, 50, 100, 100);
        previouslyCopiedShape = new Rectangle(20, 20, 50, 50);
        clipboard.copy(previouslyCopiedShape);
        copyCommand = new CopyShape(paper, rectangle);
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class CopyShape.
     */
    @Test
    public void testExecute() {
        System.out.println("Testing execute...");

        // Verifica che la clipboard contenga inizialmente la figura precedente
        assertEquals("La clipboard dovrebbe contenere la figura precedentemente copiata.",
                previouslyCopiedShape, clipboard.getCopiedShape());

        // Esegui il comando
        copyCommand.execute();

        // Verifica che la nuova figura sia stata copiata nella clipboard
        assertEquals("La figura corrente dovrebbe essere copiata nella clipboard.",
                rectangle, clipboard.getCopiedShape());
    }

    /**
     * Test of undo method, of class CopyShape.
     */
    @Test
    public void testUndo() {
        System.out.println("Testing undo...");

        // Esegui il comando e poi annullalo
        copyCommand.execute();
        copyCommand.undo();

        // Verifica che la clipboard sia stata ripristinata alla figura precedente
        assertEquals("La clipboard dovrebbe contenere la figura precedentemente copiata.",
                previouslyCopiedShape, clipboard.getCopiedShape());
    }
    
    @Test
    public void testRedo() {
        System.out.println("Testing redo...");

        // Esegui il comando, annullalo e poi ripristinalo
        copyCommand.execute();
        copyCommand.undo();
        copyCommand.redo();

        // Verifica che la figura corrente sia nuovamente copiata nella clipboard
        assertEquals("La figura corrente dovrebbe essere di nuovo copiata nella clipboard.",
                rectangle, clipboard.getCopiedShape());
    }
    
}
