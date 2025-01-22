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
public class CopyShapeCommandTest {
    
    private Clipboard clipboard;
    private Paper paper;
    private Shape rectangle;
    
    
    @Before
    public void setUp() {
        
        clipboard = Clipboard.getInstance();
        clipboard.clear(); 
        paper = new Paper(new javafx.scene.layout.AnchorPane());

       
        rectangle = new Rectangle(50, 50, 100, 100);
    }
    
    @After
    public void tearDown() {
        clipboard.clear();
    }

    /**
     * Test of execute method, of class CopyShapeCommand.
     */
     @Test
    public void testExecute() {
        System.out.println("execute");

        
        assertNull(clipboard.getCopiedShape());

        
        CopyShapeCommand copyCommand = new CopyShapeCommand(paper, rectangle);

        
        copyCommand.execute();

        // Verifica che la figura sia stata copiata nella clipboard
        assertEquals(rectangle, clipboard.getCopiedShape());
    }

    /**
     * Test of undo method, of class CopyShapeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        CopyShapeCommand instance = null;
        instance.undo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
