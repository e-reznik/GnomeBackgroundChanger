package app;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        if (args.length > 0) {
            App app = new App(args[0]);
        } else {
            throw new IllegalArgumentException("Source required. Try nasa.");
        }
    }
}
