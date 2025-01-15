/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author cassd
 */
public class LineToolTest {
    
    Line testLine;
    
    MouseEvent mouseEvent;
    
   
         
    public LineToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testLine = new Line(20,20,20,20);
        testLine.setStartX(event.getX());
        testLine.setStartY(event.getY());
        testLine.setEndX(event.getX());
        testLine.setEndY(event.getY());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of onMousePressed method, of class LineTool.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("onMousePressed");
        MouseEvent event = null;
        LineTool instance = null;
        instance.onMousePressed(event);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of onMouseDragged method, of class LineTool.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("onMouseDragged");
        MouseEvent event = null;
        LineTool instance = null;
        instance.onMouseDragged(event);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of onMouseReleased method, of class LineTool.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("onMouseReleased");
        MouseEvent event = null;
        LineTool instance = null;
        instance.onMouseReleased(event);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
