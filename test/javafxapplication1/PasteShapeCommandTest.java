/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
public class PasteShapeCommandTest {
    
    private Clipboard clipboard;
    private Paper paper;
    private Shape rectangle;
    private PasteShapeCommand pasteCommand;
    

    @Before
    public void setUp() {
        clipboard = Clipboard.getInstance();
        clipboard.clear(); 
        paper = new Paper(new AnchorPane());
        rectangle = new Rectangle(50, 50, 100, 100);
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.BLACK);
        clipboard.copy(rectangle);
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class PasteShapeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("Testing execute...");

        // Assicuriamoci che la clipboard contenga la figura
        assertNotNull("La clipboard dovrebbe contenere una figura.", clipboard.getCopiedShape());

        double pasteX = 200;
        double pasteY = 300;
        pasteCommand = new PasteShapeCommand(paper, pasteX, pasteY);

        // Esegui il comando
        pasteCommand.execute();

        // Verifica che una nuova figura sia stata aggiunta al Paper
        assertEquals("Il Paper dovrebbe contenere una figura incollata.", 1, paper.getAnchorPanePaper().getChildren().size());

        // Verifica che la figura sia stata posizionata correttamente
        Shape pastedShape = (Shape) paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La figura incollata dovrebbe essere un Rectangle.", pastedShape instanceof Rectangle);
        assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteX, ((Rectangle) pastedShape).getX(), 0.01);
        assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteY, ((Rectangle) pastedShape).getY(), 0.01);

        // Verifica che i colori della figura incollata corrispondano a quelli dell'originale
        assertEquals("Il colore di riempimento della figura incollata dovrebbe essere lo stesso dell'originale.", Color.RED, ((Rectangle) pastedShape).getFill());
        assertEquals("Il colore del bordo della figura incollata dovrebbe essere lo stesso dell'originale.", Color.BLACK, ((Rectangle) pastedShape).getStroke());
    }


    /**
     * Test of undo method, of class PasteShapeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("Testing undo...");

        // Esegui il comando di incolla
        double pasteX = 200;
        double pasteY = 300;
        pasteCommand = new PasteShapeCommand(paper, pasteX, pasteY);
        pasteCommand.execute();

        // Assicurati che la figura sia stata incollata
        assertEquals("La figura dovrebbe essere presente nel Paper dopo execute.", 1, paper.getAnchorPanePaper().getChildren().size());

        // Esegui undo
        pasteCommand.undo();

        // Verifica che la figura sia stata rimossa dal Paper
        assertEquals("La figura dovrebbe essere stata rimossa dal Paper dopo undo.", 0, paper.getAnchorPanePaper().getChildren().size());
    
    }
    
    @Test
    public void testRedo() {
        System.out.println("Testing redo...");

        // Esegui il comando di incolla
        double pasteX = 200;
        double pasteY = 300;
        pasteCommand = new PasteShapeCommand(paper, pasteX, pasteY);
        pasteCommand.execute();

        // Esegui undo per rimuovere la figura
        pasteCommand.undo();

        // Verifica che la figura sia stata rimossa
        assertEquals("La figura dovrebbe essere stata rimossa dal Paper dopo undo.", 0, paper.getAnchorPanePaper().getChildren().size());

        // Esegui redo
        pasteCommand.redo();

        // Verifica che la figura sia stata nuovamente aggiunta
        assertEquals("La figura dovrebbe essere stata riaggiunta al Paper dopo redo.", 1, paper.getAnchorPanePaper().getChildren().size());

        // Verifica che la figura sia posizionata correttamente
        Shape pastedShape = (Shape) paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La figura incollata dovrebbe essere un Rectangle.", pastedShape instanceof Rectangle);
        assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteX, ((Rectangle) pastedShape).getX(), 0.01);
        assertEquals("La figura incollata dovrebbe essere posizionata correttamente.", pasteY, ((Rectangle) pastedShape).getY(), 0.01);
    }

    
    
}
