package app;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;

public class Main {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        App app = new App();
        if (args.length > 0) {
            try {
                app.change(args[0]);
            } catch (IOException ex) {
                LOGGER.error(ex);
            }
        } else {
            throw new IllegalArgumentException("Source required. Try nasa.");
        }
    }
}
