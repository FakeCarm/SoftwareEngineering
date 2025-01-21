package javafxapplication1;

import javafx.scene.shape.Shape;

public class Clipboard {
    private static Clipboard instance;
    private Shape copiedShape;

    private Clipboard() {}

    public static Clipboard getInstance() {
        if (instance == null) {
            instance = new Clipboard();
        }
        return instance;
    }

    public void copy(Shape shape) {
        this.copiedShape = shape;
    }

    public Shape getCopiedShape() {
        return copiedShape;
    }

    public void clear() {
        this.copiedShape = null;
    }
}
