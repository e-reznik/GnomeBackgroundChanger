package app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        App app = new App();
        if (args.length > 0) {
            try {
                app.change(args[0]);
            } catch (IOException | URISyntaxException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new IllegalArgumentException("Source required. Try nasa.");
        }
    }
}
