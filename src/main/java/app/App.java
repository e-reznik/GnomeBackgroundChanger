package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private final Map<String, String> availableSourcesMap;

    public App() throws IOException {
        availableSourcesMap = Sources.readSourceFile();
    }

    public boolean changeBackground(String selectedSource) throws IOException {
        String urlOfSource = findUrlOfImage(selectedSource);
        String urlOfImg = extractImageOnPage(selectedSource, urlOfSource);
        Path path = downloadImage(urlOfImg);
        executeScript(path);
        return true;
    }

    /**
     * Map with different image sources.
     *
     * @param source
     * @return
     */
    private String findUrlOfImage(String source) {
        String url = availableSourcesMap.get(source);
        if (url != null) {
            return url;
        } else {
            throw new NullPointerException("The source " + source + " doesn't exist. The available sources are: " + availableSourcesMap.keySet().toString());
        }
    }

    /**
     * Finds the image on that page.
     *
     * @param urlOfSource
     * @return
     * @throws IOException
     */
    private String extractImageOnPage(String selectedSource, String urlOfSource) throws IOException {
        Document doc = Jsoup.connect(urlOfSource).get();
        Element elementImg = null;
        String urlImg = null;

        switch (selectedSource) {
            case "nasa":
                elementImg = doc.getElementsByTag("img").first();
                urlImg = elementImg.parent().absUrl("href");
                break;
            case "natgeo":
                elementImg = doc.getElementsByAttributeValue("property", "og:image").first();
                urlImg = elementImg.attributes().get("content");
                break;
            default:
        }

        LOGGER.debug("Url of the image: {}", urlImg);
        return urlImg;
    }

    /**
     * Downloads and saves an image locally.
     *
     * @param url
     * @return
     * @throws IOException
     */
    private Path downloadImage(String urlOfImage) throws IOException {
        try (InputStream in = new URL(urlOfImage).openStream()) {
            Path path = Paths.get("image.jpg");
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);

            Path imgAbs = path.toAbsolutePath();

            LOGGER.debug("Local path of the image: {}", imgAbs);
            return imgAbs;
        }
    }

    /**
     * Builds and executes the script.
     *
     * @param path
     * @throws IOException
     * @throws URISyntaxException
     */
    private void executeScript(Path path) throws IOException {
        String content = "gsettings set org.gnome.desktop.background picture-uri " + path;
        Runtime.getRuntime().exec(content);
    }
}
