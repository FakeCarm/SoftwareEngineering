/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.input.MouseEvent;
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
public class AddShapeTest {
    
    private SelectedShapeTool shape;
    private AddShape com;
    private Rectangle testRectangle;
    private Paper paper;
    private Color testStrokeColor;
    private Color testFillColor;
    private MouseEvent clickOnPaper;
    
    public AddShapeTest() {
    }
    
    /*
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    */
    
    @Before
    public void setUp() {
        
    }
    /*
    @After
    public void tearDown() {
    }
    */
    /**
     * Test of execute method, of class AddShape.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");

        // Crea un AnchorPane e un Paper
        AnchorPane pane = new AnchorPane();
        Paper paper = new Paper(pane);

        // Crea una forma
        Shape shape = new javafx.scene.shape.Rectangle(50, 50, 100, 100);

        // Crea un'istanza di AddShape
        AddShape instance = new AddShape(paper, shape);

        // Esegui il metodo execute
        instance.execute();

        // Verifica che la forma sia presente nell'AnchorPane del Paper
        assertTrue(pane.getChildren().contains(shape));
    }

    /**
     * Test of undo method, of class AddShape.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        AddShape instance = null;
        instance.undo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
