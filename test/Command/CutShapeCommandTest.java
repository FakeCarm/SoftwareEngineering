/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.CutShape;
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
    
    
    @Before
    public void setUp() {
        clipboard = Clipboard.getInstance();
        clipboard.clear();
        paper = new Paper(new AnchorPane(), new BorderPane());
        rectangle = new Rectangle(50, 50, 100, 100);
        paper.addOnPaper(rectangle);
        cutCommand = new CutShape(paper, rectangle);
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class CutShape.
     */
    @Test
    public void testExecute() {
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
    }

    /**
     * Test of undo method, of class CutShape.
     */
    @Test
    public void testUndo() {
        System.out.println("Testing undo...");

        // Esegui il comando e poi annullalo
        cutCommand.execute();
        cutCommand.undo();

        // Verifica che la figura sia stata riaggiunta al Paper
        assertTrue("La figura dovrebbe essere stata riaggiunta al Paper.", 
                   paper.getAnchorPanePaper().getChildren().contains(rectangle));

        // Verifica che la clipboard sia stata ripristinata allo stato precedente
        assertNull("La clipboard dovrebbe essere vuota se la figura non era originariamente presente nella clipboard.", 
                   clipboard.getCopiedShape());
    }
    
    @Test
    public void testRedo() {
        System.out.println("Testing redo...");

        // Esegui il comando, annullalo e poi ripristinalo
        cutCommand.execute();
        cutCommand.undo();
        cutCommand.redo();

        // Verifica che la figura sia stata rimossa nuovamente dal Paper
        assertFalse("La figura dovrebbe essere stata rimossa nuovamente dal Paper.", 
                    paper.getAnchorPanePaper().getChildren().contains(rectangle));

        // Verifica che la figura sia ancora presente nella clipboard
        assertEquals("La figura dovrebbe essere ancora presente nella clipboard.", 
                     rectangle, clipboard.getCopiedShape());
    }
    
}
