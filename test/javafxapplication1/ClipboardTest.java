/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

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
    
    public ClipboardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Clipboard.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Clipboard expResult = null;
        Clipboard result = Clipboard.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class Clipboard.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Shape shape = null;
        Clipboard instance = null;
        instance.copy(shape);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCopiedShape method, of class Clipboard.
     */
    @Test
    public void testGetCopiedShape() {
        System.out.println("getCopiedShape");
        Clipboard instance = null;
        Shape expResult = null;
        Shape result = instance.getCopiedShape();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class Clipboard.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        Clipboard instance = null;
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
