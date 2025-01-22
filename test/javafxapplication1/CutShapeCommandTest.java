/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

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
public class CutShapeCommandTest {
    
    private Clipboard clipboard;
    private Paper paper;
    private Shape rectangle;
    
    
    @Before
    public void setUp() {
        
        clipboard = Clipboard.getInstance();
        clipboard.clear(); 
        paper = new Paper(new javafx.scene.layout.AnchorPane());

        
        rectangle = new Rectangle(50, 50, 100, 100);
        paper.addOnPaper(rectangle); 
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class CutShapeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");

        // Assicuriamoci che la figura sia inizialmente presente
        assertTrue(paper.getAnchorPanePaper().getChildren().contains(rectangle));

        
        CutShapeCommand cutCommand = new CutShapeCommand(paper, rectangle);
        cutCommand.execute();

        // Verifica che la figura sia stata rimossa dal Paper
        assertFalse(paper.getAnchorPanePaper().getChildren().contains(rectangle));

        // Verifica che la figura sia stata copiata nella clipboard
        assertEquals(rectangle, clipboard.getCopiedShape());
    }

    /**
     * Test of undo method, of class CutShapeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        CutShapeCommand instance = null;
        instance.undo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
