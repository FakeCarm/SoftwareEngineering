/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
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
    private DeleteShape deleteCommand;
    
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
        
        paper = new Paper(new AnchorPane());
        rectangle = new Rectangle(50, 50, 100, 100);
        paper.addOnPaper(rectangle);
        deleteCommand = new DeleteShape(paper, rectangle);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class DeleteShape.
     */
    @Test
    public void testExecute() {
        System.out.println("Testing execute...");

        // Assicuriamoci che la forma sia inizialmente presente
        assertTrue("La forma dovrebbe essere presente nell'AnchorPane.", 
                   paper.getAnchorPanePaper().getChildren().contains(rectangle));

        // Esegui il comando
        deleteCommand.execute();

        // Verifica che la forma sia stata rimossa
        assertFalse("La forma dovrebbe essere stata rimossa dall'AnchorPane.", 
                    paper.getAnchorPanePaper().getChildren().contains(rectangle));
    }

    /**
     * Test of undo method, of class DeleteShape.
     */
    @Test
    public void testUndo() {
        System.out.println("Testing undo...");

        // Esegui il comando e poi annullalo
        deleteCommand.execute();
        deleteCommand.undo();

        // Verifica che la forma sia stata riaggiunta
        assertTrue("La forma dovrebbe essere stata riaggiunta all'AnchorPane.", 
                   paper.getAnchorPanePaper().getChildren().contains(rectangle));
    }
    
    @Test
    public void testRedo() {
        System.out.println("Testing redo...");

        // Esegui il comando, annullalo e poi ripristinalo
        deleteCommand.execute();
        deleteCommand.undo();
        deleteCommand.redo();

        // Verifica che la forma sia stata nuovamente rimossa
        assertFalse("La forma dovrebbe essere stata nuovamente rimossa dall'AnchorPane.", 
                    paper.getAnchorPanePaper().getChildren().contains(rectangle));
    }
    
}
