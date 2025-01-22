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
public class ClipboardTest {
    
    private Clipboard clipboard;
    
    public ClipboardTest() {
    }
    
    
    @Before
    public void setUp() {
        clipboard = Clipboard.getInstance();
        clipboard.clear(); 
    }
    
    @After
    public void tearDown() {
        
        clipboard.clear();
    }

    /**
     * Test of getInstance method, of class Clipboard.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Clipboard firstInstance = Clipboard.getInstance();
        Clipboard secondInstance = Clipboard.getInstance();
        assertNotNull(firstInstance);
        assertNotNull(secondInstance);
        assertSame(firstInstance, secondInstance); // Devono essere la stessa istanza
    }

    /**
     * Test of copy method, of class Clipboard.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Shape shape = new Rectangle(50, 50, 100, 100); 
        clipboard.copy(shape);
        assertEquals(shape, clipboard.getCopiedShape()); // Verifica che la figura copiata sia memorizzata
    }

    /**
     * Test of getCopiedShape method, of class Clipboard.
     */
    @Test
    public void testGetCopiedShape() {
        System.out.println("getCopiedShape");
        Shape shape = new Rectangle(50, 50, 100, 100); 
        clipboard.copy(shape); 
        Shape result = clipboard.getCopiedShape();
        assertEquals(shape, result); // La figura restituita deve corrispondere a quella copiata
    }

    /**
     * Test of clear method, of class Clipboard.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Shape shape = new Rectangle(50, 50, 100, 100); 
        clipboard.copy(shape); 
        clipboard.clear(); 
        assertNull(clipboard.getCopiedShape()); // Dopo la pulizia, la figura copiata deve essere null
    }
    
}
