/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Classe che gestisce il poligono selezionato
 * @author cassd
 */
public class PolygonEditor extends ShapeEditor {
    
    private Polygon polygon;

    
    public PolygonEditor(Shape s, double x, double y) {
        super(s,x,y);
        this.polygon = (Polygon) s;
    }
    
    /**
     * Metodo che ritorna la lunghezza del poligono
     * @return 
     */
    @Override
    public double getWidth() {
        return Math.abs(this.getMaxX() - this.getMinX());
    }

    /**
     * Metodono che ritorna l'altezza del poligono
     * @return 
     */
    @Override
    public double getHeight() {
        return Math.abs(this.getMaxY() - this.getMinY());
    }
    
    /**
     * Metodo che cambia l'altezza del poligono
     * @param size 
     */
    @Override
    public void changeHeightSize(double size) {
        ObservableList<Double> points = polygon.getPoints();
        if (points.isEmpty()) {
            System.out.println("Poli vuoto");
            return; 
        }
        
        System.out.println(points);
        double minY = this.getMinY();
        System.out.println("MINIMO Y" + minY);
        
        double scalefactor = size/this.getHeight();
        
        for(int i = 1; i < points.size(); i+=2){
            double y = points.get(i);
            double newY = (y-minY)*scalefactor + minY;
            points.set(i, newY);
        }
       
        
        System.out.println("HEIGHT " + this.getHeight());
    }
    
    /**
     * Metodo che cambia la lunghezza del poligono
     * @param size 
     */
    @Override
    public void changeWidthSize(double size) {
        ObservableList<Double> points = polygon.getPoints();
        if (points.isEmpty()) {
            System.out.println("Poli vuoto");
            return; // Nessun punto nel poligono, quindi larghezza 0
        }
        
        System.out.println(points);
        double minX = this.getMinX();
        System.out.println("MINIMO X" + minX);
        
        double scalefactor = size/this.getWidth();
        
        for(int i = 0; i < points.size(); i+=2){
            double x = points.get(i);
            double newX = (x-minX)*scalefactor + minX;
            points.set(i, newX);
        }
       
        
        System.out.println("WIDTH " + this.getWidth());
    }
    
    /**
     * Metodo che ritorna la coordinata X più grande
     * @return 
     */
    private double getMaxX(){
        ObservableList<Double> points = polygon.getPoints();
        double maxX = points.get(0);
        
        if (points.isEmpty()) {   
            return 0; 
        }
        
        maxX = points.get(0);
        for (int i = 0; i < points.size(); i += 2) { // Consideriamo solo le coordinate X
            double x = points.get(i);
            
            if (x > maxX) {
                maxX = x;
            }
        }
        return maxX;
    }
    
    /**
     * Metodo che ritorna la coordinata Y più grande
     * @return 
     */
    private double getMaxY() {
        ObservableList<Double> points = polygon.getPoints();

        if (points.isEmpty()) {   
            return 0; 
        }

        double maxY = points.get(1); // Inizializzazione con il primo valore Y

        for (int i = 1; i < points.size(); i += 2) { // Consideriamo solo le coordinate Y
            double y = points.get(i);

            if (y > maxY) {
                maxY = y;
            }
        }
        return maxY;
    }

    /**
     * Metodo che ritorna la coordinata X più piccola
     * @return 
     */
    private double getMinX(){
        ObservableList<Double> points = polygon.getPoints();
        double minX = points.get(0);
       
        if (points.isEmpty()) {   
            return 0; 
        }
        
        minX = points.get(0);
        for (int i = 0; i < points.size(); i += 2) { // Consideriamo solo le coordinate X
            double x = points.get(i);
            
            if (x < minX) {
                minX = x;
               
            }
        }
        return minX;
    }
    
    /**
     * Metodo che ritorna la coordinata Y più piccola
     * @return 
     */
    private double getMinY() {
        ObservableList<Double> points = polygon.getPoints();

        if (points.isEmpty()) {   
            return 0; 
        }

        double minY = points.get(1); // Inizializzazione con il primo valore Y

        for (int i = 1; i < points.size(); i += 2) { // Consideriamo solo le coordinate Y
            double y = points.get(i);

            if (y < minY) {
                minY = y;
            }
        }
        return minY;
    }
}
