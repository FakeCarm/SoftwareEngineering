/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author cassd
 */

public class PolygonEditorTest {

    private PolygonEditor polygonEditor;
    private Polygon polygon;

    @Before
    public void setUp() {
       
        polygon = new Polygon(0, 0, 4, 0, 4, 3, 0, 3);
        polygonEditor = new PolygonEditor(polygon, 0, 0);
    }

    @Test
    public void testGetWidth() {
        System.out.println("TEST: getWidth()");
        double expectedWidth = 4;
        assertEquals("Risultato atteso 4", expectedWidth, polygonEditor.getWidth(), 0.001);
    }

    @Test
    public void testGetHeight() {
        System.out.println("TEST: getHeight()");
        double expectedHeight = 3;
        assertEquals("Risultato atteso 3", expectedHeight, polygonEditor.getHeight(), 0.001);
    }

    @Test
    public void testChangeWidthSize() {
        System.out.println("TEST: changeWidthSize()");
        polygonEditor.changeWidthSize(8);
        
        ObservableList<Double> points = polygon.getPoints();
        
        assertEquals("Risultato atteso 8", 8, polygonEditor.getWidth(), 0.001);
    }

    @Test
    public void testChangeHeightSize() {
        System.out.println("TEST: changeHeightSize()");
        polygonEditor.changeHeightSize(6);
        
     
        assertEquals("Risultato atteso 6", 6, polygonEditor.getHeight(), 0.001);
    }
}
