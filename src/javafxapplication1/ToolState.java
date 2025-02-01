package javafxapplication1;

import javafx.scene.input.MouseEvent;

/**
 * Classe astratta che rappresenta lo stato dello strumento.
 * Ogni stato concreto (es. selezione, disegno) implementa i metodi specifici per gestire l'interazione dell'utente.
 */
public abstract class ToolState {
    
    
    public ToolState() {
        // Costruttore
    } 

    /**
     * Metodo astratto da implementare per gestire il mouse quando viene premuto.
     * 
     * @param event l'evento del mouse
     */
    public abstract void onMousePressed(MouseEvent event);
    
    /**
     * Metodo astratto da implementare per gestire il mouse durante il trascinamento.
     * 
     * @param event l'evento del mouse
     */
    public abstract void onMouseDragged(MouseEvent event);
    
    /**
     * Metodo astratto da implementare per gestire il rilascio del mouse.
     * 
     * @param event l'evento del mouse
     */
    public abstract void onMouseReleased(MouseEvent event);
}
