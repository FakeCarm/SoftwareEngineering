/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
public class SelectedShapeToolTest {
    
    private SelectedShapeToolImpl tool;
    
    public SelectedShapeToolTest() {
    }
    
    
    @Before
    public void setUp() {
        tool = new SelectedShapeToolImpl(Color.RED, Color.BLUE);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of strokeColorProperty method, of class SelectedShapeTool.
     */
    @Test
    public void testGetStrokeColor() {
        
        assertEquals(Color.RED, tool.getStrokeColor());
        
        
        
    }

    /**
     * Test of fillColorProperty method, of class SelectedShapeTool.
     */
    @Test
    public void testGetFillColor() {
        assertEquals(Color.BLUE, tool.getFillColor());
       
    }

   

    public class SelectedShapeToolImpl extends SelectedShapeTool {

        public SelectedShapeToolImpl(Color strokeColor, Color fillColor) {
            super(strokeColor, fillColor);
        }

        @Override
        public void onMousePressed(MouseEvent event) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onMouseDragged(MouseEvent event) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void onMouseReleased(MouseEvent event) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
