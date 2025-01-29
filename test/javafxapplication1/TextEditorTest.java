/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javafx.scene.paint.Color;
import static org.junit.Assert.*;

/**
 *
 * @author cassd
 */
public class TextEditorTest {
    
    private TextEditor textEditor;
    private Text text;
    
    public TextEditorTest() {
      
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException {
        new JFXPanel();
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(latch::countDown);
        latch.await();
        
        text = new Text("Test");
        text.setFont(new Font(20));
        text.setFill(Color.BLACK);
        textEditor = new TextEditor(text, 100, 200);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getWidth method, of class TextEditor.
     */
    @Test
    public void testGetWidth() {
        System.out.println("TEST: getWidth()");
        double expectedWidth = text.getBoundsInLocal().getWidth();
        assertEquals("La larghezza dovrebbe corrispondere ai limiti locali del testo", 
                     expectedWidth, textEditor.getWidth(), 0.001);
    
    }

    /**
     * Test of getHeight method, of class TextEditor.
     */
    @Test
    public void testGetHeight() {
        System.out.println("TEST: getHeight()");
        double expectedHeight = text.getFont().getSize();
        assertEquals("L'altezza dovrebbe corrispondere alla dimensione del font", 
                     expectedHeight, textEditor.getHeight(), 0.001);
    
    }

    /**
     * Test of changeHeightSize method, of class TextEditor.
     */
    @Test
    public void testChangeHeightSize() {
        System.out.println("TEST: changeHeightSize");
        double newSize = 30;
        textEditor.changeHeightSize(newSize);
        assertEquals("La dimensione del font dovrebbe venir aggiornata a 30", 
                     newSize, text.getFont().getSize(), 0.001);
    
    }

    /**
     * Test of changeWidthSize method, of class TextEditor.
     */
    @Test
    public void testChangeWidthSize() {
     
    }
    
}
