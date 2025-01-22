/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import Command.AddShape;
import Command.Command;
import Command.Invoker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cassd
 */
public class InvokerTest {
    
    private Invoker testInvoker;
    private Paper testDrawingPaper;
    private Line testLine;
    private Rectangle testRectangle;

    
    @Before
    public void setUp() {
        Invoker.resetInvoker();
        testDrawingPaper = new Paper(new AnchorPane());
        testLine = new Line(0, 0, 50, 50);
        testRectangle = new Rectangle(10, 10, 100, 50);
        testInvoker = Invoker.getInvoker();

        // Assicura che gli stack di undo e redo siano vuoti
        while (testInvoker.canUndo()) {
            testInvoker.undo();
        }
        while (testInvoker.canRedo()) {
            testInvoker.redo();
        }
    }

    @After
    public void tearDown() {
        // Ripulisci lo stato globale dell'Invoker
        while (testInvoker.canUndo()) {
            testInvoker.undo();
        }
        while (testInvoker.canRedo()) {
            testInvoker.redo();
        }
        testDrawingPaper.getAnchorPanePaper().getChildren().clear();
    }


    /**
     * Test of getInvoker method, of class Invoker.
     */
    @Test
    public void testGetInvoker() {
        System.out.println("Testing getInvoker...");
        Invoker invokerInstance = Invoker.getInvoker();
        assertNotNull("L'istanza di Invoker non dovrebbe essere null.", invokerInstance);
        assertEquals("L'istanza di Invoker dovrebbe essere un singleton.", testInvoker, invokerInstance);
    }
    
    /**
     * Test of executeCommand method, of class Invoker.
     */
    @Test
    public void testExecuteCommand() {
        System.out.println("Testing executeCommand...");

        // Aggiungi una linea al Paper
        AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
        testInvoker.executeCommand(addLineCommand);

        // Verifica che la linea sia stata aggiunta
        assertTrue("La linea dovrebbe essere presente nel Paper dopo execute.",
                testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
    }
    
    @Test
    public void testUndo() {
        System.out.println("Testing undo...");

        // Aggiungi un rettangolo al Paper
        AddShape addRectangleCommand = new AddShape(testDrawingPaper, testRectangle);
        testInvoker.executeCommand(addRectangleCommand);

        // Verifica che il rettangolo sia stato aggiunto
        assertTrue("Il rettangolo dovrebbe essere presente nel Paper dopo execute.",
                testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

        // Esegui undo
        testInvoker.undo();

        // Verifica che il rettangolo sia stato rimosso
        assertFalse("Il rettangolo dovrebbe essere stato rimosso dal Paper dopo undo.",
                testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));
    }
    
    @Test
    public void testRedo() {
        System.out.println("Testing redo...");

        // Aggiungi una linea al Paper e poi esegui undo
        AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
        testInvoker.executeCommand(addLineCommand);
        testInvoker.undo();

        // Verifica che la linea sia stata rimossa
        assertFalse("La linea dovrebbe essere stata rimossa dal Paper dopo undo.",
                testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));

        // Esegui redo
        testInvoker.redo();

        // Verifica che la linea sia stata riaggiunta
        assertTrue("La linea dovrebbe essere stata riaggiunta al Paper dopo redo.",
                testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
    }
    
    @Test
    public void testCanUndo() {
        System.out.println("Testing canUndo...");

        // Inizialmente, non ci sono comandi nella pila di undo
        assertFalse("canUndo dovrebbe essere false all'inizio.", testInvoker.canUndo());

        // Aggiungi una linea e verifica che canUndo sia true
        AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
        testInvoker.executeCommand(addLineCommand);
        assertTrue("canUndo dovrebbe essere true dopo executeCommand.", testInvoker.canUndo());
    }

    @Test
    public void testCanRedo() {
        System.out.println("Testing canRedo...");

        // Inizialmente, non ci sono comandi nella pila di redo
        assertFalse("canRedo dovrebbe essere false all'inizio.", testInvoker.canRedo());

        // Aggiungi una linea, esegui undo e verifica che canRedo sia true
        AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
        testInvoker.executeCommand(addLineCommand);
        testInvoker.undo();
        assertTrue("canRedo dovrebbe essere true dopo undo.", testInvoker.canRedo());
    }

    @Test
    public void testUndoRedoStateIntegration() {
        System.out.println("Testing undo/redo integration...");

        // Aggiungi una linea e un rettangolo
        AddShape addLineCommand = new AddShape(testDrawingPaper, testLine);
        AddShape addRectangleCommand = new AddShape(testDrawingPaper, testRectangle);
        testInvoker.executeCommand(addLineCommand);
        testInvoker.executeCommand(addRectangleCommand);

        // Verifica che entrambe le forme siano presenti
        assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
        assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

        // Esegui undo due volte
        testInvoker.undo();
        testInvoker.undo();

        // Verifica che entrambe le forme siano state rimosse
        assertFalse(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
        assertFalse(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));

        // Esegui redo due volte
        testInvoker.redo();
        testInvoker.redo();

        // Verifica che entrambe le forme siano state riaggiunte
        assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testLine));
        assertTrue(testDrawingPaper.getAnchorPanePaper().getChildren().contains(testRectangle));
    }
    
}
