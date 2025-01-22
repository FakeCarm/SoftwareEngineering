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
    

    
    @Before
    public void setUp() {
        
        clipboard = Clipboard.getInstance();
        clipboard.clear(); 
        paper = new Paper(new AnchorPane());

        // Crea una figura di test e aggiungila alla clipboard
        rectangle = new Rectangle(50, 50, 100, 100);
        ((Rectangle) rectangle).setFill(Color.RED);
        ((Rectangle) rectangle).setStroke(Color.BLACK);
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
        System.out.println("execute");

        // Assicuriamoci che la clipboard contenga la figura
        assertNotNull(clipboard.getCopiedShape());

       
        double pasteX = 200;
        double pasteY = 300;
        PasteShapeCommand pasteCommand = new PasteShapeCommand(paper, pasteX, pasteY);
        pasteCommand.execute();

        // Verifica che una nuova figura sia stata aggiunta al Paper
        assertEquals(1, paper.getAnchorPanePaper().getChildren().size());

        // Verifica che la nuova figura sia posizionata correttamente
        Shape pastedShape = (Shape) paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue(pastedShape instanceof Rectangle);
        assertEquals(pasteX, ((Rectangle) pastedShape).getX(), 0.01);
        assertEquals(pasteY, ((Rectangle) pastedShape).getY(), 0.01);

        // Verifica che i colori della figura incollata corrispondano a quelli dell'originale
        assertEquals(Color.RED, ((Rectangle) pastedShape).getFill());
        assertEquals(Color.BLACK, ((Rectangle) pastedShape).getStroke());
    }

    /**
     * Test of undo method, of class PasteShapeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        PasteShapeCommand instance = null;
        instance.undo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
