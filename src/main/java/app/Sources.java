package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Sources {

    private Sources() {
    }

    /**
     * Reads and maps the source file.
     *
     * @return
     * @throws IOException
     */
    public static Map<String, String> readSourceFile() throws IOException {
        InputStream inputStream = Sources.class.getResourceAsStream("/sources.txt");
        Map<String, String> sourceMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] source = line.split(" ");
                sourceMap.put(source[0], source[1]);
            }
        }
        return sourceMap;
    }
}
