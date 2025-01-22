/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
public class DeleteShapeTest {
    
    private Paper paper;
    private Shape rectangle;
    
    public DeleteShapeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // Crea un'istanza di Paper e una forma da aggiungere/rimuovere
        paper = new Paper(new javafx.scene.layout.AnchorPane());
        rectangle = new Rectangle(50, 50, 100, 100);

        // Aggiungi la forma al Paper
        paper.addOnPaper(rectangle);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class DeleteShape.
     */
    @Test
    public void testExecute() {
        // Assicuriamoci che la forma sia inizialmente presente
        assertTrue(paper.getAnchorPanePaper().getChildren().contains(rectangle));

        // Crea un'istanza del comando DeleteShape
        DeleteShape deleteCommand = new DeleteShape(paper, rectangle);

        // Esegui il comando
        deleteCommand.execute();

        // Verifica che la forma sia stata rimossa
        assertFalse(paper.getAnchorPanePaper().getChildren().contains(rectangle));
    }

    /**
     * Test of undo method, of class DeleteShape.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        DeleteShape instance = null;
        instance.undo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
